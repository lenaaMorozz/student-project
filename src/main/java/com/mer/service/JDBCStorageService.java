package com.mer.service;

import com.mer.dto.AvgGradeStudentDTO;
import com.mer.dto.ChangeGradeByNameAndGroupDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCStorageService {

    public List<AvgGradeStudentDTO> getAvgGradeBy(int group) {
        return TransactionScript.getInstance().getAvgGrade(group);
    }

    public void updateGradeByNameAndGroup(ChangeGradeByNameAndGroupDTO studentDTO) {
        TransactionScript.getInstance().updateGradeByNameAndGroup(studentDTO);
    }

    public static final class TransactionScript {
        private static final String URL = "jdbc:postgresql://localhost:32769/student";
        private static final String LOGIN = "postgres";
        private static final String PASSWORD = "pass";
        private Connection connection;

        public TransactionScript() {
            try {
                this.connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        private static final TransactionScript instance = new TransactionScript();

        public static TransactionScript getInstance() {
            return instance;
        }


        public List<AvgGradeStudentDTO> getAvgGrade(int group) {
            List<AvgGradeStudentDTO> students = new ArrayList<>();

            try {
                try {
                    connection.setAutoCommit(false);
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            """
                                    select first_name, last_name, group_num, AVG(grade) as avg_grade 
                                    from student.student st
                                                   join student.grade gd on gd.student_id = st.id
                                                   join student."group" gp on gp.id = st.group_id
                                    where gp.group_num = ?
                                    group by first_name, last_name, group_num
                                    """
                    );
                    preparedStatement.setInt(1, group);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        students.add(new AvgGradeStudentDTO(resultSet.getString("first_name"),
                                resultSet.getString("last_name"),
                                resultSet.getDouble("avg_grade")));
                    }
                    connection.commit();
                } catch (Exception e) {
                    connection.rollback();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return students;
        }

        public void updateGradeByNameAndGroup(ChangeGradeByNameAndGroupDTO studentDTO) {
            try {
                try {
                    connection.setAutoCommit(false);
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            """
                                    update student.grade gd
                                    set grade = ?
                                    from student.student s
                                             join student."group" g on g.id = s.group_id
                                    where s.first_name = ?
                                      and s.last_name = ?
                                      and g.group_num = ?
                                      and gd.subject_id = (select id from student.subject sb where sb.name = ?)
                                      and gd.student_id = s.id;
                                    """
                    );
                    preparedStatement.setInt(1, studentDTO.getGrade());
                    preparedStatement.setString(2, studentDTO.getFirstName());
                    preparedStatement.setString(3, studentDTO.getLastName());
                    preparedStatement.setInt(4, studentDTO.getGroup());
                    preparedStatement.setString(5, studentDTO.getSubject());
                    int executeUpdate = preparedStatement.executeUpdate();
                    if (executeUpdate == 0) {
                        throw new RuntimeException();
                    }
                    connection.commit();
                } catch (Exception e) {
                    connection.rollback();
                    throw new RuntimeException();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

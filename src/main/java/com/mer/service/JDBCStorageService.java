package com.mer.service;

import com.mer.dto.AvgGradeStudentDTO;
import com.mer.dto.ExcellentDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCStorageService {

    public double getAvgGradeForHighGroup() {
        return TransactionScript.getInstance().getAvgGradeForHighGroup();
    }

    public List<ExcellentDTO> getExcellentStudentsAboveAge(int age) {
        return TransactionScript.getInstance().getExcellentStudentsAboveAge(age);
    }

    public List<AvgGradeStudentDTO> getAvgGradeBy(int group) {
        return TransactionScript.getInstance().getAvgGrade(group);
    }


    public static final class TransactionScript {
        private String url = "jdbc:postgresql://localhost:32768/student";
        private String login = "postgres";
        private String password = "pass";
        private Connection connection;

        public TransactionScript() {
            try {
                this.connection = DriverManager.getConnection(url, login, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        private static final TransactionScript instance = new TransactionScript();

        public static TransactionScript getInstance() {
            return instance;
        }

        public double getAvgGradeForHighGroup() {
            double avgGrade = 0;
            try {
                try {
                    connection.setAutoCommit(false);
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            """
                                    select AVG(grade) avg_grade
                                    from student.grade gd
                                            join student.student st on st.id = gd.student_id
                                            join student."group" gp on st.group_id = gp.id
                                    where gp.group_num = 10 OR gp.group_num = 11
                                    """
                    );
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        avgGrade = resultSet.getDouble("avg_grade");
                    }
                    connection.commit();
                } catch (Exception e) {
                    connection.rollback();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return avgGrade;
        }

        public List<ExcellentDTO> getExcellentStudentsAboveAge(int age) {
            List<ExcellentDTO> students = new ArrayList<>();
            try {
                try {
                    connection.setAutoCommit(false);
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            """
                                    select distinct first_name, last_name, age
                                    from student.student st
                                             join student.grade g on g.student_id = st.id
                                    where st.age >= ?
                                      and not exists (
                                        select 1 from student.grade g
                                        where g.student_id = st.id
                                          and g.grade != 5);
                                    """
                    );
                    preparedStatement.setInt(1, age);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        students.add(new ExcellentDTO(resultSet.getString("first_name"),
                                resultSet.getString("last_name"),
                                resultSet.getInt("age")));
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
                                    where "group".group_num = ?
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
    }
}

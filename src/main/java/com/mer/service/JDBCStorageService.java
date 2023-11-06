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

    public List<AvgGradeStudentDTO> getAvgGradeByLastName(String lastName) {
        return TransactionScript.getInstance().getAvgGradeByLastName(lastName);
    }


    public static final class TransactionScript {
        private String url = "jdbc:postgresql://localhost/student";
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
                                    select AVG(grade) avg_grade from grade g
                                    join student st on st.id = g.student_id
                                    join subject sub on sub.id = g.subject_id
                                    where st.group = 10 AND st.group = 11
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
                                    select first_name, last_name, age from student st
                                    join grade g on g.student_id = st.id
                                    where st.age >= ? AND g.grade = 5;
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

        public List<AvgGradeStudentDTO> getAvgGradeByLastName(String lastName) {
            List<AvgGradeStudentDTO> students = new ArrayList<>();

            try {
                try {
                    connection.setAutoCommit(false);
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            """
                                    select first_name, last_name, group_num, AVG(grade) as avg_grade from student st
                                    join grade gd on g.student_id = st.id
                                    join group gp on gp.id = st.group_id
                                    where last_name = ?
                                    """
                    );
                    preparedStatement.setString(1, lastName);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        students.add(new AvgGradeStudentDTO(resultSet.getString("first_name"),
                                resultSet.getString("last_name"),
                                resultSet.getInt("group_num"),
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

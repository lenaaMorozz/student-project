package com.mer;

import com.mer.groups.MyLinkedList;
import com.mer.model.Student;
import lombok.Data;


@Data
public class StudentAnalyzer {

    private CSVReader csvReader;

    public double calculateAverageGrade(int group) {
        double grade = 0;
        MyLinkedList<Student> students = csvReader.classroomDataGroup.getStudents(group);
        for (Student student : students) {
            grade += (double) (student.getPhysicsGrade()
                               + student.getMathematicsGrade()
                               + student.getRusGrade()
                               + student.getLiteratureGrade()
                               + student.getGeometryGrade()
                               + student.getInformaticsGrade()) / 6;

        }
        return grade / students.getSize();
    }

    public void findExcellentStudentsAboveAge(int age) {
        for (int i = age; i <= 17; i++) {
            MyLinkedList<Student> students = csvReader.personAgeDataGroup.getStudents(i);
            for (Student student : students) {
                if (student.getPhysicsGrade() == 5
                    && student.getMathematicsGrade() == 5
                    && student.getRusGrade() == 5
                    && student.getLiteratureGrade() == 5
                    && student.getGeometryGrade() == 5
                    && student.getInformaticsGrade() == 5) {
                    System.out.println(student);
                }
            }
        }
    }

    public void findStudentsByLastName(String lastName) {
        if (lastName.toUpperCase().charAt(0) < 1040
            || lastName.toUpperCase().charAt(0) > 1071) {
            System.out.println("Фамилия должна быть на русском языке");
            return;
        }

        MyLinkedList<Student> students = csvReader.personNameDataGroup.getStudents(lastName.toUpperCase().charAt(0));
        for (Student student : students) {
            if (lastName.equals(student.getLastName())) {
                System.out.println(student);
            }
        }
    }
}

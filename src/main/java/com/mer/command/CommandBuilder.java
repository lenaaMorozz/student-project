package com.mer.command;

import com.mer.model.Student;
import com.mer.service.StudentService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CommandBuilder {
    private final StudentService studentService;

    public CommandBuilder(StudentService studentService) {
        this.studentService = studentService;
        studentService.saveStudents();
    }

    public Command help() {
        return () ->
                System.out.println("""
                        "Команды:"\n
                        1. calculateAverageGrade <номер класса> - вывод средней оценки всех учеников по
                        всем предметам данного класса;
                        2. findExcellentStudents <возраст ученика> - вывод всех отличников старше указанного возраста;
                        3. findStudents <фамилия> - выводит всех студентов с указанной фамилией.
                        """);
    }

    public Command findExcellentStudentsAboveAge(int age) {
        ArrayList<Student> students = new ArrayList<>();
        boolean isAgeValid = true;

        while (isAgeValid) {
            Optional<List<Student>> studentsByAge = studentService.getStudentsByAge(age);
            if (studentsByAge.isEmpty()) {
                isAgeValid = false;
            } else {
                students.addAll(studentsByAge.get());
                age++;
            }
        }
        return () ->
                students.stream()
                        .filter(student ->
                                student.getInformaticsGrade() == 5
                                && student.getPhysicsGrade() == 5
                                && student.getMathematicsGrade() == 5
                                && student.getLiteratureGrade() == 5
                                && student.getGeometryGrade() == 5
                                && student.getRusGrade() == 5)
                        .forEach(System.out::println);
    }

    public Command findAverageGradeInGroup(int group) {
        return () ->
                studentService.getStudentsByGroup(group).stream()
                        .flatMapToDouble(student ->
                                Arrays.asList(student.getPhysicsGrade(), student.getRusGrade()).stream()
                                        .mapToDouble(Double::valueOf))
                        .average()
                        .ifPresentOrElse(
                                avg -> System.out.printf("Средняя оценка учеников %d класса: %.2f%n", group, avg),
                                () -> System.out.println("Такого класса нет")
                        );


    }

    public Command findStudentsByLastName(String lastName) {
        return () ->
                studentService.getStudentsByLastName(lastName)
                        .forEach(System.out::println);
    }

}


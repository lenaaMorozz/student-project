package com.mer.command;

import com.mer.model.Student;
import com.mer.service.StudentService;

import java.util.Arrays;

public class CommandBuilder {
    private final StudentService studentService;

    public CommandBuilder(StudentService studentService) {
        this.studentService = studentService;
        studentService.saveStudents();
    }

    public Command findExcellentStudentsAboveAge(int age) {
        return () ->
                studentService.getStudentsByAge(age).stream()
                        .filter(student -> student.getAge() >= age)
                        .filter(student -> student.getInformaticsGrade() == 5
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


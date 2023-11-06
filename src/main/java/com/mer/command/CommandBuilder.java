package com.mer.command;

import com.mer.dto.ExcellentDTO;
import com.mer.service.JDBCStorageService;

import java.util.List;

public class CommandBuilder {
    private final JDBCStorageService jdbcStorageService;

    public CommandBuilder(JDBCStorageService jdbcStorageService) {
        this.jdbcStorageService = jdbcStorageService;
    }

    public Command help() {
        return () ->
                System.out.println("""
                         "Команды:"\n
                         1. findAvgGradeForHighGroup <номер класса> - вывод средней оценки всех учеников по
                         всем предметам 10 и 11 классов;
                         2. findExcellentStudentsAboveAge <возраст ученика> - вывод всех отличников старше указанного возраста;
                         3. findAvgGradeByLastName <фамилия> - выводит среднюю оценку у студентов с указанной фамилией.
                        """);
    }

    public Command findAvgGradeForHighGroup() {
        double avgGrade = jdbcStorageService.getAvgGradeForHighGroup();
        return () ->
                System.out.printf("Средняя оценка учеников 10 и 11 классов: %.2f%n", avgGrade);
    }

    public Command findExcellentStudentsAboveAge(int age) {
        List<ExcellentDTO> studentsByAge = jdbcStorageService.getExcellentStudentsAboveAge(age);
        return () -> studentsByAge
                .forEach(System.out::println);
    }

    public Command findAvgGradeByLastName(String lastName) {
        return () ->
                jdbcStorageService.getAvgGradeByLastName(lastName)
                        .forEach(System.out::println);
    }

}


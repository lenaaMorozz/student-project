package com.mer;


import com.mer.command.Command;
import com.mer.command.CommandBuilder;
import com.mer.service.StudentService;
import com.mer.util.DataLoaderImpl;

import java.nio.file.Path;


public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("You need to enter parameters\nSee 'help'");
            return;
        }
        try {
            CommandBuilder commandBuilder =
                    new CommandBuilder(new StudentService(new DataLoaderImpl(
                            Path.of("src/main/resources/students.csv")
                            .toAbsolutePath().toString())));
            Command command = commandBuilder.help();

            switch (args[0]) {
                case "calculateAverageGrade" -> command = commandBuilder
                        .findAverageGradeInGroup(Integer.parseInt(args[1]));
                case "findExcellentStudents" -> command = commandBuilder
                        .findExcellentStudentsAboveAge(Integer.parseInt(args[1]));
                case "findStudents" -> command = commandBuilder
                        .findStudentsByLastName(args[1]);
            }

            command.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
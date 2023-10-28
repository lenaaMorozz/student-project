package com.mer;


import com.mer.command.Command;
import com.mer.command.CommandBuilder;
import com.mer.service.StudentService;
import com.mer.util.DataLoaderImpl;


public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("You need to enter parameters\nSee 'help'");
            return;
        }
        try {
            CommandBuilder commandBuilder = new CommandBuilder(new StudentService(new DataLoaderImpl("src/main/resources/students.csv")));
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
        } catch (IllegalArgumentException e) {
            System.out.println("You need to enter parameters\nSee 'help'");
        }
    }
}
package com.mer;

import com.mer.command.Command;
import com.mer.command.CommandBuilder;
import com.mer.service.JDBCStorageService;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("You need to enter parameters\nSee 'help'");
            return;
        }
        try {
            CommandBuilder commandBuilder =
                    new CommandBuilder(new JDBCStorageService());
            Command command = commandBuilder.help();

            switch (args[0]) {
                case "findAvgGradeForHighGroup" -> command = commandBuilder
                        .findAvgGradeForHighGroup();
                case "findExcellentStudentsAboveAge" -> command = commandBuilder
                        .findExcellentStudentsAboveAge(Integer.parseInt(args[1]));
                case "findAvgGradeByLastName" -> command = commandBuilder
                        .findAvgGradeByLastName(args[1]);
            }

            command.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
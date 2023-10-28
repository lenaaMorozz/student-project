package com.mer;


import com.mer.command.Command;
import com.mer.command.CommandBuilder;
import com.mer.service.StudentAnalyzer;
import com.mer.service.StudentService;
import com.mer.util.DataLoaderImpl;


public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("You need to enter parameters\nSee 'help'");
            return;
        }
        try {
            Command command = new CommandBuilder(new StudentService(new DataLoaderImpl(args[0])))
                    .findExcellentStudentsAboveAge(Integer.parseInt(args[2]));
            command.execute();
        } catch (IllegalArgumentException e) {
            System.out.println("You need to enter parameters\nSee 'help'");
        }

//        CSVReader csvReader = new CSVReader();
//        System.out.println("Введите путь до файла \"students.csv\":");
//        csvReader.fillDataGroup(new Scanner(System.in).nextLine()); //путь до файла "students.csv"
//        StudentAnalyzer studentAnalyzer = new StudentAnalyzer();
//        studentAnalyzer.setCsvReader(csvReader);
//
//        System.out.println("Средняя оценка учеников 10 класса: "
//                           + studentAnalyzer.calculateAverageGrade(10));
//        System.out.println("Средняя оценка учеников 11 класса: "
//                           + studentAnalyzer.calculateAverageGrade(11));
//
//        System.out.println("Отличники старше 14 лет: ");
//        studentAnalyzer.findExcellentStudentsAboveAge(14);
//
//        studentAnalyzer.findStudentsByLastName();

    }
}
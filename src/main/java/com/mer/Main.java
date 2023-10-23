package com.mer;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader();
        System.out.println("Введите путь до файла \"students.csv\":");
        csvReader.fillDataGroup(new Scanner(System.in).nextLine()); //путь до файла "students.csv"
        StudentAnalyzer studentAnalyzer = new StudentAnalyzer();
        studentAnalyzer.setCsvReader(csvReader);

        System.out.println("Средняя оценка учеников 10 класса: "
                           + studentAnalyzer.calculateAverageGrade(10));
        System.out.println("Средняя оценка учеников 11 класса: "
                           + studentAnalyzer.calculateAverageGrade(11));

        System.out.println("Отличники старше 14 лет: ");
        studentAnalyzer.findExcellentStudentsAboveAge(14);

        studentAnalyzer.findStudentsByLastName();

    }
}
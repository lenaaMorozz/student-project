package com.mer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CSVReaderTest {
    private static CSVReader csvReader;

    @BeforeAll
    public static void init() {
        csvReader = new CSVReader();
        csvReader.fillDataGroup("/Users/elenamerzlakova/Downloads/students.csv");
    }

    @Test
    public void checkAmountStudent_ClassroomDataGroupTest() {
        int amountStudent = 0;
        for (int i = 0; i < 12; i++) {
            amountStudent += csvReader.classroomDataGroup.getStudents(i + 1).getSize();
        }
        Assertions.assertEquals(100000, amountStudent);
    }

    @Test
    public void checkAmountStudent_PersonAgeDataGroupTest() {
        int amountStudent = 0;
        for (int i = 0; i < 13; i++) {
            amountStudent += csvReader.personAgeDataGroup.getStudents(i + 5).getSize();
        }
        Assertions.assertEquals(100000, amountStudent);
    }

    @Test
    public void checkAmountStudent_PersonNameDataGroupTest() {
        int amountStudent = 0;
        for (int i = 0; i < 32; i++) {
            amountStudent += csvReader.personNameDataGroup.getStudents((char) (i + 1040)).getSize();
        }
        Assertions.assertEquals(100000, amountStudent);
    }
}

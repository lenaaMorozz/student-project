package com.mer;

import com.mer.groups.*;
import com.mer.model.Student;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Getter
public class CSVReader {

    DataGroup<Integer, MyLinkedList<Student>> classroomDataGroup = new ClassroomDataGroup<>();
    DataGroup<Integer, MyLinkedList<Student>> personAgeDataGroup = new PersonAgeDataGroup<>();
    DataGroup<Character, MyLinkedList<Student>> personNameDataGroup = new PersonNameDataGroup<>();

    public void fillDataGroup(String path) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String row;
            bufferedReader.readLine(); //пропускаем заголовок
            while ((row = bufferedReader.readLine()) != null) {
                String[] data = row.split(",");

                Student student = Student.builder()
                        .lastName(data[0])
                        .firstName(data[1])
                        .age(Integer.parseInt(data[2]))
                        .group(Integer.parseInt(data[3]))
                        .physicsGrade(Integer.parseInt(data[4]))
                        .mathematicsGrade(Integer.parseInt(data[5]))
                        .rusGrade(Integer.parseInt(data[6]))
                        .literatureGrade(Integer.parseInt(data[7]))
                        .geometryGrade(Integer.parseInt(data[8]))
                        .informaticsGrade(Integer.parseInt(data[9]))
                        .build();

                classroomDataGroup.addStudent(student);
                personAgeDataGroup.addStudent(student);
                personNameDataGroup.addStudent(student);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

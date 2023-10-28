package com.mer.util;

import com.mer.model.Student;
import lombok.Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

@Data
public class DataLoaderImpl implements DataLoader<Student> {
    private BufferedReader bufferedReader;
    private boolean firstLineSkipped;


    public DataLoaderImpl(String path) {
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
            firstLineSkipped = false;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Optional<Student> load() {
        try {
            if (!firstLineSkipped) {
                bufferedReader.readLine();
                firstLineSkipped = true;
            }

            String row = bufferedReader.readLine();
            if (row == null) {
                bufferedReader.close();
                return Optional.empty();
            }
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
            return Optional.of(student);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

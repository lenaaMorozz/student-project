package com.mer;

import com.mer.model.Student;

public class Main2 {
    public static void main(String[] args) {
        DataGroup<Student> studentDataGroup = new DataGroup<>(Student::getLastName);
    }
}

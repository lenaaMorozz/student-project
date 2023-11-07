package com.mer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.StringJoiner;

@Getter
@AllArgsConstructor
public class AvgGradeStudentDTO {
    private String firstName;
    private String lastName;
//    private int group;
    private double avgGrade;

//    @Override
//    public String toString() {
//        return new StringJoiner(", ")
//                .add("firstName='" + firstName + "'")
//                .add("lastName='" + lastName + "'")
//                .add("group=" + group)
//                .add("avgGrade=" + avgGrade)
//                .toString();
//    }
}

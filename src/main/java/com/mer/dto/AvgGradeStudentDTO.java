package com.mer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class AvgGradeStudentDTO {
    private String firstName;
    private String lastName;
    private double avgGrade;
}

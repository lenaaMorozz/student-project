package com.mer.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String firstName;
    private String lastName;
    private int age;
    private int group;
    private int physicsGrade;
    private int mathematicsGrade;
    private int rusGrade;
    private int literatureGrade;
    private int geometryGrade;
    private int informaticsGrade;

}

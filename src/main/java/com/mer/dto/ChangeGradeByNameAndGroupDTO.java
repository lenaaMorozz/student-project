package com.mer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ChangeGradeByNameAndGroupDTO {
    private String firstName;
    private String lastName;
    private int grade;
    private int group;
    private String subject;
}

package com.mer.dto;

import lombok.AllArgsConstructor;
import java.util.StringJoiner;

@AllArgsConstructor
public class ExcellentDTO {
    private String firstName;
    private String lastName;
    private int age;

    @Override
    public String toString() {
        return new StringJoiner(", ")
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("age=" + age)
                .toString();
    }
}

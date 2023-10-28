package com.mer.service;

import com.mer.groups.DataGroup;
import com.mer.model.Student;
import com.mer.util.DataLoader;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class StudentService {
    private DataLoader<Student> dataLoader;
    private final DataGroup<Student> dataAge = new DataGroup<>(Student::getAge);
    private final DataGroup<Student> dataLastName = new DataGroup<>(Student::getLastName);
    private final DataGroup<Student> dataGroup = new DataGroup<>(Student::getGroup);

    public StudentService(DataLoader<Student> dataLoader) {
        this.dataLoader = dataLoader;
    }

    public void saveStudents() {
        Optional<Student> load;
        while ((load = dataLoader.load()).isPresent()) {
            Student student = load.get();

            dataAge.add(student);
            dataLastName.add(student);
            dataGroup.add(student);
        }
    }

    public List<Student> getStudentsByAge(Integer age) {
        return dataAge.getList(age);
    }

    public List<Student> getStudentsByLastName(String lastName) {
        return dataLastName.getList(lastName);
    }

    public List<Student> getStudentsByGroup(Integer group) {
        return dataGroup.getList(group);
    }

}

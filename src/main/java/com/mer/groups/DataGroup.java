package com.mer.groups;

import com.mer.Student;

public interface DataGroup<K, V> {
    void addStudent(Student student);
    Student[] getStudents(K key);

}

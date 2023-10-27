package com.mer.groups;

import com.mer.model.Student;
import com.mer.util.MyHashMap;
import com.mer.util.MyLinkedList;

public class PersonNameDataGroup extends MyHashMap<Character, MyLinkedList<Student>> {

    public PersonNameDataGroup() {
        setSize(32); //сколько букв в алфавите(без Ё), не пропускаем буквы Ы, Ъ и тд, чтобы коды символов были по порядку
        initialTable();
    }

    @Override
    public void addStudent(Student student) {
        getTable()[student.getLastName().charAt(0) - 1040]
                .value.add(student);
    }

    @Override
    public MyLinkedList<Student> getStudents(Character key) {
        return getTable()[key - 1040].value;
    }

    @Override
    protected void initialTable() {
        for (int i = 0; i < 32; i++) {
            getTable()[i] = new Node<>((char) (i + 1040), new MyLinkedList<>());
        }
    }
}

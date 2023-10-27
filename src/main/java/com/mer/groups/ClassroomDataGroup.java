package com.mer.groups;

import com.mer.model.Student;
import com.mer.util.MyHashMap;
import com.mer.util.MyLinkedList;

public class ClassroomDataGroup extends MyHashMap<Integer, MyLinkedList<Student>> {


    public ClassroomDataGroup() {
        setSize(12); //12 классов
        initialTable();
    }


    @Override
    public void addStudent(Student student) {
        getTable()[student.getGroup() - 1].value.add(student);
    }

    @Override
    public MyLinkedList<Student> getStudents(Integer key) {
        return getTable()[key - 1].value;
    }

    @Override
    protected void initialTable() {
        for (int i = 0; i < 12; i++) {
            getTable()[i] = new Node<>(i + 1, new MyLinkedList<>());
        }
    }
}

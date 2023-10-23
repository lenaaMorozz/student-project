package com.mer.groups;

import com.mer.Student;

public class ClassroomDataGroup<K extends Integer, V extends MyLinkedList<Student>> extends DataGroupAbstr<K, V> {


    public ClassroomDataGroup() {
        setSize(11);
        initialTable();
//        for (int i = 0; i < 11; i++) {
//            getNode(i).key = (K) Integer.valueOf(i + 1);
//        }
    }


    @Override
    public void addStudent(Student student) {
        switch (student.getGroup()) {
            case 1 -> getTable()[0].value.add(student);
            case 2 -> getTable()[1].value.add(student);
            case 3 -> getTable()[2].value.add(student);
            case 4 -> getTable()[3].value.add(student);
            case 5 -> getTable()[4].value.add(student);
            case 6 -> getTable()[5].value.add(student);
            case 7 -> getTable()[6].value.add(student);
            case 8 -> getTable()[7].value.add(student);
            case 9 -> getTable()[8].value.add(student);
            case 10 -> getTable()[9].value.add(student);
            case 11 -> getTable()[10].value.add(student);
        }
    }

    @Override
    public MyLinkedList<Student> getStudents(Integer key) {
        return getTable()[key - 1].value;
    }

    @Override
    protected void initialTable() {
        for (int i = 0; i < 11; i++) {
            getTable()[i] = (Node<K, V>) new Node<>(i + 1, new MyLinkedList<Student>());
        }
    }
}

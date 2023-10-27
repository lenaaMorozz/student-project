package com.mer.groups;

import com.mer.model.Student;

public class PersonAgeDataGroup extends DataGroup<Integer, MyLinkedList<Student>> {

    public PersonAgeDataGroup() {
        setSize(13); //в нашем случае возвраст от 5 до 17 лет
        initialTable();
    }

    @Override
    public void addStudent(Student student) {
        getTable()[student.getAge() - 5].value.add(student);
    }

    @Override
    public MyLinkedList<Student> getStudents(Integer key) {
        return getTable()[key - 5].value;
    }


    @Override
    protected void initialTable() {
        for (int i = 0; i < 13; i++) {
            getTable()[i] = new Node<>(i + 5, new MyLinkedList<>());
        }
    }
}

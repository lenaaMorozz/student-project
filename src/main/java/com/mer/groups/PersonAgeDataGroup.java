package com.mer.groups;

import com.mer.model.Student;

public class PersonAgeDataGroup extends DataGroupAbstr<Integer, MyLinkedList<Student>> {

    public PersonAgeDataGroup() {
        setSize(13); //в нашем случае возвраст от 5 до 17 лет
        initialTable();
    }

    @Override
    public void addStudent(Student student) {
        switch (student.getAge()) {
            case 5 -> getTable()[0].value.add(student);
            case 6 -> getTable()[1].value.add(student);
            case 7 -> getTable()[2].value.add(student);
            case 8 -> getTable()[3].value.add(student);
            case 9 -> getTable()[4].value.add(student);
            case 10 -> getTable()[5].value.add(student);
            case 11 -> getTable()[6].value.add(student);
            case 12 -> getTable()[7].value.add(student);
            case 13 -> getTable()[8].value.add(student);
            case 14 -> getTable()[9].value.add(student);
            case 15 -> getTable()[10].value.add(student);
            case 16 -> getTable()[11].value.add(student);
            case 17 -> getTable()[12].value.add(student);

        }
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

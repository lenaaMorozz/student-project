package com.mer.groups;

import com.mer.model.Student;

public class PersonNameDataGroup<K extends Character, V extends MyLinkedList<Student>> extends DataGroupAbstr<K, V> {

    public PersonNameDataGroup() {
        setSize(32); //сколько букв в алфавите(без Ё), не пропускаем буквы Ы, Ъ и тд, чтобы коды символов были по порядку
        initialTable();
    }

    @Override
    public void addStudent(Student student) {
        switch (student.getLastName().charAt(0)) {
            case 1040 -> getTable()[0].value.add(student);
            case 1041 -> getTable()[1].value.add(student);
            case 1042 -> getTable()[2].value.add(student);
            case 1043 -> getTable()[3].value.add(student);
            case 1044 -> getTable()[4].value.add(student);
            case 1045 -> getTable()[5].value.add(student);
            case 1046 -> getTable()[6].value.add(student);
            case 1047 -> getTable()[7].value.add(student);
            case 1048 -> getTable()[8].value.add(student);
            case 1050 -> getTable()[10].value.add(student);
            case 1051 -> getTable()[11].value.add(student);
            case 1052 -> getTable()[12].value.add(student);
            case 1053 -> getTable()[13].value.add(student);
            case 1054 -> getTable()[14].value.add(student);
            case 1055 -> getTable()[15].value.add(student);
            case 1056 -> getTable()[16].value.add(student);
            case 1057 -> getTable()[17].value.add(student);
            case 1058 -> getTable()[18].value.add(student);
            case 1059 -> getTable()[19].value.add(student);
            case 1060 -> getTable()[20].value.add(student);
            case 1061 -> getTable()[21].value.add(student);
            case 1062 -> getTable()[22].value.add(student);
            case 1063 -> getTable()[23].value.add(student);
            case 1064 -> getTable()[24].value.add(student);
            case 1065 -> getTable()[25].value.add(student);
            case 1069 -> getTable()[29].value.add(student);
            case 1070 -> getTable()[30].value.add(student);
            case 1071 -> getTable()[31].value.add(student);
            case 1072 -> getTable()[32].value.add(student);

        }
    }

    @Override
    public MyLinkedList<Student> getStudents(Character key) {
        return getTable()[key - 1040].value;
    }

    @Override
    protected void initialTable() {
        for (int i = 0; i < 32; i++) {
            getTable()[i] = (Node<K, V>) new Node<>((char) (i + 1040), new MyLinkedList<Student>());
        }
    }
}

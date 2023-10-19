package com.mer.groups;

import com.mer.Student;
import java.util.ArrayList;

public class ClassroomDataGroup<K extends Integer, V> implements DataGroup<K, V> {

    private final Node<Integer, ArrayList<Student>>[] table = new Node[11];

    {
        for (int i = 0; i < 11; i++) {
            table[i].key = i + 1;
        }
    }

    @Override
    public void addStudent(Student student) {
        switch (student.getGroup()) {
            case 1 -> table[0].value.add(student);
            case 2 -> table[1].value.add(student);
            case 3 -> table[2].value.add(student);
            case 4 -> table[3].value.add(student);
            case 5 -> table[4].value.add(student);
            case 6 -> table[5].value.add(student);
            case 7 -> table[6].value.add(student);
            case 8 -> table[7].value.add(student);
            case 9 -> table[8].value.add(student);
            case 10 -> table[9].value.add(student);
            case 11 -> table[10].value.add(student);
        }
    }

    @Override
    public Student[] getStudents(Integer key) {
        return get(key).toArray(Student[]::new);
    }

    private ArrayList<Student> get(Integer key) {
        Node<Integer, ArrayList<Student>> node = table[key - 1];
        return node.value;
    }

    static class Node<K, V> {
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

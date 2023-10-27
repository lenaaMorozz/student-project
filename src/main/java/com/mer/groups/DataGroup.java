package com.mer.groups;

import com.mer.model.Student;

/**
 *Для хранения данных выбор пал на свою упрощенную реализацию HashMap, в качестве ключей служат критерии, в качестве значений -
 * своя реализация LinkedList (односвязный), в котором хранятся студенты. Доступ к группам данных в этом случае будет
 * со сложностью О(1), коллизии исключены, так как мы заранее знаем значения ключей, они уникальны (реализацией не предусмотрено
 * создание двусвязного списка или красно-черного дерево в одном элементе HashMap).
 */

public abstract class DataGroup<K, V> {
    private Node<K, V>[] table;

    protected abstract void initialTable();

    public abstract void addStudent(Student student);
    public abstract V getStudents(K key);


    protected void setSize(int size) {
        table = new Node[size];
    }


    protected Node<K,V>[] getTable() {
        return table;
        }

    protected static class Node<K, V> {
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

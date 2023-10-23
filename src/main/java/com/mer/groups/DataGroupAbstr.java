package com.mer.groups;

public abstract class DataGroupAbstr<K, V> implements DataGroup<K, V> {
    private Node<K, V>[] table;

    protected abstract void initialTable();


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

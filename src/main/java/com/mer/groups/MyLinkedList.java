package com.mer.groups;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Выбор пал на реализацию LinkedList, потому что для наших задач нужно будет добавить много данных, в LinkedList
 * сложность будет О(1), в ArrayList пришлось бы постоянно увеличивать размер внутреннего массива,
 * иногда была бы сложность О(n), так как нужно все равно перебрать все элементы, то получить их можно с помощью итератора,
 * то есть в обоих случаях будет сложность О(n), но надо учитывать, что памяти в LinkedList придется затратить больше
 * для хранения ссылок.
 */
public class MyLinkedList<E> implements Iterable<E> {
    private int size = 0;
    private Node<E> header = null;

    public void add(E e) {
        Node<E> entry = new Node<>(e);
        if (header == null) {
            header = entry;
        } else {
            Node<E> currentNode = header;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = entry;
        }
        size++;
    }

    public E getFirst() {
        return header.element;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
            this.next = null;
        }
    }

    private class MyIterator implements Iterator<E> {
        private Node<E> currentNode = header;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E e = currentNode.element;
            currentNode = currentNode.next;
            return e;
        }
    }
}

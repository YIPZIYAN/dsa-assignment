/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.util.Iterator;

/**
 *
 * @author Yip Zi Yan
 */
public class CircularDoublyLinkedList<T> implements ListInterface<T> {

    private Node startNode;
    private int numberOfEntries;

    public CircularDoublyLinkedList() {
        startNode = null;
    }

    @Override
    public boolean add(T newEntry) {
        if (newEntry != null) {
            Node newNode = new Node(newEntry);
            if (!isEmpty()) {
                startNode.prev.next = newNode;
                newNode.prev = startNode.prev;
                newNode.next = startNode;
                startNode.prev = newNode;
            } else {
                newNode.next = newNode.prev = newNode;
                startNode = newNode;
            }
            numberOfEntries++;
        }

        return false;
    }

    @Override
    public boolean add(int index, T newEntry) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(T anEntry) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        if (!isEmpty()) {
            startNode = startNode.prev = startNode.prev.next = null;
            numberOfEntries = 0;
        }

    }

    @Override
    public boolean replace(int index, T newEntry) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T getEntry(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(T anEntry) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return startNode == null;
    }

    @Override
    public boolean isFull() { //no need
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int indexOf(T anEntry) {
        int index = -1;

        return index;
    }

    @Override
    public Iterator<T> getIterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<T> {

        private Node currentNode;

        public LinkedIterator() {
            if (!isEmpty()) {
                currentNode = startNode;
            }
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            T currentElement = null;
            if (hasNext()) {
                currentElement = currentNode.data;
                if (currentNode == startNode.prev) {
                    currentNode = null;
                } else {
                    currentNode = currentNode.next;
                }
            }
            return currentElement;
        }

    }

    private class Node {

        T data;
        Node prev;
        Node next;

        public Node() {
        }

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

    }

    @Override
    public String toString() {
        String str = "";
        if (!isEmpty()) {
            Node currentNode = startNode;
            do {
                str += currentNode.data + " ";
                currentNode = currentNode.next;
            } while (currentNode != startNode);
        }
        return "CircularDoublyLinkedQueue\n" + str;
    }

}

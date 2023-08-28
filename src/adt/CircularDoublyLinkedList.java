/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

/**
 *
 * @author Yip Zi Yan
 */
public class CircularDoublyLinkedList<T> implements ListInterface<T> {

    private Node startNode;
    private int numberOfEntries;

    public CircularDoublyLinkedList() {
        clear();
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
        numberOfEntries = 0;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isFull() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int indexOf(T anEntry) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}

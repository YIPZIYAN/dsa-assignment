package adt.exampleAdt;

import adt.exampleAdt.StackInterface;

/**
 * ArrayStack.java A class that implements the ADT Stack using an array.
 *
 * @author Frank M. Carrano
 * @version 2.0
 * @param <T>
 */
public class ArrayStack<T> implements StackInterface<T> {

    private T[] array;
    private int topIndex; // index of top entry
    private static final int DEFAULT_CAPACITY = 50;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayStack(int initialCapacity) {
        array = (T[]) new Object[initialCapacity];
        topIndex = -1;
    }

    @Override
    public void push(T newEntry) {
        if (!isDuplicate(newEntry)) {
                    topIndex++;
            if (topIndex < array.length) {
                array[topIndex] = newEntry;
            }
        }
        
    }

    @Override
    public T peek() {
        T top = null;

        if (!isEmpty()) {
            top = array[topIndex];
        }

        return top;
    }

    @Override
    public T pop() {
        T top = null;
        if (!isEmpty()) {
            top = array[topIndex];
            array[topIndex] = null;
            topIndex--;

        } // end if

        return top;
    }

    @Override
    public boolean isEmpty() {
        return topIndex < 0;
    }

    public void clear() {
        topIndex = -1;
    }

    @Override
    public String toString() {
        String output = "";
        if (!isEmpty()) {
            for (int i = 0; i <= topIndex; i++) {
                output += array[i] + " ";
            }
        }

        return output;
    }

    private boolean isDuplicate(T anEntry) {
        boolean isDuplicated = false;
        if (!isEmpty()) {
            for (int i = 0; i <= topIndex; i++) {
                if (array[i].equals(anEntry)) {
                    isDuplicated = true;
                }
            }
        }
        return isDuplicated;
    }
}

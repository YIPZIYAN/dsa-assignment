package adt;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author Yip Zi Yan & Goh Chun Yen
 */
public class CircularDoublyLinkedList<T> implements ListInterface<T>, Serializable {

    private Node startNode;
    private int numberOfEntries;

    public CircularDoublyLinkedList() {
        startNode = null;
        numberOfEntries = 0;
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
            return true;
        }

        return false;
    }

    @Override
    public boolean add(int index, T newEntry) {
        if (index < 0 || index >= numberOfEntries || newEntry == null) {
            return false;
        }

        Node newNode = new Node(newEntry);
        if (index == 0) {
            newNode.next = startNode;
            newNode.prev = startNode.prev;
            startNode.prev.next = newNode;
            startNode.prev = newNode;
            startNode = newNode;
        } else {
            Node currentNode = startNode;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.next;
            }
            newNode.next = currentNode.next;
            newNode.prev = currentNode;
            currentNode.next.prev = newNode;
            currentNode.next = newNode;
        }
        numberOfEntries++;
        return true;

    }

    @Override
    public boolean remove(T anEntry) {
        if (!isEmpty()) {
            Node currentNode = startNode;
            do {
                if (currentNode.data.equals(anEntry)) {
                    if (currentNode == startNode) {
                        if (numberOfEntries == 1) {
                            currentNode.prev = currentNode.next = null;
                            startNode = null;
                        } else {
                            startNode = currentNode.next;
                            currentNode.prev.next = startNode;
                            startNode.prev = currentNode.prev;
                        }
                    } else {
                        currentNode.prev.next = currentNode.next;
                        currentNode.next.prev = currentNode.prev;
                    }
                    numberOfEntries--;
                    return true;
                }
                currentNode = currentNode.next;
            } while (currentNode != startNode);

        }

        return false;
    }

    @Override
    public void clear() {
        if (!isEmpty()) {
            Node currentNode = startNode;
            do {
                Node nextNode = currentNode.next;
                currentNode.next = null;
                currentNode = nextNode;
            } while (currentNode != startNode);
            startNode = currentNode.prev = null;
        }

        numberOfEntries = 0;

    }

    @Override
    public boolean replace(int index, T newEntry) {

        if (index < 0 || index >= numberOfEntries || newEntry == null) {
            return false;
        }

        Node currentNode = startNode;

        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        currentNode.data = newEntry;
        return true;

    }

    @Override
    public T getEntry(int index) {
        T data = null;
        Node currentNode = startNode;

        if (index >= 0 && index < numberOfEntries) {
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
            data = currentNode.data;
        }

        return data;
    }

    @Override
    public boolean contains(T anEntry) {
        return indexOf(anEntry) == -1 ? false : true;
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
    public int indexOf(T anEntry) {
        Node currentNode = startNode;
        int index = 0;

        if (!isEmpty()) {
            do {
                if (currentNode.data.equals(anEntry)) {
                    return index;
                }
                currentNode = currentNode.next;
                index++;
            } while (currentNode != startNode);
        }

        return -1;
    }

    @Override
    public boolean addAll(ListInterface<T> listOfEntries) {
        if (listOfEntries.isEmpty()) {
            return false;
        }
        Iterator<T> it = listOfEntries.getIterator();
        while (it.hasNext()) {
            add(it.next());
        }
        return true;
    }

    @Override
    public T getFirstEntry() {
        return startNode.data;
    }

    @Override
    public T getLastEntry() {
        return startNode.prev.data;
    }

    @Override
    public ListInterface<T> subList(int beginIndex, int endIndex) {
        if (beginIndex < 0 || endIndex > numberOfEntries - 1 || beginIndex > endIndex) {
            return null;
        }

        ListInterface<T> subList = new CircularDoublyLinkedList<>();
        Node currentNode = startNode;

        //move to the begin index
        for (int i = 0; i < beginIndex; i++) {
            currentNode = currentNode.next;
        }

        //add until end index
        for (int i = beginIndex; i <= endIndex; i++) {
            subList.add(currentNode.data);
            currentNode = currentNode.next;
        }

        return subList;
    }

    @Override
    public void sortBy(Comparator<T> comparator, boolean isAscending) {
        if (isEmpty()) {
            return;
        }

        Node currentNode = startNode;
        Node index = null;
        T temp;

        do {
            index = currentNode.next;
            while (index != startNode) {
                int val = comparator.compare(currentNode.data, index.data);

                if (isAscending ? val > 0 : val < 0) {
                    temp = currentNode.data;
                    currentNode.data = index.data;
                    index.data = temp;
                }
                index = index.next;
            }
            currentNode = currentNode.next;
        } while (currentNode != startNode);

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

    private class Node implements Serializable {

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
        return "CircularDoublyLinkedList: " + str;
    }

}

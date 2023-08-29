/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.CircularDoublyLinkedList;
import adt.ListInterface;
import java.util.Iterator;

/**
 *
 * @author Yip Zi Yan
 */
public class TestCircularDoublyLinkedList {

    public static void main(String[] args) {
        ListInterface<Integer> list = new CircularDoublyLinkedList<>();

        System.out.println("Add to the list");
        System.out.println(list.add(12));
        System.out.println(list);
        System.out.println(list.add(13));
        System.out.println(list);
        System.out.println(list.add(14));
        System.out.println(list);
        System.out.println(list.add(15));
        System.out.println(list);

        System.out.println("");

        System.out.println("Remove from the list");
        System.out.println(list.remove(15));
        System.out.println(list);
        System.out.println(list.remove(13));
        System.out.println(list);
        System.out.println(list.remove(1)); //false
        System.out.println(list);
        System.out.println(list.remove(12));
        System.out.println(list);
        System.out.println(list.remove(14));
        System.out.println(list);

        System.out.println("");

        System.out.println("Add to the list");
        System.out.println(list.add(12));
        System.out.println(list);
        System.out.println(list.add(13));
        System.out.println(list);
        System.out.println(list.add(14));
        System.out.println(list);
        System.out.println(list.add(15));
        System.out.println(list);

        System.out.println("");

        System.out.println("Clear the list");
        list.clear();
        System.out.println(list);

        System.out.println("");

        System.out.println("Add to specific location the list");
        System.out.println(list.add(1, 12)); //false
        System.out.println(list);
        System.out.println(list.add(0, 12));
        System.out.println(list);
        System.out.println(list.add(1, 13));
        System.out.println(list);
        System.out.println(list.add(2, 14));
        System.out.println(list);
        System.out.println(list.add(3, 15));
        System.out.println(list);
        System.out.println(list.add(5, 99)); //false
        System.out.println(list);
        System.out.println(list.add(2, 22)); //true
        System.out.println(list);
        System.out.println(list.add(2, 44)); //true
        System.out.println(list);
        System.out.println(list.add(6, 33)); //true
        System.out.println(list);

        System.out.println("\nGet index");
        System.out.println(list.indexOf(12));

        System.out.println("\nContains");
        System.out.println(list.contains(1));
        System.out.println(list.contains(12));
        System.out.println(list.contains(13));

        System.out.println("\nGet entry");
        System.out.println(list.getEntry(0));
        System.out.println(list.getEntry(2));
        
        System.out.println("\nReplace");
        list.replace(3, 99);
        System.out.println(list);

        int sum = 0;
        Iterator<Integer> it = list.getIterator();
        while (it.hasNext()) {
            sum += it.next();
        }
        System.out.println(sum);

        list.clear();
        System.out.println("Get entry");
        System.out.println(list.getEntry(0));
        System.out.println(list.getEntry(2));

    }
}

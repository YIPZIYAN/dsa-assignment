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

        list.add(12);
        System.out.println(list);
        list.add(13);
        System.out.println(list);
        list.add(14);
        System.out.println(list);
        list.add(15);
        System.out.println(list);

        System.out.println(list);

        int sum = 0;
        Iterator<Integer> it = list.getIterator();
        while (it.hasNext()) {
            sum += it.next();
        }
        System.out.println(sum);

    }
}

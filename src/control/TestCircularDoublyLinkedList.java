/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.CircularDoublyLinkedList;
import adt.ListInterface;

/**
 *
 * @author Yip Zi Yan
 */
public class TestCircularDoublyLinkedList {

    public static void main(String[] args) {
        ListInterface<Integer> list = new CircularDoublyLinkedList<>();

        list.add(12);
        list.add(13);
        list.add(14);
        list.add(15);

        System.out.println(list);
        list.clear();
        System.out.println(list);


    }
}

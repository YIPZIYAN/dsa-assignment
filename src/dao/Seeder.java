/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.CircularDoublyLinkedList;
import adt.ListInterface;
import entity.Tutor;

/**
 *
 * @author Yip Zi Yan
 *
 * Used to initialize fake data
 *
 */
public class Seeder {

    ListInterface<Tutor> tutorList = new CircularDoublyLinkedList<>();

    public Seeder() {
        tutorList.add(new Tutor("Yip Zi Yan",'M','P'));
        tutorList.add(new Tutor("Goh Chun Yen",'M','F'));
        tutorList.add(new Tutor("Chew Lip Sin",'M','F'));
        tutorList.add(new Tutor("Lim Yi Leong",'M','F'));
        tutorList.add(new Tutor("Tan Li Li",'F','P'));
        tutorList.add(new Tutor("Chan Mei Mei",'F','P'));
        tutorList.add(new Tutor("Ji Tai Mei",'F','P'));
    }

    public ListInterface<Tutor> getTutorList() {
        return tutorList;
    }
    
    

}

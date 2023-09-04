/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.CircularDoublyLinkedList;
import adt.ListInterface;
import dao.CourseSeeder;
import entity.Course;
import java.util.Comparator;
import java.util.Iterator;
import utility.Paginator;

/**
 *
 * @author Yip Zi Yan
 */
public class TestCircularDoublyLinkedList {

    public static void main(String[] args) {
        ListInterface<Course> list = new CircularDoublyLinkedList<>();
        CourseSeeder seeder = new CourseSeeder();
        list = seeder.getCourseList();
        list.sortBy(Comparator.comparing(Course::getCourseCode));
        for (int i = 0; i < list.getNumberOfEntries(); i++) {
            System.out.println(list.getEntry(i).getCourseCode());
        }
        list.sortBy(Comparator.comparing(Course::getCourseName));
        for (int i = 0; i < list.getNumberOfEntries(); i++) {
            System.out.println(list.getEntry(i).getCourseName());
        }
        list.sortBy(Comparator.comparing(Course::getCourseCreditHours));
        for (int i = 0; i < list.getNumberOfEntries(); i++) {
            System.out.println(list.getEntry(i).getCourseCreditHours());
        }
                list.sortBy(Comparator.comparing(Course::getCourseFees));
        for (int i = 0; i < list.getNumberOfEntries(); i++) {
            System.out.println(list.getEntry(i).getCourseFees());
        }

//        list.clear();
//        System.out.println("Get entry");
//        System.out.println(list.getEntry(0));
//        System.out.println(list.getEntry(2));
        System.out.println(list);
        System.out.println(list.subList(0, 2));
        Paginator page = new Paginator(list, 3);

        System.out.println(page.jumpTo(0));

    }
}

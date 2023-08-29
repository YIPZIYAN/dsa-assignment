/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import boundary.CourseManagementUI;
import utility.MessageUI;
import adt.*;
import entity.Course;

/**
 *
 * @author Goh Chun Yen
 */
public class CourseManagement {
    
    private ListInterface<Course> courseList = new CircularDoublyLinkedList<>();
    private CourseManagementUI courseUI = new CourseManagementUI();

    public void startUI() {
        
        int choice;
        do {
            choice = courseUI.getMenuChoice();
            switch (choice) {
                case 1:
                    //addCourse();
                    //courseUI.listAllCourse(getAllCourses());
                    break;
                case 0:
                    MessageUI.displayExitMessage();
            }
        } while (choice != 0);

    }

    public String getAllCourses() {
        String outputStr = "";
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            outputStr += courseList.getEntry(i) + "\n";
        }
        return outputStr;
    }
    
    public static void main(String[] args) {
        CourseManagement courseControl = new CourseManagement();
        courseControl.startUI();
    }
}

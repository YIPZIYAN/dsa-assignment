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
import utility.GeneralUtil;

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
                    courseUI.listAllCourses(getAllCourses());
                    break;
                case 2:
                    addNewCourse();
                    break;
                case 0:
                    MessageUI.displayExitMessage();
            }
        } while (choice != 0);

    }

    private void addNewCourse() {
        //pass courseList to compare courseCode
        Course newCourse = courseUI.addCourse(courseList);

        if (newCourse == null) {
            return;
        }

        courseList.add(newCourse);
        //System.out.println(newCourse);
        GeneralUtil.systemPause();
    }

    public String getAllCourses() {
        String outputStr = "";
        
        for (int i = 0; i < courseList.getNumberOfEntries(); i++) {
            outputStr += i + 1 + ". ";
            outputStr += courseList.getEntry(i) + "\n";
        }
        
        return outputStr;
    }

    public static void main(String[] args) {
        CourseManagement courseControl = new CourseManagement();
        courseControl.startUI();
    }
}

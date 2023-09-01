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
    private static ListInterface<Programme> programmeList = new CircularDoublyLinkedList<>();
    
    private static Programme[] programmes = {
        new Programme("RSD", "Bachelor of Computer Science in Data Science", "Bachelor Degree"),
        new Programme("DCS", "Diploma in Computer Science", "Diploma"),
        new Programme("RAC", "Bachelor of Accounting", "Bachelor Degree"),
        new Programme("REE", "Bachelor of Electronics Engineering Technology", "Bachelor Degree"),
        new Programme("RFS", "Bachelor of Science in Food Science", "Bachelor Degree"),
        new Programme("RRE", "Bachelor of Real Estate Management", "Bachelor Degree"),
        new Programme("RMS", "Bachelor of Communication in Media Studies", "Bachelor Degree"),
        new Programme("FIS", "Foundation in Science", "Foundation")
    };

    public CourseManagement() {
        courseList = productDAO.retrieveFromFileCourse();
    }

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
                case 3:
                    findCourse();
                    break;
                case 4:
                    editCourseDetails();
                    break;
                case 5:
                    removeCourse();
                    break;
                case 6:
                    addProgrammeToCourse();
                    break;
                case 7:
                    removeProgrammeFromCourse();
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
    
    private void findCourse() {
        courseUI.findCourse(courseList, programmeList, getProgrammeList());
    }

    private void editCourseDetails() {
        Course newCourse = (Course) courseUI.editCourse(courseList);

        if (newCourse == null) {
            return;
        }

        for (int i = 0; i < courseList.getNumberOfEntries(); i++) {
            if (newCourse.getCourseCode().equals(courseList.getEntry(i).getCourseCode())) {
                courseList.setEntry(i, newCourse);
                productDAO.saveToFileCourse(courseList);
                GeneralUtil.systemPause();
                return;
            }
        }
    }

    private void removeCourse() {
        Course courseFound = courseUI.removeCourse(courseList);
        if (courseFound == null) {
            return;
        }
        courseList.remove(courseFound);
        productDAO.saveToFileCourse(courseList);
        GeneralUtil.systemPause();
    }

    private void addProgrammeToCourse() {
        Object[] obj = courseUI.addProgrammeToCourse(courseList, programmeList, getProgrammeList());

        if (obj == null) {
            return;
        }

        Course courseFound = (Course) obj[0];
        ListInterface<Programme> programmeAdded = (ListInterface<Programme>) obj[1];

        for (int i = 0; i < courseList.getNumberOfEntries(); i++) {
            if (courseList.getEntry(i).equals(courseFound)) {
                for (int j = 0; j < programmeAdded.getNumberOfEntries(); j++) {
                    courseList.getEntry(i).getProgrammes().add(programmeAdded.getEntry(j));
                }
            }
        }
        productDAO.saveToFileCourse(courseList);
        GeneralUtil.systemPause();

    }

    private void removeProgrammeFromCourse() {
        Object[] obj = courseUI.removeProgrammeFromCourse(courseList);
        if (obj == null) {
            return;
        }
        Course courseFound = (Course) obj[0];
        ListInterface<Programme> programmeRemoved = (ListInterface<Programme>) obj[1];

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

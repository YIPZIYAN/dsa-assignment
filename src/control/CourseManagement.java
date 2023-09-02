/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import boundary.CourseManagementUI;
import utility.MessageUI;
import adt.*;
import dao.*;
import entity.*;
import utility.GeneralUtil;

/**
 *
 * @author Goh Chun Yen
 */
public class CourseManagement {

    private ListInterface<Course> courseList = new CircularDoublyLinkedList<>();
    private DAO dAO = new DAO("course.dat");
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
        courseList = dAO.retrieveFromFile();
    }

    public void startUI() {

        int choice;
        do {
            choice = courseUI.getMenuChoice();
            switch (choice) {
                case 1:
                    getAllCourses();
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

    public void getAllCourses() {
        String outputStr = "";

        for (int i = 0; i < courseList.getNumberOfEntries(); i++) {

            if (i + 1 < 10) {
                outputStr += i + 1 + ".  ";
            } else {
                outputStr += i + 1 + ". ";
            }
            outputStr += courseList.getEntry(i) + "\n\n";
        }

        courseUI.listAllCourses(outputStr);
    }

    private void addNewCourse() {
        //pass courseList to compare courseCode
        Course newCourse = courseUI.addCourse(courseList);

        if (newCourse == null) {
            return;
        }

        courseList.add(newCourse);
        dAO.saveToFile(courseList);
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
                dAO.saveToFile(courseList);
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
        dAO.saveToFile(courseList);
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
        dAO.saveToFile(courseList);
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
            if (courseList.getEntry(i).equals(courseFound)) {
                for (int j = 0; j < programmeRemoved.getNumberOfEntries(); j++) {
                    courseList.getEntry(i).getProgrammes().remove(programmeRemoved.getEntry(j));
                }
            }
        }
        dAO.saveToFile(courseList);
        GeneralUtil.systemPause();
    }

    private String getProgrammeList() {
        String outputStr = "";
        for (int i = 0; i < programmeList.getNumberOfEntries(); i++) {
            outputStr += programmeList.getEntry(i) + "\n";
        }

        return outputStr;

    }

    public static void main(String[] args) {
        programmeList.addAll(programmes);
        CourseManagement courseControl = new CourseManagement();
        courseControl.startUI();
    }
}

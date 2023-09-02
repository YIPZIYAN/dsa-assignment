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
import java.util.Iterator;
import utility.GeneralUtil;

/**
 *
 * @author Goh Chun Yen
 */
public class CourseManagement {

    private ListInterface<Course> courseList = new CircularDoublyLinkedList<>();
    private DAO dAO = new DAO("courses.dat");
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
                    editCourse();
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

        Iterator<Course> it = courseList.getIterator();
        int num = 0;

        while (it.hasNext()) {
            outputStr += String.format("%2d. ", ++num) + it.next() + "\n\n";
        }

        courseUI.listAllCourses(outputStr);
    }

    private void addNewCourse() {
        //pass courseList to compare repeat courseCode

        do {

            String newCourseCode = courseUI.getNewCourseCode();

            Course newCourse = courseUI.addCourse(courseIsExist(newCourseCode), newCourseCode);

            if (newCourse != null) {
                courseList.add(newCourse);
                dAO.saveToFile(courseList);
            }

        } while (courseUI.repeatAction("Anymore course to add? [Y|N] > "));

    }

    private void findCourse() {
        ListInterface<Course> searchResults;
        do {
            int choice = courseUI.findCourse();

            switch (choice) {
                case 1:
                    do {
                        searchResults = findStringResults(courseUI.findByCourseCode(), choice);
                        courseUI.displaySearchResults(getAllSearchResults(searchResults));
                    } while (courseUI.repeatAction("Continue to find by course code? [Y|N] > "));
                    break;
                    
                case 2:
                    do {
                        searchResults = findStringResults(courseUI.findByCourseName(), choice);
                        courseUI.displaySearchResults(getAllSearchResults(searchResults));
                    } while (courseUI.repeatAction("Continue to find by course name? [Y|N] > "));
                    break;
                    
                case 3:
                    do {
                        searchResults = findIntResults(courseUI.findByCreditHours());
                        courseUI.displaySearchResults(getAllSearchResults(searchResults));
                    } while (courseUI.repeatAction("Continue to find by course credit hours? [Y|N] > "));
                    break;
                    
                case 4:
                    do {
                        searchResults = findDoubleResults(courseUI.findByCourseFees());
                        courseUI.displaySearchResults(getAllSearchResults(searchResults));
                    } while (courseUI.repeatAction("Continue to find by course fees? [Y|N] > "));
                    break;
                case 5:
                    
                    do {
                        searchResults = findStringResults(courseUI.findByCourseDept(), choice);
                        courseUI.displaySearchResults(getAllSearchResults(searchResults));
                    } while (courseUI.repeatAction("Continue to find by course department? [Y|N] > "));
                    break;

                case 6:
                    do {
                        searchResults = findStringResults(courseUI.findByProgramme(programmeList), choice);
                        courseUI.displaySearchResults(getAllSearchResults(searchResults));
                    } while (courseUI.repeatAction("Continue to find by programme? [Y|N] > "));

                    break;
            }
        } while (courseUI.repeatAction("Anymore course to find? [Y|N] > "));

    }

    private ListInterface<Course> findDoubleResults(double[] input) {
        ListInterface<Course> searchResults = new CircularDoublyLinkedList<>();
        Iterator<Course> it = courseList.getIterator();

        while (it.hasNext()) {
            Course course = it.next();
            //input[0] = min & input[1] = max
            if (course.getCourseFees() >= input[0] && course.getCourseFees() <= input[1]) {
                searchResults.add(course);
            }

        }

        return searchResults;
    }

    private ListInterface<Course> findIntResults(int input) {
        ListInterface<Course> searchResults = new CircularDoublyLinkedList<>();
        Iterator<Course> it = courseList.getIterator();

        while (it.hasNext()) {
            Course course = it.next();

            if (course.getCourseCreditHours() == input) {
                searchResults.add(course);
            }

        }

        return searchResults;
    }

    private ListInterface<Course> findStringResults(String input, int choice) {
        ListInterface<Course> searchResults = new CircularDoublyLinkedList<>();
        Iterator<Course> it = courseList.getIterator();

        if (input.isBlank()) {
            return searchResults;
        }

        while (it.hasNext()) {
            switch (choice) {
                case 1: //course code
                    if (it.next().getCourseCode().contains(input)) {
                        searchResults.add(it.next());
                    }
                    break;
                case 2: //course name
                    if (it.next().getCourseName().contains(input)) {
                        searchResults.add(it.next());
                    }
                    break;
                case 5: //course department
                    if (it.next().getCourseDepartment().contains(input)) {
                        searchResults.add(it.next());
                    }
                    break;
                case 6: //course programme
                    Iterator<Programme> pIt = it.next().getProgrammes().getIterator();
                    boolean isExist = false;
                    while (pIt.hasNext()) {
                        if (pIt.next().getProgrammeCode().equals(input)) {
                            isExist = true;
                        }
                    }
                    if (isExist) {
                        searchResults.add(it.next());
                    }
                    break;
            }

        }

        return searchResults;
    }

    private boolean courseIsExist(String input) {
        Iterator<Course> it = courseList.getIterator();
        while (it.hasNext()) {
            if (it.next().getCourseCode().equals(input)) {
                return true;
            }
        }
        return false;
    }

    private Course getCourse(String courseCode) {
        Iterator<Course> it = courseList.getIterator();
        while (it.hasNext()) {
            Course course = it.next();
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    private void editCourse() {

        do {
            int choice;
            boolean loop;
            Course curCourse = new Course();
            String courseCode = courseUI.editCourseMenu();
            choice = courseUI.getEditChoice(courseIsExist(courseCode), getCourse(courseCode));
            if (choice != -1 || choice != 0) {
                curCourse = getCourse(courseCode);
            }

            switch (choice) {
                case 0:
                    return;
                case 1:
                    do {
                        loop = false;
                        String editCourseCode = courseUI.getEditString(choice);

                        if (courseIsExist(editCourseCode) || editCourseCode.equals(courseCode)) {
                            loop = courseUI.displayErrMsg(choice);
                        } else {
                            if (courseUI.repeatAction("Are you sure to edit the course code? [Y|N] > ")) {
                                courseUI.displaySucMsg(choice);
                                curCourse.setCourseCode(editCourseCode);
                                dAO.saveToFile(courseList);
                            }
                        }
                    } while (loop);
                    break;

                case 2:
                    do {
                        loop = false;
                        String editCourseName = courseUI.getEditString(choice);

                        if (editCourseName.equals(editCourseName)) {
                            loop = courseUI.displayErrMsg(choice);
                        } else {
                            if (courseUI.repeatAction("Are you sure to edit the course name? [Y|N] > ")) {
                                courseUI.displaySucMsg(choice);
                                curCourse.setCourseName(editCourseName);
                                dAO.saveToFile(courseList);
                            }
                        }
                    } while (loop);
                    break;
                    
                case 3:
                    
                case -1:
                    break;
            }

        } while (courseUI.repeatAction("Anymore course to edit? [Y|N] > "));

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

        return outputStr;
    }

    private String getAllSearchResults(ListInterface<Course> searchResults) {
        String outputStr = "";

        if (searchResults.isEmpty()) {
            return null;
        }

        Iterator<Course> it = searchResults.getIterator();
        int i = 0;

        while (it.hasNext()) {
            outputStr += String.format("%2d. ", ++i) + it.next() + "\n\n";
        }

        return outputStr;

    }

    public static void main(String[] args) {
        programmeList.addAll(programmes);
        CourseManagement courseControl = new CourseManagement();
        courseControl.startUI();
    }
}

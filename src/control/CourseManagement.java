/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import boundary.CourseManagementUI;
import utility.MessageUI;
import adt.*;
import adt.exampleAdt.SortedListInterface;
import adt.exampleAdt.SortedLinkedList;
import dao.*;
import entity.*;
import java.util.Iterator;
import utility.GeneralUtil;
import utility.Paginator;

/**
 *
 * @author Goh Chun Yen
 */
public class CourseManagement {

    private ListInterface<Course> courseList = new CircularDoublyLinkedList<>();
    private DAO dAO = new DAO("courses.dat");
    private CourseManagementUI courseUI = new CourseManagementUI();

    ProgrammeSeeder pSeeder = new ProgrammeSeeder();
    CourseSeeder cSeeder = new CourseSeeder();
    ListInterface<Programme> programmeList = pSeeder.getProgrammeList();

    public CourseManagement() {
        dAO.saveToFile(cSeeder.getCourseList());
        courseList = dAO.retrieveFromFile();
        Course.setTotalCourse(courseList.getNumberOfEntries());
    }

    public static void main(String[] args) {
        CourseManagement courseControl = new CourseManagement();
        courseControl.startUI();
    }

    private void startUI() {

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
                case 8:
                    generateReportMenu();
                    break;
                case 0:
                    MessageUI.displayExitMessage();
            }
        } while (choice != 0);

    }

    private void getAllCourses() {
        String outputStr = "";

        Iterator<Course> it = courseList.getIterator();
        int num = 0;

        while (it.hasNext()) {
            outputStr += String.format("%2d. ", ++num) + it.next() + "\n\n";
        }

        courseUI.listAllCourses(outputStr);
    }

    private void addNewCourse() {
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

                        if (courseIsExist(editCourseCode) || editCourseCode.equals(curCourse.getCourseCode())) {
                            loop = courseUI.displayEditErrMsg(choice);
                        } else {
                            if (courseUI.repeatAction("Are you sure to edit the course code? [Y|N] > ")) {
                                courseUI.displayEditSucMsg(choice);
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

                        if (editCourseName.equals(curCourse.getCourseName())) {
                            loop = courseUI.displayEditErrMsg(choice);
                        } else {
                            if (courseUI.repeatAction("Are you sure to edit the course name? [Y|N] > ")) {
                                courseUI.displayEditSucMsg(choice);
                                curCourse.setCourseName(editCourseName);
                                dAO.saveToFile(courseList);
                            }
                        }
                    } while (loop);
                    break;

                case 3:
                    do {
                        loop = false;
                        double editCourseFees = courseUI.getEditDouble();

                        if (editCourseFees == curCourse.getCourseFees()) {
                            loop = courseUI.displayEditErrMsg(choice);
                        } else {
                            if (courseUI.repeatAction("Are you sure to edit the course fees? [Y|N] > ")) {
                                courseUI.displayEditSucMsg(choice);
                                curCourse.setCourseFees(editCourseFees);
                                dAO.saveToFile(courseList);
                            }
                        }
                    } while (loop);
                    break;

                case 4:
                    do {
                        loop = false;
                        String editCourseDept = courseUI.getEditString(choice);

                        if (editCourseDept.equals(curCourse.getCourseDepartment())) {
                            loop = courseUI.displayEditErrMsg(choice);
                        } else {
                            if (courseUI.repeatAction("Are you sure to edit the course department? [Y|N] > ")) {
                                courseUI.displayEditSucMsg(choice);
                                curCourse.setCourseName(editCourseDept);
                                dAO.saveToFile(courseList);
                            }
                        }
                    } while (loop);
                    break;

                case -1:
                    break;
            }

        } while (courseUI.repeatAction("Anymore course to edit? [Y|N] > "));

    }

    private void removeCourse() {
        do {
            String courseCode = courseUI.removeCourse();
            if (courseIsExist(courseCode)) {
                Course courseFound = getCourse(courseCode);
                courseUI.displayCourseInformation(courseFound);
                courseUI.displayOriProgrammeInCourse(courseFound);
                if (courseUI.repeatAction("Are you sure to remove the course? [Y|N] > ")) {
                    courseUI.displayRmvMsg(true);
                    courseList.remove(courseFound);
                    dAO.saveToFile(courseList);
                }
            } else {
                courseUI.displayRmvMsg(false);
            }

        } while (courseUI.repeatAction("Anymore course to remove? [Y|N] > "));

    }

    private void addProgrammeToCourse() {
        do {
            String courseCode = courseUI.addProgrammeToCourse();
            if (courseIsExist(courseCode)) {
                Course courseFound = getCourse(courseCode);
                boolean isRepeat;
                do {
                    courseUI.displayCourseInformation(courseFound);
                    courseUI.displayOriProgrammeInCourse(courseFound);
                    String programmeCode = courseUI.getProgrammeCode(programmeList);
                    Iterator<Programme> it = courseFound.getProgrammes().getIterator();
                    boolean isExist = false;
                    while (it.hasNext()) {
                        if (it.next().getProgrammeCode().equals(programmeCode)) {
                            isExist = true;
                        }
                    }
                    if (!isExist) {
                        if (courseUI.repeatAction("Are you sure to add the programme to course? [Y|N] > ")) {

                            for (int i = 0; i < programmeList.getNumberOfEntries(); i++) {
                                if (programmeList.getEntry(i).getProgrammeCode().equals(programmeCode)) {
                                    courseFound.getProgrammes().add(programmeList.getEntry(i));
                                    dAO.saveToFile(courseList);
                                    break;
                                }
                            }

                            courseUI.displayAddProgrammeMsg(true);
                        }
                    } else {
                        courseUI.displayAddProgrammeMsg(false);
                    }

                    isRepeat = courseUI.repeatAction("Anymore programme to add? [Y|N] > ");
                    if (isRepeat) {
                        GeneralUtil.clearScreen();
                    }
                } while (isRepeat);

            } else {
                courseUI.displayRmvMsg(false); //same output so use the function
            }
        } while (courseUI.repeatAction("Anymore course to add programme? [Y|N] > "));

    }

    private void removeProgrammeFromCourse() {
        do {
            String courseCode = courseUI.removeProgrammeFromCourse();
            if (courseIsExist(courseCode)) {
                Course courseFound = getCourse(courseCode);

                boolean isRepeat;
                do {
                    isRepeat = false;

                    courseUI.displayCourseInformation(courseFound);
                    courseUI.displayOriProgrammeInCourse(courseFound);
                    String programmeCode = courseUI.getRemoveProgrammeCode();
                    Iterator<Programme> it = courseFound.getProgrammes().getIterator();
                    boolean isExist = false;
                    while (it.hasNext()) {
                        if (it.next().getProgrammeCode().equals(programmeCode)) {
                            isExist = true;
                        }
                    }
                    if (isExist) {
                        if (courseUI.repeatAction("Are you sure to remove the programme from the course? [Y|N] > ")) {

                            for (int i = 0; i < courseFound.getProgrammes().getNumberOfEntries(); i++) {
                                if (courseFound.getProgrammes().getEntry(i).getProgrammeCode().equals(programmeCode)) {
                                    courseFound.getProgrammes().remove(courseFound.getProgrammes().getEntry(i));
                                    dAO.saveToFile(courseList);
                                    break;
                                }
                            }

                            courseUI.displayRmvProgrammeMsg(true);
                        }
                    } else {
                        courseUI.displayRmvProgrammeMsg(false);
                    }

                    isRepeat = courseUI.repeatAction("Anymore programme to remove? [Y|N] > ");
                    if (isRepeat) {
                        GeneralUtil.clearScreen();
                    }

                } while (isRepeat);

            } else {
                courseUI.displayRmvMsg(false); //same output so use the function
            }
        } while (courseUI.repeatAction("Anymore course to remove programme? [Y|N] > "));

    }

    private void generateReportMenu() {
        int choice = courseUI.reportMenu();
        if (choice == 1) {
            int sortingChoice = courseUI.courseReportMenu();
            switch (sortingChoice) {
                case 1:
                    SortedListInterface<Course> sortedList = new SortedLinkedList<>();
                    Iterator<Course> it = courseList.getIterator();
                    while (it.hasNext()) {
                        sortedList.add(it.next());
                    }
                    generateReport((ListInterface<Course>) sortedList);
                    GeneralUtil.systemPause();
            }
        } else {

        }
    }

    private void generateReport(ListInterface<Course> courseList) {
        Paginator page = new Paginator(courseList, 7);
        String currentPage = getPageContent(page.jumpTo(0));
        String choice;
        do {

            if (currentPage == "") {
                currentPage = getPageContent(page.jumpTo(page.currentPage));
            }

            courseUI.displayAllCourse(currentPage, false);

            System.out.printf("Page No: %-73d < 1 .. %d >\n",
                    page.currentPage + 1, page.pageNumber);
            if (page.isEndOfPage()) {
                MessageUI.displayInfoMessage(String.format("%52s", "END OF PAGES"));
            }

            choice = courseUI.generateCourseReportMenu().toLowerCase();
            switch (choice) {
                case ">":
                    currentPage = getPageContent(page.nextPage());
                    break;
                case ">|":
                    currentPage = getPageContent(page.toEnd());
                    break;
                case "<":
                    currentPage = getPageContent(page.prevPage());
                    break;
                case "|<":
                    currentPage = getPageContent(page.toStart());
                    break;
                default:
                    if (choice.matches("[0-9]+")) { // is integer
                        currentPage = getPageContent(page.jumpTo(Integer.parseInt(choice) - 1));
                    } else if (!choice.equals("exit")) {
                        System.err.println("Invalid command.");
                        GeneralUtil.systemPause();
                    }
                    break;
            }

        } while (!choice.equals("exit"));
    }

    private String getPageContent(ListInterface<Course> list) {
        String outputStr = "";
        try {
            int number = courseList.indexOf(list.getFirstEntry());
            Iterator<Course> it = list.getIterator();
            while (it.hasNext()) {
                outputStr += String.format("%2d. ", ++number)
                        + it.next() + "\n\n";

            }
        } catch (Exception e) {
            GeneralUtil.systemPause();
        }

        return outputStr;
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

}

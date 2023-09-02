/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import adt.CircularDoublyLinkedList;
import adt.ListInterface;
import entity.*;
import java.util.Iterator;
import utility.*;

/**
 *
 * @author Goh Chun Yen
 */
public class CourseManagementUI {

    CustomScanner cScan = new CustomScanner();

    public int getMenuChoice() {

        GeneralUtil.clearScreen();
        System.out.println("Course Management");
        System.out.println("-------------------------------");
        System.out.println(
                  "1. Course List\n"
                + "2. Add Course\n"
                + "3. Find Course\n"
                + "4. Edit Course Details\n"
                + "5. Remove Course\n"
                + "6. Add Programme to Course\n"
                + "7. Remove Programme from Course\n"
                + "8. Generate Reports\n"
                + "0. Quit");
        System.out.println("-------------------------------");

        int choice = cScan.inputInt("Enter Selection > ", 0, 8);
        return choice;
    }

    public void listAllCourses(String outputStr) {
        GeneralUtil.clearScreen();

        System.out.println("Course List: \n"
                + "============================================================================================\n"
                + "No  Course Code Name                                         Credit Hours Department Fees\n"
                + "============================================================================================\n"
                + outputStr);

        GeneralUtil.systemPause();
    }

    public String getNewCourseCode() {
        GeneralUtil.clearScreen();

        System.out.println("Add Course");
        System.out.println("----------");
        return cScan.inputCourseCode("Enter Course Code > ", "Invalid course code format. [Eg: AACS1234]");
    }

    public Course addCourse(boolean courseIsExist, String courseCode) {

        if (courseIsExist) {
            System.err.println("The course code is already exist.");
            return null;
        }
        // var to store data
        String courseName, courseDepartment;
        int courseCreditHours;
        double courseFees;

        boolean loop;
        do {
            loop = false;
            courseName = cScan.inputString("Enter Course Name > ");
            if (courseName.isBlank()) {
                System.err.println("Course name cannot be blank.");
                loop = true;
            }
        } while (loop);

        courseCreditHours = getCreditHour(courseCode);

        courseFees = cScan.inputDouble("Enter Course Fees > ", 0.00, 9999.99);

        courseDepartment = getCourseDept();

        if (cScan.confimation("Are you sure to add new course? [Y|N] > ")) {
            System.out.println("New course successfully added.");
            return new Course(courseCode, courseName, courseCreditHours, courseDepartment, courseFees);
        }

        return null;
    }

    public String findByCourseCode() {
        GeneralUtil.clearScreen();
        System.out.println("Find Course By Course Code");
        System.out.println("--------------------------");
        return cScan.inputString("Enter Course Code > ");
    }

    public String findByCourseName() {
        GeneralUtil.clearScreen();
        System.out.println("Find Course By Course Name");
        System.out.println("--------------------------");
        return cScan.inputString("Enter Course Name > ");
    }

    public int findByCreditHours() {
        GeneralUtil.clearScreen();
        System.out.println("Find Course By Credit Hours");
        System.out.println("---------------------------");
        return cScan.inputInt("Enter Credit Hours > ");
    }

    public double[] findByCourseFees() {
        GeneralUtil.clearScreen();
        double min, max;
        System.out.println("Find Course By Course Fees");
        System.out.println("--------------------------");
        min = cScan.inputDouble("Enter minimum course fees > ", 0.0, 9999.99);
        max = cScan.inputDouble("Enter maximum course fees > ", min, 9999.99);
        double[] input = {min, max};
        return input;
    }

    public String findByCourseDept() {
        GeneralUtil.clearScreen();
        System.out.println("Find Course By Department");
        System.out.println("-------------------------");
        return getCourseDept();
    }

    public String findByProgramme(ListInterface<Programme> programmeList) {
        GeneralUtil.clearScreen();
        System.out.println("Find Course By Programme");
        System.out.println("------------------------");
        return getProgrammeCode(programmeList);
    }

    public void displaySearchResults(String outputStr) {
        if (outputStr == null) {
            System.err.println("No results found.");
        } else {
            System.out.println("\nSearch Results: \n"
                    + "============================================================================================\n"
                    + "No  Course Code Name                                         Credit Hours Department Fees\n"
                    + "============================================================================================\n"
                    + outputStr);
        }

    }

    public String editCourseMenu() {
        GeneralUtil.clearScreen();
        System.out.println("Edit Course");
        System.out.println("-----------");
        return cScan.inputString("Enter Course Code > ");
    }

    public String getEditString(int choice) {
        switch (choice) {
            case 1:
                return cScan.inputCourseCode("Enter new course code > ", "Invalid course code format. [Eg: AACS1234]");
            case 2:
                return cScan.inputString("Enter new course name > ");
            case 4:
                return getCourseDept();
        }

        return null;
    }

    public double getEditDouble() {
        return cScan.inputDouble("Enter new course fees > ", 0.00, 9999.99);
    }

    public void displaySucMsg(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Course code has been successfully edited.");
                break;
            case 2:
                System.out.println("Course name has been successfully edited.");
                break;
            case 3:
                System.out.println("Course fees has been successfully edited.");
                break;
            case 4:
                System.out.println("Course department has been successfully edited.");
                break;
        }
    }

    public boolean displayErrMsg(int choice) {

        switch (choice) {
            case 1:
                System.err.println("New course code is already exists or cannot same with original course code.");
                return repeatAction("Try Again ? [Y|N] > ");
            case 2:
                System.err.println("New course name cannot empty or same with original course name.");
                return repeatAction("Try Again ? [Y|N] > ");
            case 3:
                System.err.println("New course fees cannot same with original course fees.");
                return repeatAction("Try Again ? [Y|N] > ");
            case 4:
                System.err.println("New course department cannot same with original course department.");
                return repeatAction("Try Again ? [Y|N] > ");

        }
        return false;

    }

    public Course removeCourse(ListInterface<Course> courseList) {
        GeneralUtil.clearScreen();

        String courseCode;

        System.out.println("Remove Course");
        System.out.println("-------------");
        boolean cont;
        do {
            cont = false;
            char[] checkChar = {'Y', 'N'};
            char c;
            courseCode = cScan.inputString("Enter Course Code > ");
            Course courseFound = new Course();
            //Course courseFound = courseIsExist(courseList, courseCode);
            if (courseFound == null) {
                System.err.println("Course not found.");
                c = cScan.inputChar("Continue to remove? [Y = yes N = no] > ", "Please enter [Y] or [N] only.", checkChar);
                if (c == 'Y' || c == 'y') {
                    cont = true;
                    System.out.println("");
                }
            } else {
                displayCourseInformation(courseFound);
                displayOriProgrammeInCourse(courseFound);
                c = cScan.inputChar("Are you sure to remove the course? [Y = yes N = no] > ", "Please enter [Y] or [N] only.", checkChar);
                if (c == 'Y' || c == 'y') {
                    System.out.println("Course has been removed successfully.");

                    return courseFound;
                }

            }

        } while (cont);

        return null;
    }

    public Object[] addProgrammeToCourse(ListInterface<Course> courseList, ListInterface<Programme> programmeList, String programmeListOutput) {
        GeneralUtil.clearScreen();

        String courseCode;

        System.out.println("Add Programme");
        System.out.println("-------------");

        boolean cont;
        do {
            cont = false;
            char[] checkChar = {'Y', 'N'};
            char c;
            courseCode = cScan.inputString("Enter Course Code > ");
            Course courseFound = new Course();
            //Course courseFound = courseIsExist(courseList, courseCode);
            if (courseFound == null) {
                System.err.println("Course not found.");
                c = cScan.inputChar("Continue to add programme? [Y = yes N = no] > ", "Please enter [Y] or [N] only.", checkChar);
                if (c == 'Y' || c == 'y') {
                    cont = true;
                    System.out.println("");
                }
            } else {
                boolean loop;
                ListInterface<Programme> programmeAdded = new CircularDoublyLinkedList<>();

                do {
                    loop = false;
                    displayCourseInformation(courseFound);
                    displayAddedProgrammeInCourse(courseFound, programmeAdded);
                    //displayProgrammeList(programmeListOutput);

                    String programmeCode = cScan.inputString("Enter programme code to add > ");

                    int isCodeValid = -1;
                    for (int i = 0; i < programmeList.getNumberOfEntries(); i++) {
                        if (programmeCode.equals(programmeList.getEntry(i).getProgrammeCode())) {
                            isCodeValid = i;
                        }
                    }
                    if (isCodeValid != -1) {
                        boolean isProgrammeExist = false;

                        if (!courseFound.getProgrammes().isEmpty()) {
                            for (int i = 0; i < courseFound.getProgrammes().getNumberOfEntries(); i++) {

                                if (programmeCode.equals(courseFound.getProgrammes().getEntry(i).getProgrammeCode())) {

                                    isProgrammeExist = true;
                                }
                            }
                        }
                        if (!programmeAdded.isEmpty()) {
                            for (int i = 0; i < programmeAdded.getNumberOfEntries(); i++) {
                                if (programmeCode.equals(programmeAdded.getEntry(i).getProgrammeCode())) {
                                    isProgrammeExist = true;
                                }
                            }
                        }

                        if (isProgrammeExist) {
                            System.err.println("Programme already in the current course.");
                        } else {
                            c = cScan.inputChar("Are you sure to add the programme to the course? [Y = yes N = no] > ", "Please enter [Y] or [N] only.", checkChar);
                            if (c == 'Y' || c == 'y') {
                                programmeAdded.add(programmeList.getEntry(isCodeValid));
                                System.out.println("Programme has successfully added in the course.");
                            }
                        }
                    } else {
                        System.err.println("No such programme.");
                    }

                    c = cScan.inputChar("Anymore programme add to the current course? [Y = yes N = no] > ", "Please enter [Y] or [N] only.", checkChar);
                    if (c == 'Y' || c == 'y') {
                        loop = true;
                        GeneralUtil.clearScreen();
                    } else {
                        Object[] obj = {courseFound, programmeAdded};
                        return obj;
                    }
                } while (loop);
            }

        } while (cont);

        return null;
    }

    public Object[] removeProgrammeFromCourse(ListInterface<Course> courseList) {
        GeneralUtil.clearScreen();

        String courseCode;

        System.out.println("Remove Programme");
        System.out.println("----------------");

        boolean cont;
        do {
            cont = false;
            char[] checkChar = {'Y', 'N'};
            char c;
            courseCode = cScan.inputString("Enter Course Code > ");
            Course courseFound = new Course();
            //Course courseFound = courseIsExist(courseList, courseCode);
            if (courseFound == null) {
                System.err.println("Course not found.");
                c = cScan.inputChar("Continue to remove programme? [Y = yes N = no] > ", "Please enter [Y] or [N] only.", checkChar);
                if (c == 'Y' || c == 'y') {
                    cont = true;
                    System.out.println("");
                }
            } else {
                boolean loop;
                ListInterface<Programme> programmeRemoved = new CircularDoublyLinkedList<>();

                do {
                    loop = false;
                    displayCourseInformation(courseFound);
                    displayRemovedProgrammeInCourse(courseFound, programmeRemoved);
                    if (!courseFound.getProgrammes().isEmpty() && (courseFound.getProgrammes().getNumberOfEntries() != programmeRemoved.getNumberOfEntries())) {
                        String programmeCode = cScan.inputString("Enter programme code to remove > ");
                        boolean programmeNotFound = false;

                        if (!programmeRemoved.isEmpty()) {

                            for (int i = 0; i < programmeRemoved.getNumberOfEntries(); i++) {
                                if (programmeCode.equals(programmeRemoved.getEntry(i).getProgrammeCode())) {
                                    programmeNotFound = true;
                                }
                            }
                        }

                        if (programmeNotFound == false) {
                            for (int i = 0; i < courseFound.getProgrammes().getNumberOfEntries(); i++) {
                                if (programmeCode.equals(courseFound.getProgrammes().getEntry(i).getProgrammeCode())) {

                                    c = cScan.inputChar("Are you sure to remove the programme from the course? [Y = yes N = no] > ", "Please enter [Y] or [N] only.", checkChar);

                                    if (c == 'Y' || c == 'y') {
                                        programmeRemoved.add(courseFound.getProgrammes().getEntry(i));
                                        System.out.println("The programme has successfully removed from the course.");
                                    }
                                    programmeNotFound = false;
                                    break;
                                } else {
                                    programmeNotFound = true;

                                }
                            }
                        }

                        if (programmeNotFound) {
                            System.err.println("No such programme in the course.");
                        }
                    } else {
                        System.out.println("The course do not have any programme.");
                    }
                    c = cScan.inputChar("Anymore programme to remove from the current course? [Y = yes N = no] > ", "Please enter [Y] or [N] only.", checkChar);
                    if (c == 'Y' || c == 'y') {
                        loop = true;
                        GeneralUtil.clearScreen();
                    } else {
                        Object[] obj = {courseFound, programmeRemoved};
                        return obj;
                    }

                } while (loop);
            }
        } while (cont);

        return null;
    }

    public int findCourse() {
        GeneralUtil.clearScreen();
        String outputStr = "";
        outputStr
                += "Find Course\n"
                + "-----------\n"
                + "1. By Course Code\n"
                + "2. By Course Name\n"
                + "3. By Credit Hours\n"
                + "4. By Course Fees\n"
                + "5. By Course Department\n"
                + "6. By Course Programmes\n"
                + "0. Back\n";
        System.out.println(outputStr);
        int choice = cScan.inputInt("Select edit choice > ", 0, 5);
        return choice;
    }

    public int getEditChoice(boolean courseIsExist, Course course) {
        if (courseIsExist) {
            displayCourseInformation(course);
            displayOriProgrammeInCourse(course);
            String outputStr = "";
            outputStr
                    += "Edit Choice\n"
                    + "===========\n"
                    + "1. Course Code\n"
                    + "2. Course Name\n"
                    + "3. Course Fees\n"
                    + "4. Course Department\n"
                    + "0. Back\n";
            System.out.println(outputStr);
            int choice = cScan.inputInt("Select edit choice > ", 0, 4);
            return choice;
        } else {
            System.err.println("Course not found.");
        }
        return -1;
    }

    private void displayCourseInformation(Course courseFound) {
        String outputStr = "";
        outputStr += "Course Information\n"
                + "==================\n"
                + "Course Code      : " + courseFound.getCourseCode() + "\n"
                + "Course Name      : " + courseFound.getCourseName() + "\n"
                + "Credit Hours     : " + courseFound.getCourseCreditHours() + "\n"
                + "Course Department: " + courseFound.getCourseDepartment() + "\n"
                + "Course Fees      : " + String.format("%.2f", courseFound.getCourseFees());

        System.out.println(outputStr);
    }

    private void displayOriProgrammeInCourse(Course courseFound) {
        String outputStr = "";
        if (!courseFound.getProgrammes().isEmpty()) {
            outputStr += "Programmes       : \n";
            for (int i = 0; i < courseFound.getProgrammes().getNumberOfEntries(); i++) {
                outputStr += String.format("\t%-4s%-35s\n", courseFound.getProgrammes().getEntry(i).getProgrammeCode(), courseFound.getProgrammes().getEntry(i).getProgrammeName());
            }
        }
        System.out.println(outputStr);
    }

    private void displayAddedProgrammeInCourse(Course courseFound, ListInterface<Programme> programmeAdded) {
        String outputStr = "";
        if (!courseFound.getProgrammes().isEmpty()) {
            outputStr += "Programmes       : \n";
            for (int i = 0; i < courseFound.getProgrammes().getNumberOfEntries(); i++) {
                outputStr += String.format("\t%-4s%-35s\n", courseFound.getProgrammes().getEntry(i).getProgrammeCode(), courseFound.getProgrammes().getEntry(i).getProgrammeName());
            }
        }
        if (!programmeAdded.isEmpty()) {
            for (int i = 0; i < programmeAdded.getNumberOfEntries(); i++) {
                outputStr += String.format("\t%-4s%-35s\n", programmeAdded.getEntry(i).getProgrammeCode(), programmeAdded.getEntry(i).getProgrammeName());
            }
        }
        System.out.println(outputStr);
    }

    private void displayRemovedProgrammeInCourse(Course courseFound, ListInterface<Programme> programmeRemoved) {
        String outputStr = "";

        if (!courseFound.getProgrammes().isEmpty() && (courseFound.getProgrammes().getNumberOfEntries() != programmeRemoved.getNumberOfEntries())) {

            outputStr += "Programmes       : \n";

            for (int i = 0; i < courseFound.getProgrammes().getNumberOfEntries(); i++) {
                boolean isRemoved = false;
                if (!programmeRemoved.isEmpty()) {
                    for (int j = 0; j < programmeRemoved.getNumberOfEntries(); j++) {
                        if (courseFound.getProgrammes().getEntry(i).getProgrammeCode().equals(programmeRemoved.getEntry(j).getProgrammeCode())) {
                            isRemoved = true;
                        }
                    }
                }
                if (!isRemoved) {
                    outputStr += String.format("\t%-4s%-35s\n", courseFound.getProgrammes().getEntry(i).getProgrammeCode(), courseFound.getProgrammes().getEntry(i).getProgrammeName());
                }
            }
        }
        System.out.println(outputStr);
    }

    private String getProgrammeCode(ListInterface<Programme> programmeList) {
        String outputStr = "";
        Iterator<Programme> it = programmeList.getIterator();
        while (it.hasNext()) {
            outputStr += it.next() + "\n";

        }
        System.out.println(
                "Programme List\n"
                + "=================================================================================");
        System.out.printf("%-15s%-50s%-20s\n", "Programme Code", "Programme Name", "Programme Type");
        System.out.println("=================================================================================\n" + outputStr);
        String input;
        boolean loop;
        do {
            loop = false;
            input = cScan.inputString("Enter programme code > ");
            Iterator<Programme> iter = programmeList.getIterator();
            while (iter.hasNext()) {
                if (input.equals(iter.next().getProgrammeCode())) {
                    return input;
                } else {
                    System.err.println("Programme code invalid. Please try again.");
                    loop = true;
                }
            }
        } while (loop);

        return input;

    }

    private int getCreditHour(String courseCode) {
        int creditHour = courseCode.charAt(7);
        if (Character.isUpperCase(courseCode.charAt(7))) {
            creditHour = creditHour - 'A' + 10;
        }
        return creditHour - '0';
    }

    private String getCourseDept() {
        System.out.println("                    Course Department     ");
        System.out.println("============================================================");
        System.out.println(" 1. Faculty of Accountancy, Finance And Business     [FAFB]");
        System.out.println(" 2. Faculty of Applied Sciences                      [FOAS]");
        System.out.println(" 3. Faculty of Built Environment                     [FOBE] ");
        System.out.println(" 4. Faculty of Communication And Creative Industries [FCCI]");
        System.out.println(" 5. Faculty of Computing And Information Technology  [FOCS]");
        System.out.println(" 6. Faculty of Engineering And Technology            [FOET]");
        System.out.println(" 7. Faculty of Social Science And Humanities         [FSSH]");
        System.out.println(" 8. Centre for Pre-University Studies                [CPUS]");
        System.out.println("============================================================");

        switch (cScan.inputInt("Select Course Department > ", 1, 8)) {
            case 1:
                return "FAFB";

            case 2:
                return "FOAS";

            case 3:
                return "FOBE";

            case 4:
                return "FCCI";

            case 5:
                return "FOCS";

            case 6:
                return "FOET";

            case 7:
                return "FSSH";

            case 8:
                return "CPUS";

            default:
                return null;
        }
    }

    public boolean repeatAction(String msg) {
        return cScan.confimation(msg);
    }

}

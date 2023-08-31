/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import adt.CircularDoublyLinkedList;
import adt.ListInterface;
import entity.*;
import java.time.format.FormatStyle;
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

    public Course addCourse(ListInterface<Course> courseList) {
        GeneralUtil.clearScreen();

        System.out.println("Add Course");
        System.out.println("----------");

        // var to store data
        String courseCode, courseName, courseDepartment;
        int courseCreditHours;
        double courseFees;

        boolean error;
        do {
            courseCode = cScan.inputCourseCode("Enter Course Code > ", "Invalid course code format.");
            for (int i = 0; i < courseList.getNumberOfEntries(); i++) {
                if (courseList.getEntry(i).getCourseCode().equals(courseCode)) {
                    error = true;
                    break;
                }
            }
            if (error) {
            error = false;
            courseCode = cScan.inputCourseCode("Enter Course Code > ", "Invalid course code format. [Eg: AACS1234]");
            if (courseIsExist(courseList, courseCode) != null) {
                System.err.println("The course code is already exist.");
                error = true;
            }
        } while (error);

        courseName = cScan.inputString("Enter Course Name > ");

        courseCreditHours = getCreditHour(courseCode);

        courseFees = cScan.inputDouble("Enter Course Fees > ", 0.00, 9999.99);

        displayCourseDeptSelection();
        courseDepartment = getCourseDept(cScan.inputInt("Select Course Department > ", 1, 8));

        if (cScan.confimation("\\n[Confirmation]\\n [Y = yes N = no]\\nAre You Sure? > ")) {
            return new Course(courseCode, courseName, courseCreditHours, courseDepartment, courseFees);
        }

        return null;
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
            Course courseFound = courseIsExist(courseList, courseCode);
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
            Course courseFound = courseIsExist(courseList, courseCode);
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
                    displayProgrammeList(programmeListOutput);

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
            Course courseFound = courseIsExist(courseList, courseCode);
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

    private Course courseIsExist(ListInterface<Course> courseList, String courseCode) {
        for (int i = 0; i < courseList.getNumberOfEntries(); i++) {
            if (courseList.getEntry(i).getCourseCode().equals(courseCode)) {
                return courseList.getEntry(i);
            }
        }
        return null;
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

    private void displayProgrammeList(String programmeListOutput) {
        System.out.println(
                "Programme List\n"
                + "=================================================================================");
        System.out.printf("%-15s%-50s%-20s\n", "Programme Code", "Programme Name", "Programme Type");
        System.out.println("=================================================================================\n" + programmeListOutput);

    }

    private int getCreditHour(String courseCode) {
        int creditHour = courseCode.charAt(7);
        if (Character.isUpperCase(courseCode.charAt(7))) {
            creditHour = creditHour - 'A' + 10;
        }
        return creditHour - '0';
    }

    private String getCourseDept(int num) {
        switch (num) {
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

    private void displayCourseDeptSelection() {
        System.out.println("\n                    Course Department     ");
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
    }

}

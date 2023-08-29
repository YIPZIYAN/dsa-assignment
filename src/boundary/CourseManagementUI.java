/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import adt.ListInterface;
import entity.Course;
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
        System.out.println("1. Course List\n"
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
        
        System.out.println("List of Courses: \n"
                + "No Course Code Name                                              Credit Hours Department Fees\n"
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

        boolean error = false;
        do {
            courseCode = cScan.inputCourseCode("Enter Course Code > ", "Invalid course code format.");
            for (int i = 0; i < courseList.getNumberOfEntries(); i++) {
                if(courseList.getEntry(i).getCourseCode().equals(courseCode)) {
                    error = true;
                    break;
                }
            }
            if(error) {
                System.err.println("The course code is already exist.");
            }
        } while (error);
        

        courseName = cScan.inputString("Enter Course Name > ");

        courseCreditHours = getCreditHour(courseCode);

        courseFees = cScan.inputDouble("Enter Course Fees > ", 0.00, 9999.99);

        displayCourseDeptSelection();
        courseDepartment = getCourseDept(cScan.inputInt("Select Course Department > ", 1, 8));

        if (cScan.confimation()) {
            return new Course(courseCode, courseName, courseCreditHours, courseDepartment, courseFees);
        }

        return null;
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

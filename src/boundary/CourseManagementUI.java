package boundary;

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

    //COURSE MENU
    public int getMenuChoice() {

        GeneralUtil.clearScreen();
        System.out.println("       Course Management");
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
                + "9. Back\n"
                + "0. Quit");
        System.out.println("-------------------------------");

        int choice = cScan.inputInt("Enter Selection > ", 0, 9);
        return choice;
    }

    //1. COURSE LIST
    public void listAllCourses(String outputStr) {
        GeneralUtil.clearScreen();

        System.out.println("Course List: \n"
                + "============================================================================================\n"
                + "No  Course Code Name                                         Credit Hours Department Fees\n"
                + "============================================================================================\n"
                + outputStr);

        GeneralUtil.systemPause();
    }

    //2. ADD COURSE
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

    //3. FIND COURSE
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
        int choice = cScan.inputInt("Select edit choice > ", 0, 6);
        return choice;
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

    public void displayProgrammeSearchResults(String outputStr) {
        System.out.println("Search Results:");
        System.out.println("=================================================================================");
        System.out.printf("%-15s%-50s%-20s\n", "Programme Code", "Programme Name", "Programme Type");
        System.out.println("=================================================================================");
        System.out.println(outputStr);
    }

    //4. EDIT COURSE
    public String editCourseMenu() {
        GeneralUtil.clearScreen();
        System.out.println("Edit Course");
        System.out.println("-----------");
        return cScan.inputString("Enter Course Code > ");
    }

    public int getEditChoice(boolean courseIsExist, Course course) {
        if (courseIsExist) {
            GeneralUtil.clearScreen();
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

    public void displayEditSucMsg(int choice) {
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

    public boolean displayEditErrMsg(int choice) {

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

    //5. REMOVE COURSE
    public String removeCourse() {
        GeneralUtil.clearScreen();
        System.out.println("Remove Course");
        System.out.println("-------------");
        return cScan.inputString("Enter Course Code > ");
    }

    public void displayRmvMsg(boolean isExist) {
        if (isExist) {
            System.out.println("The course has been successfully removed.");
        } else {
            System.err.println("Course not found.");
        }

    }

    //6. ADD PROGRAMME TO COURSE
    public String addProgrammeToCourse() {
        GeneralUtil.clearScreen();
        System.out.println("Add Programme To Course");
        System.out.println("-----------------------");
        return cScan.inputString("Enter Course Code > ");

    }

    public void displayAddProgrammeMsg(boolean isSuccess) {
        if (isSuccess) {
            System.out.println("Programme has successfully added in the course.");
        } else {
            System.err.println("Programme already in the current course.");
        }
    }

    //7. REMOVE PROGRAMME FROM COURSE
    public String removeProgrammeFromCourse() {
        GeneralUtil.clearScreen();
        System.out.println("Remove Programme From Course");
        System.out.println("----------------------------");
        return cScan.inputString("Enter Course Code > ");

    }

    public String getRemoveProgrammeCode() {
        return cScan.inputString("Enter Programme Code > ");
    }

    public void displayCourseInfo(Course courseFound) {
        System.out.println("Course Information");
        System.out.println("==================");
        System.out.println("Course Code      : " + courseFound.getCourseCode());
        System.out.println("Course Name      : " + courseFound.getCourseName());
        System.out.println("Credit Hours     : " + courseFound.getCourseCreditHours());
        System.out.println("Course Department: " + courseFound.getCourseDepartment());
        System.out.println("Course Fees      : " + String.format("%.2f", courseFound.getCourseFees()));

    }

    public void displayRmvProgrammeMsg(boolean isSuccess) {
        if (isSuccess) {
            System.out.println("Programme has successfully removed from the course.");
        } else {
            System.err.println("Programme not found in the current course.");
        }

    }

    //8. GENERATE REPORT
    public int reportMenu() {
        GeneralUtil.clearScreen();
        System.out.println("Generate Report");
        System.out.println("---------------");
        System.out.println("1. Course Report");
        System.out.println("2. Programme Report");
        System.out.println("0. Back");
        return cScan.inputInt("Enter your choice > ", 0, 2);
    }

    public int courseReportMenu() {
        GeneralUtil.clearScreen();
        System.out.println("Course Report");
        System.out.println("-------------");
        System.out.println("1. With Course Code Sorting");
        System.out.println("2. With Course Name Sorting");
        System.out.println("3. With Credit Hours Sorting");
        System.out.println("4. With Course Fees Sorting");
        System.out.println("5. With Course Department Sorting");
        System.out.println("0. Back");
        return cScan.inputInt("Enter your choice > ", 0, 5);
    }

    public void displayAllCourse(String outputStr, boolean displayOnly) {
        GeneralUtil.clearScreen();
        System.out.println("Course Report");
        displayCourseTableHeader();
        System.out.println(outputStr);
        if (displayOnly) {
            GeneralUtil.systemPause();
        }
    }

    public void displayAllProgramme(String outputStr, boolean displayOnly) {
        GeneralUtil.clearScreen();
        System.out.println("Programme Report");
        displayProgrammeTableHeader();
        System.out.println(outputStr);
        if (displayOnly) {
            GeneralUtil.systemPause();
        }
    }

    public void displayCourseTableHeader() {
        System.out.println("============================================================================================");
        System.out.println("No  Course Code Name                                         Credit Hours Department Fees");
        System.out.println("============================================================================================");
    }

    public void displayProgrammeTableHeader() {
        System.out.println("=====================================================================================");
        System.out.printf("%-2s. %-15s%-50s%-20s\n", "No", "Programme Code", "Programme Name", "Programme Type");
        System.out.println("=====================================================================================");
    }

    public void generateCourseReportHeader() {
        System.out.println("\n                   Page Controller      ");
        System.out.println("=====================================================");
    }

    public String generateCourseReportMenu() {
        generateCourseReportHeader();
        System.out.println(" Enter command below to perform the following tasks.");
        System.out.println(" [  |< ]  Go to first page.");
        System.out.println(" [  <  ]  Go to previous page.");
        System.out.println(" [  >  ]  Go to next page.");
        System.out.println(" [  >| ]  Go to last page.");
        System.out.println(" You can also enter number of the page.");
        MessageUI.displayInfoMessage(" Enter \"exit\" to go previous process.");
        System.out.println("=====================================================");

        String command = cScan.inputString("Enter action command > ");
        return command;
    }

    public void displayCourseTotal(int total) {
        System.out.println("TOTAL COURSE > " + total);
    }

    public void displayProgrammeTotal(int total) {
        System.out.println("TOTAL PROGRAMME > " + total);
    }

    //OTHERS FUNCTION
    public String getProgrammeCode(ListInterface<Programme> programmeList) {
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
                    loop = true;
                }
            }
            if (loop) {
                System.err.println("Programme code invalid. Please try again.");
            }
        } while (loop);

        return input;

    }

    public int getCreditHour(String courseCode) {
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

    public void displayCourseInformation(Course courseFound) {
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

    public void displayOriProgrammeInCourse(Course courseFound) {
        String outputStr = "";
        if (!courseFound.getProgrammes().isEmpty()) {
            outputStr += "Programmes       : \n";
            for (int i = 0; i < courseFound.getProgrammes().getNumberOfEntries(); i++) {
                outputStr += String.format("\t%-4s%-35s\n", courseFound.getProgrammes().getEntry(i).getProgrammeCode(), courseFound.getProgrammes().getEntry(i).getProgrammeName());
            }
        }
        System.out.println(outputStr);
    }

    public boolean repeatAction(String msg) {
        return cScan.confimation(msg);
    }

}

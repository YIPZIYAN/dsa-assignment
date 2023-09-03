/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.exampleAdt.ArrayList;
import adt.exampleAdt.ListInterface;
import entity.Tutor;
import utility.*;

/**
 *
 * @author Yip Zi Yan
 */
public class TutorManagementUI {

    CustomScanner cScan = new CustomScanner();

    public int getMenuChoice() {

        GeneralUtil.clearScreen();
        System.out.println("         Tutor Management System          ");
        System.out.println("------------------------------------------");
        System.out.println("1. Tutor List\n"
                + "2. Add Tutor\n"
                + "3. Find Tutor\n"
                + "4. Edit / Remove Tutor Details\n"
                + "5. Filter Tutor\n"
                + "6. Generate Report\n"
                + "0. Quit");
        System.out.println("------------------------------------------");

        int choice = cScan.inputInt("Enter Selection > ", 0, 6);

        return choice;
    }

    public boolean addTutorMenu() {
        GeneralUtil.clearScreen();
        displayAddTutorHeader();
        System.out.println("You are required to enter:");
        System.out.println("1. Tutor Name");
        System.out.println("2. Tutor Gender");
        System.out.println("3. Tutor Status (Part-Time/Full-Time)");
        return cScan.confimation("\nStart Now? [Y|N] > ");
    }

    private void displayAddTutorHeader() {
        System.out.println("          Add New Tutor          ");
        System.out.println("=================================");
    }

    public Tutor addTutor(int currentId) {

        GeneralUtil.clearScreen();
        displayAddTutorHeader();

        // var to store data
        String name, email;
        double salary;
        char gender, status;

        // constraint
        char[] checkGender = {'M', 'F'};
        char[] checkStatus = {'F', 'P'};

        name = cScan.inputString("Enter Tutor Name  > ");

        displayGenderSelection();
        gender = cScan.inputChar("Enter Tutor Gender > ", "Please enter [M] or [F] only.", checkGender);

        displayStatusSelection();
        status = cScan.inputChar("Enter Status > ", "Please enter [F] or [P] only.", checkStatus);

        salary = cScan.inputDouble("Enter Monthly Salary > RM ", 0, 999999.99);

        Tutor pendingTutor = new Tutor(name, gender, status, salary, currentId);

        GeneralUtil.clearScreen();
        displayAddTutorHeader();
        displayTutorDetails(pendingTutor);

        if (cScan.confimation("\n[Confirmation]\nAre You Sure? [Y|N] > ")) {
            System.out.println("New Tutor Successfully Added.");
            return pendingTutor;

        }

        Tutor.setTotalTutor(Tutor.getTotalTutor() - 1); // Cancel Add Process, Total-1
        return null;
    }

    public boolean contAction(String str) {
        return cScan.confimation(str);
    }

    private void displayStatusSelection() {
        System.out.println("\n     Status     ");
        System.out.println("================");
        System.out.println(" F    Full-time ");
        System.out.println(" P    Part-time ");
        System.out.println("================");
        System.out.println("Enter [F] or [P]");
    }

    private void displayGenderSelection() {
        System.out.println("\n     Gender     ");
        System.out.println("================");
        System.out.println(" M    Male      ");
        System.out.println(" F    Female    ");
        System.out.println("================");
        System.out.println("Enter [M] or [F]");
    }

    public void displayAllTutor(String outputStr, boolean displayOnly) {
        GeneralUtil.clearScreen();
        System.out.printf("\n%50s\n", "Tutor List");
        displayTutorTableHeader();
        System.out.println(outputStr);
//        System.out.printf("%s\n\n", "Total Number of Tutor(s) = " + total);
        if (displayOnly) {
            GeneralUtil.systemPause();
        }
    }

    public void displayTutorTableHeader() {
        System.out.println("========================================================================================================================================================");
        System.out.printf("%-4s %-8s %-26s %-10s %-32s %-11s %-12s %-22s %s\n", "No.", "ID", "Name", "Gender", "Email", "Salary", "Status", "Created At", "Updated At");
        System.out.println("========================================================================================================================================================");
    }

    private void displayTutorDetails(Tutor pendingTutor) {
        System.out.println("Name                   : " + pendingTutor.getTutorName());
        System.out.println("Gender                 : " + pendingTutor.getGenderStr());
        System.out.println("Email (auto-generated) : " + pendingTutor.getEmail());
        System.out.println("Status                 : " + pendingTutor.getStatusStr());
        System.out.printf("Salary (RM)            : %.2f\n", pendingTutor.getSalary());
    }

    public int findTutorMenu() {
        GeneralUtil.clearScreen();
        findTutorHeader();
        System.out.println("1. Find By Name\n"
                + "2. Find By Email\n"
                + "3. Find By Tutor ID\n"
                + "0. Quit");
        System.out.println("==========================");

        int choice = cScan.inputInt("Enter Selection > ", 0, 3);

        return choice;
    }

    public void findTutorHeader() {
        System.out.println("        Find Tutor        ");
        System.out.println("==========================");
    }

    public String getTutorQuery(String queryQuestion, String mode) {

        switch (mode) {
            case "find":
                GeneralUtil.clearScreen();
                findTutorHeader();
                break;
            case "update":
                break;
        }
        return cScan.inputString(queryQuestion);
    }

    public void displayFindResult(String str) {
        GeneralUtil.clearScreen();

        System.out.printf("%50s\n", "Search Result");
        displayTutorTableHeader();
        if (str.equals("")) {
            MessageUI.displayNoResultMessage();
        } else {
            System.out.println(str);
        }
        GeneralUtil.systemPause();
    }

    public void updateTutorHeader() {
        System.out.println("                Update Tutor                 ");
        System.out.println("=============================================");
    }

    public int updateTutorMenu() {

        updateTutorHeader();
        System.out.println("Please Select A Tutor To Edit");
        System.out.println("1. Select By No.\n"
                + "2. Select By ID\n"
                + "0. Quit");
        System.out.println("=============================================");

        int choice = cScan.inputInt("Enter Selection > ", 0, 2);

        return choice;
    }

    public void displaySelectedTutor(Tutor selectedTutor) {
        GeneralUtil.clearScreen();
        updateTutorHeader();
        if (selectedTutor == null) {
            MessageUI.displayNoResultMessage();
            GeneralUtil.systemPause();
        } else {
            displayTutorDetails(selectedTutor);
        }
    }

    public int selectAttributeToUpdate(Tutor selectedTutor) {
        System.out.println("\n       Action");
        System.out.println("====================");
        System.out.println("1. Change Name");
        System.out.print("2. Change To ");
        System.out.println(selectedTutor.getGender() == 'M'
                ? "Female" : "Male");
        System.out.println("3. Change Status");
        System.out.println("4. Change Salary");
        System.out.println("5. REMOVE Tutor");
        System.out.println("0. Exit");
        MessageUI.displayInfoMessage("Please note that the email will change when the name is edited \nor the status is updated from full-time to part-time, or vice versa");
        int choice = cScan.inputInt("Enter Selection > ", 0, 5);

        return choice;
    }

    public String updateTutorName() {
        String newName = cScan.inputString("Enter Tutor Name  > ");

        if (cScan.confimation("Are You Sure? [Y|N] > ")) {
            return newName;
        }

        return null;
    }

    public boolean updateTutorGender(boolean isFemale) {

        String str = "Are You Sure To Change Gender To ";
        str += isFemale ? "Male" : "Female";
        str += "? [Y|N] > ";

        return cScan.confimation(str);
    }

    public double updateTutorSalary() {
        double newSalary = cScan.inputDouble("Enter Salary > RM ", 0, 999999.99);

        if (cScan.confimation("Are You Sure? [Y|N] > ")) {
            return newSalary;
        }

        return -1;
    }

    public String updateTutorStatus(String currentStatus) {
        String[] allStatus = {"FT", "PT", "RS", "RT"};
        ListInterface<Character> validStatus = new ArrayList<>();

        //display valid status
        System.out.println("\n     Status     ");
        System.out.println("================");
        for (String status : allStatus) {
            if (!status.equals(currentStatus)) {
                validStatus.add(statusList(status));
            }
        }
        System.out.println("================");

        //convert valid status to char[]
        char[] checkStatus = new char[validStatus.getNumberOfEntries()];
        for (int i = 0; i < validStatus.getNumberOfEntries(); i++) {
            checkStatus[i] = validStatus.getEntry(i + 1);
        }

        char newStatus = cScan.inputChar("Enter Status > ",
                "Invalid Status Code.",
                checkStatus);

        if (cScan.confimation("Are You Sure? [Y|N] > ")) {
            return convertToStatusCode(newStatus);
        }
        return null;
    }

    private char statusList(String status) {
        switch (status) {
            case "FT":
                System.out.println(" F    Full-time ");
                return 'F';
            case "PT":
                System.out.println(" P    Part-time ");
                return 'P';
            case "RS":
                System.out.println(" R    Resigned  ");
                return 'R';
        }
        System.out.println(" T    Retired   ");
        return 'T';
    }

    private String convertToStatusCode(char ch) {
        switch (Character.toUpperCase(ch)) {
            case 'F':
                return "FT";
            case 'P':
                return "PT";
            case 'R':
                return "RS";
        }
        return "RT";
    }

    public boolean removeTutor() {
        return cScan.confimation("Are You Sure To REMOVE This Tutor? [Y|N] > ");
    }

    public void removedClosure() {
        MessageUI.displayInfoMessage("Removed successfully."
                + "\nPlease note that system will return back to previous screen since this tutor had been removed.");
        GeneralUtil.systemPause();
    }

    public void filterTutorHeader() {
        System.out.println("              Filter Tutor By Status              ");
        System.out.println("==================================================");
    }

    public int filterTutorMenu(String selected) {
        GeneralUtil.clearScreen();
        filterTutorHeader();
        System.out.println("You can select multiple criterias to filter");
        System.out.println("1. Full-Time\t[FT]\n"
                + "2. Part-Time\t[PT]\n"
                + "3. Resigned\t[RS]\n"
                + "4. Retired\t[RT]");

        System.out.print(!selected.equals("") ? "5. Undo\n6. Done\n" : "");
        System.out.println("0. Quit");
        if (!selected.equals("")) {
            System.out.println("\nFilter: " + selected);
        }
        System.out.println("==================================================");

        int choice = cScan.inputInt("Enter Selection > ", 0,
                selected.equals("") ? 4 : 6);

        return choice;
    }

    public void displayFilteredTutor(String outputStr) {
        GeneralUtil.clearScreen();
        System.out.printf("\n%66s\n", "Filtered Tutor List");
        displayTutorTableHeader();
        if (outputStr.equals("")) {
            MessageUI.displayNoResultMessage();
        } else {
            System.out.println(outputStr);
        }
        GeneralUtil.systemPause();
    }

    public void generateTutorReportHeader() {
        System.out.println("\n                   Page Controller      ");
        System.out.println("=====================================================");
    }

    public String generateTutorReportMenu() {
        generateTutorReportHeader();
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
}

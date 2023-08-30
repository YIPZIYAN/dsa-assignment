/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

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
        System.out.println("Tutor Management System");
        System.out.println("--------------------------");
        System.out.println("1. Tutor List\n"
                + "2. Add Tutor\n"
                + "3. Find Tutor\n"
                + "4. Edit Tutor Details\n"
                + "5. Remove Tutor\n"
                + "6. Filter Tutor\n"
                + "7. Generate Report\n"
                + "0. Quit");
        System.out.println("--------------------------");

        int choice = cScan.inputInt("Enter Selection > ", 0, 9);

        return choice;
    }

    public boolean addTutorMenu() {
        GeneralUtil.clearScreen();
        displayAddTutorHeader();
        System.out.println("Your are required to enter:");
        System.out.println("1. Tutor Name");
        System.out.println("2. Tutor Gender");
        System.out.println("3. Tutor Status (Part-Time/Full-Time)");
        return cScan.confimation("\nStart Now? [Y|N] > ");
    }

    private void displayAddTutorHeader() {
        System.out.println("          Add New Tutor          ");
        System.out.println("=================================");
    }

    public Tutor addTutor() {

        GeneralUtil.clearScreen();
        displayAddTutorHeader();

        // var to store data
        String name, email;
        char gender, status;

        // constraint
        char[] checkGender = {'M', 'F'};
        char[] checkStatus = {'F', 'P'};

        name = cScan.inputString("Enter Tutor Name  > ");

        displayGenderSelection();
        gender = cScan.inputChar("Enter Tutor Gender > ", "Please enter [M] or [F] only.", checkGender);

        displayStatusSelection();
        status = cScan.inputChar("Enter Status > ", "Please enter [F] or [P] only.", checkStatus);

        Tutor pendingTutor = new Tutor(name, gender, status);

        GeneralUtil.clearScreen();
        displayAddTutorHeader();
        displayTutorDetails(pendingTutor);

        if (cScan.confimation("\n[Confirmation]\nAre You Sure? [Y|N] > ")) {
            System.out.println("New Tutor Successfully Added.");
            return pendingTutor;

        }

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

    public void displayAllTutor(String outputStr) {
        GeneralUtil.clearScreen();
        System.out.printf("\n%50s\n", "Tutor List");
        displayTutorTableHeader();
        System.out.println(outputStr);
//        System.out.printf("%s\n\n", "Total Number of Tutor(s) = " + total);
        GeneralUtil.systemPause();
    }

    public void displayTutorTableHeader() {
        System.out.println("==============================================================================================");
        System.out.printf("%-4s %-8s %-26s %-10s %-32s %s\n", "No.","ID", "Name", "Gender", "Email", "Status");
        System.out.println("==============================================================================================");
    }

    private void displayTutorDetails(Tutor pendingTutor) {
        System.out.println("Name                   : " + pendingTutor.getTutorName());
        System.out.println("Gender                 : " + pendingTutor.getGenderStr());
        System.out.println("Email (auto-generated) : " + pendingTutor.getEmail());
        System.out.println("Status                 : " + pendingTutor.getStatusStr());
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

    public String getTutorNameQuery(String queryQuestion) {
        GeneralUtil.clearScreen();
        findTutorHeader();
        return cScan.inputString(queryQuestion);
    }

    public void displayFindResult(String str) {
        GeneralUtil.clearScreen();
        
        System.out.printf("%50s\n","Search Result");
        displayTutorTableHeader();
        if (str.equals("")) {
            MessageUI.displayNoResultMessage();
        } else {
            System.out.println(str);
        }
        GeneralUtil.systemPause();
    }
    
    

}

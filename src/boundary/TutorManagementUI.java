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

        if (!addTutorMenu()) {
            return null;
        }

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
        displayNewTutor(pendingTutor);

        if (cScan.confimation("\n[Confirmation]\nAre You Sure? [Y|N] > ")) {
            System.out.println("New Tutor Successfully Added.");
            return pendingTutor;

        }

        return null;
    }
    
    public boolean contAction(String str){
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

    public void displayAllTutor(String outputStr, int total) {
        GeneralUtil.clearScreen();
        System.out.printf("\n%50s\n", "Tutor List");
        System.out.println("=========================================================================================");
        System.out.printf("%-8s %-26s %-10s %-32s %s\n", "ID", "Name", "Gender", "Email", "Status");
        System.out.println("=========================================================================================");
        System.out.println(outputStr);
        System.out.printf("%s\n\n", "Total Number of Tutor(s) = " + total);
        GeneralUtil.systemPause();
    }

    private void displayNewTutor(Tutor pendingTutor) {
        GeneralUtil.clearScreen();
        displayAddTutorHeader();
        System.out.println("Name                   : " + pendingTutor.getTutorName());
        System.out.println("Gender                 : " + pendingTutor.getGenderStr());
        System.out.println("Email (auto-generated) : " + pendingTutor.getEmail());
        System.out.println("Status                 : " + pendingTutor.getStatusStr());
    }
}

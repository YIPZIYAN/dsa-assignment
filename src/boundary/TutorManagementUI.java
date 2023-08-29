/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import entity.Tutor;
import java.util.Scanner;
import utility.*;

/**
 *
 * @author Yip Zi Yan
 */
public class TutorManagementUI {

    CustomScanner cScan = new CustomScanner();

    public int getMenuChoice() {

        GeneralUtil.clearScreen();
        System.out.println("Teaching Assignemnt System");
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

    public Tutor addTutor() {
        GeneralUtil.clearScreen();

        // var to store data
        String name, email, status;
        char gender;

        // constraint
        char[] checkGender = {'M', 'F'};

        name = cScan.inputString("Enter Tutor Name  > ");

        displayGenderSelection();
        gender = cScan.inputChar("Enter Tutor Gender > ", "Please enter [M] or [F] only.", checkGender);

        displayStatusSelection();
        status = cScan.inputString("Enter Status > ");

        if (cScan.confimation()) {
            return new Tutor(name, gender, status);
        }

        return null;
    }

    private void displayStatusSelection() {
        System.out.println("\n     Status     ");
        System.out.println("================");
        System.out.println(" FT   Full-time ");
        System.out.println(" PT   Part-time ");
        System.out.println("================");
        System.out.println("Enter [FT] or [PT]");
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
        System.out.println("Tutor List");
        System.out.println("===================================================");
        System.out.printf("%6s %12s %12s %12s %s\n", "ID", "Name", "Gender", "Email", "Status");
        System.out.println("===================================================");
        System.out.println(outputStr);
        GeneralUtil.systemPause();
    }
}

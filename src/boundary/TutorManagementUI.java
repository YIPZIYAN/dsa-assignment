/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

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
}

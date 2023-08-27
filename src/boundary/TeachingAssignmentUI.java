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
public class TeachingAssignmentUI {

    CustomScanner cScan = new CustomScanner();



    
    
    public int getMenuChoice() {

        System.out.println("Teaching Assignemnt System");
        System.out.println("--------------------------");
        System.out.println("1. Assign tutor to course\n"
                + "2. Assign tutorial groups to tutor\n"
                + "3. Assign a tutor to a tutorial group\n"
                + "4. Search courses under a tutor\n"
                + "5. Search tutors for a course\n"
                + "6. List all tutors for a course\n"
                + "7. List courses under a tutor\n"
                + "8. Filter tutors based on criteria\n"
                + "9. Generate relevant reports\n");
        System.out.println("0. Quit");
        
        int choice = cScan.inputInt("Enter Selection",0,9);
        return choice;
    }
}

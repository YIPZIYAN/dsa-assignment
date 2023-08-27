/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import boundary.TutorManagementUI;
import utility.MessageUI;

/**
 *
 * @author Yip Zi Yan
 */
public class TutorManagement {

    TutorManagementUI teachingUI = new TutorManagementUI();

    public void startUI() {
        
        int choice;
        do {
            choice = teachingUI.getMenuChoice();
            switch (choice) {
                case 1:
                    
                    break;
                case 0:
                    MessageUI.displayExitMessage();
            }
        } while (choice != 0);

    }

    public static void main(String[] args) {
        TutorManagement teachingControl = new TutorManagement();
        teachingControl.startUI();
    }
}

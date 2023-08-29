/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import boundary.TutorManagementUI;
import entity.Tutor;
import utility.GeneralUtil;
import utility.MessageUI;

/**
 *
 * @author Yip Zi Yan
 */
public class TutorManagement {

    TutorManagementUI tutorUI = new TutorManagementUI();

    public void startUI() {
        
        int choice;
        do {
            choice = tutorUI.getMenuChoice();
            switch (choice) {
                case 2:
                    addNewTutor();
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

    private void addNewTutor() {
        Tutor newTutor = tutorUI.addTutor();
        
        if (newTutor == null) {
            return;
        }
        
        System.out.println(newTutor);
        GeneralUtil.systemPause();
    }
}

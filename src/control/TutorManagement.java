/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import boundary.TutorManagementUI;

/**
 *
 * @author Yip Zi Yan
 */
public class TutorManagement {
    
    TutorManagementUI teachingUI = new TutorManagementUI();


    
    public void startUI(){
        int choice = teachingUI.getMenuChoice();

    }

    public static void main(String[] args) {
        TutorManagement teachingControl = new TutorManagement();
        teachingControl.startUI();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import boundary.TeachingAssignmentUI;

/**
 *
 * @author Yip Zi Yan
 */
public class TeachingAssignment {
    
    TeachingAssignmentUI teachingUI = new TeachingAssignmentUI();


    
    public void startUI(){
        int choice = teachingUI.getMenuChoice();

    }

    public static void main(String[] args) {
        TeachingAssignment teachingControl = new TeachingAssignment();
        teachingControl.startUI();
    }
}

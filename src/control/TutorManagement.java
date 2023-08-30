/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.CircularDoublyLinkedList;
import adt.ListInterface;
import boundary.TutorManagementUI;
import dao.Seeder;
import entity.Tutor;
import java.util.Iterator;
import utility.MessageUI;

/**
 *
 * @author Yip Zi Yan
 */
public class TutorManagement {

    TutorManagementUI tutorUI = new TutorManagementUI();
    ListInterface<Tutor> tutorList = new CircularDoublyLinkedList<>();
    Seeder seeder = new Seeder(); //testing

    public void startUI() {

        int choice;
        do {
            choice = tutorUI.getMenuChoice();
            switch (choice) {
                case 1:
                    getAllTutor();
                    break;
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
        do {
            Tutor newTutor = tutorUI.addTutor();

            if (newTutor == null) {
                return;
            }
            seeder.getTutorList().add(newTutor);

        } while (tutorUI.contAction("Anymore To Add? [Y|N] > "));

    }

    private void getAllTutor() {
        String outputStr = "";

        Iterator<Tutor> it = seeder.getTutorList().getIterator();
        while (it.hasNext()) {
            outputStr += it.next() + "\n";

        }

        tutorUI.displayAllTutor(outputStr, seeder.getTutorList().getNumberOfEntries());
    }
}

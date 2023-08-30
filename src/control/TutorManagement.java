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
                case 3:
                    searchTutorUI();
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
        if (!tutorUI.addTutorMenu()) {
            return;
        }

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
        int number = 0;

        Iterator<Tutor> it = seeder.getTutorList().getIterator();
        while (it.hasNext()) {
            outputStr += String.format("%2d.  ", ++number)
                    + it.next() + "\n";

        }
        tutorUI.displayAllTutor(outputStr);
    }

    private void searchTutorUI() {
        int choice;
        do {
            choice = tutorUI.findTutorMenu();
            switch (choice) {
                case 1:
                    searchTutorByName("name",
                            "Search Tutor Name > ");
                    break;
                case 2:
                    searchTutorByName("email",
                            "Search Tutor Email > ");
                    break;
                case 3:
                    searchTutorByName("id",
                            "Search Tutor ID > ");
                    break;
            }
        } while (choice != 0);
    }

    private void searchTutorByName(String attribute, String queryQuestrion) {
        String query = tutorUI.getTutorNameQuery(queryQuestrion).toLowerCase();
        String outputStr = "";
        Iterator<Tutor> it = seeder.getTutorList().getIterator();
        int number = 0;

        while (it.hasNext()) {
            Tutor matchTutor = it.next();
            switch (attribute) {
                case "name":
                    if (matchTutor.getTutorName().toLowerCase().contains(query)) {
                        outputStr += String.format("%2d.  ", ++number)
                                + matchTutor + "\n";
                    }
                    break;
                case "email":
                    if (matchTutor.getEmail().toLowerCase().contains(query)) {
                        outputStr += String.format("%2d.  ", ++number)
                                + matchTutor + "\n";
                    }
                    break;
                case "id":
                    if (matchTutor.getTutorId().toLowerCase().equals(query)) {
                        outputStr += String.format("%2d.  ", ++number)
                                + matchTutor + "\n";
                    }
                    break;
            }

        }

        tutorUI.displayFindResult(outputStr);

    }
}

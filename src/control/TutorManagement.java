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
                    displayTutorList();
                    break;
                case 2:
                    addNewTutor();
                    break;
                case 3:
                    searchTutorUI();
                    break;
                case 4:
                    updateTutorUI();
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

            if (newTutor != null) {
                seeder.getTutorList().add(newTutor);
            }

        } while (tutorUI.contAction("Anymore To Add? [Y|N] > "));

    }

    private void displayTutorList() {
        tutorUI.displayAllTutor(getAllTutor(), true);
    }

    private String getAllTutor() {
        String outputStr = "";
        int number = 0;

        Iterator<Tutor> it = seeder.getTutorList().getIterator();
        while (it.hasNext()) {
            outputStr += String.format("%2d.  ", ++number)
                    + it.next() + "\n";

        }
        return outputStr;
    }

    private void searchTutorUI() {
        int choice;
        do {
            choice = tutorUI.findTutorMenu();
            switch (choice) {
                case 1:
                    searchTutorBy("name",
                            "Search Tutor Name > ");
                    break;
                case 2:
                    searchTutorBy("email",
                            "Search Tutor Email > ");
                    break;
                case 3:
                    searchTutorBy("id",
                            "Search Tutor ID > ");
                    break;
            }
        } while (choice != 0);
    }

    private void searchTutorBy(String attribute, String queryQuestion) {
        String query = tutorUI.getTutorQuery(queryQuestion, "find")
                .toLowerCase();
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

    private void updateTutorUI() {
        int choice;
        do {
            tutorUI.displayAllTutor(getAllTutor(), false);
            choice = tutorUI.updateTutorMenu();
            switch (choice) {
                case 1:
                    updateTutor("num", "Enter No. > ");
                    break;
                case 2:
                    updateTutor("id", "Enter Tutor ID > ");
                    break;

            }
        } while (choice != 0);
    }

    private void updateTutor(String searchBy, String queryQuestion) {
        String query = tutorUI.getTutorQuery(queryQuestion, "update")
                .toLowerCase();
        Tutor updateTutor = null;
        Iterator<Tutor> it = seeder.getTutorList().getIterator();

        switch (searchBy) {
            case "id":
                while (it.hasNext()) {
                    Tutor matchTutor = it.next();

                    if (matchTutor.getTutorId().toLowerCase().equals(query)) {
                        updateTutor = matchTutor;
                    }

                }
                break;
            case "num":
                try {
                updateTutor = seeder.getTutorList()
                        .getEntry(Integer.parseInt(query) - 1);
            } catch (Exception e) {
            }
            break;
        }

        updateSelectedField(updateTutor);

    }

    private void updateSelectedField(Tutor updateTutor) {
        int choice;
        do {
            tutorUI.displaySelectedTutor(updateTutor);
            if (updateTutor == null) {
                return;
            }

            choice = tutorUI.selectAttributeToUpdate(updateTutor);
            switch (choice) {
                case 1:
                    updateTutorName(updateTutor);
                    break;
                case 2:
                    updateTutorGender(updateTutor);
                    break;
                case 3:
                    updateTutorStatus(updateTutor);
                    break;
            }
        } while (choice != 0);
    }

    private void updateTutorName(Tutor updateTutor) {
        String newName = tutorUI.updateTutorName();
        if (newName == null) {
            return;
        }

        updateTutor.setTutorName(newName);
    }

    private void updateTutorGender(Tutor updateTutor) {
        boolean confirmToChange = tutorUI.updateTutorGender(updateTutor.isFemale());
        if (!confirmToChange) {
            return;
        }

        if (updateTutor.isFemale()) {
            updateTutor.setGender('M');
        } else {
            updateTutor.setGender('F');
        }
    }

    private void updateTutorStatus(Tutor updateTutor) {
        String newStatus = tutorUI.updateTutorStatus(updateTutor.getStatus());
        if (newStatus == null) {
            return;
        }

        updateTutor.setStatus(newStatus);
    }
}

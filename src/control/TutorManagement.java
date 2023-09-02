/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.CircularDoublyLinkedList;
import adt.ListInterface;
import boundary.TutorManagementUI;
import dao.*;
import entity.Tutor;
import java.awt.Choice;
import java.util.Iterator;
import utility.*;

/**
 *
 * @author Yip Zi Yan
 */
public class TutorManagement {

    private final String fileName = "tutor.dat";
    DAO tutorDAO = new DAO(fileName);
    TutorManagementUI tutorUI = new TutorManagementUI();
    ListInterface<Tutor> tutorList = new CircularDoublyLinkedList<>();

    TutorSeeder seeder = new TutorSeeder();

    public TutorManagement() {
        tutorDAO.saveToFile(seeder.getTutorList());
        tutorList = tutorDAO.retrieveFromFile();
        Tutor.setTotalTutor(tutorList.getNumberOfEntries());
    }

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
                case 5:
                    filterTutorUI();
                    break;
                case 6:
                    generateReportUI();
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
        int currentId;

        try {
            currentId = Integer.parseInt(tutorList.getLastEntry()
                    .getTutorId().substring(1)) + 1;
        } catch (Exception e) {
            System.out.println("Something went wrong.");
            GeneralUtil.systemPause();
            return;
        }

        do {
            Tutor newTutor = tutorUI.addTutor(currentId);

            if (newTutor != null) {
                tutorList.add(newTutor);
                tutorDAO.saveToFile(tutorList);
            }

        } while (tutorUI.contAction("Anymore To Add? [Y|N] > "));

    }

    private void displayTutorList() {
        tutorUI.displayAllTutor(getAllTutor(), true);
    }

    private String getAllTutor() {
        String outputStr = "";
        int number = 0;

        Iterator<Tutor> it = tutorList.getIterator();
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
        Iterator<Tutor> it = tutorList.getIterator();
        int number = 0;

        while (it.hasNext()) {
            Tutor matchTutor = it.next();
            switch (attribute) {
                case "name":
                    if (matchTutor.getTutorName().toLowerCase().startsWith(query)) {
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
        Iterator<Tutor> it = tutorList.getIterator();

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
                updateTutor = tutorList
                        .getEntry(Integer.parseInt(query) - 1);
            } catch (Exception e) {
            }
            break;
        }

        updateSelectedField(updateTutor);

    }

    private void updateSelectedField(Tutor updateTutor) {
        int choice;
        boolean isRemove = false;
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
                case 4:
                    isRemove = removeTutor(updateTutor);
                    break;
            }
            if (isRemove) {
                tutorUI.removedClosure();
            }
        } while (choice != 0 && !isRemove);
    }

    private void updateTutorName(Tutor updateTutor) {
        String newName = tutorUI.updateTutorName();
        if (newName == null) {
            return;
        }

        updateTutor.setTutorName(newName);
        tutorDAO.saveToFile(tutorList);
    }

    private void updateTutorGender(Tutor updateTutor) {
        boolean confirmToChange = tutorUI
                .updateTutorGender(updateTutor.isFemale());
        if (!confirmToChange) {
            return;
        }

        if (updateTutor.isFemale()) {
            updateTutor.setGender('M');
        } else {
            updateTutor.setGender('F');
        }
        tutorDAO.saveToFile(tutorList);

    }

    private void updateTutorStatus(Tutor updateTutor) {
        String newStatus = tutorUI
                .updateTutorStatus(updateTutor.getStatus());
        if (newStatus == null) {
            return;
        }

        updateTutor.setStatus(newStatus);
        tutorDAO.saveToFile(tutorList);

    }

    private boolean removeTutor(Tutor updateTutor) {
        boolean isDeleted = false;

        if (tutorUI.removeTutor()) {
            isDeleted = tutorList.remove(updateTutor);
            tutorDAO.saveToFile(tutorList);
        }
        return isDeleted;
    }

    private void filterTutorUI() {
        int choice;
        do {
            tutorUI.displayAllTutor(getAllTutor(), false);
            choice = tutorUI.filterTutorMenu();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;

            }
        } while (choice != 0);
    }

    private void generateReportUI() {
        String choice;
        Paginator page = new Paginator(tutorList, 7);
        String currentPage = getPageContent(page.jumpTo(0));
        do {

            if (currentPage == "") {
                currentPage = getPageContent(page.jumpTo(page.currentPage));
            }

            tutorUI.displayAllTutor(currentPage, false);

            System.out.printf("Page No: %-73d < 1 .. %d >\n",
                    page.currentPage + 1, page.pageNumber);
            if (page.isEndOfPage()) {
                MessageUI.displayInfoMessage(String.format("%52s", "END OF PAGES"));
            }

            choice = tutorUI.generateTutorReportMenu().toLowerCase();
            switch (choice) {
                case ">":
                    currentPage = getPageContent(page.nextPage());
                    break;
                case ">|":
                    currentPage = getPageContent(page.toEnd());
                    break;
                case "<":
                    currentPage = getPageContent(page.prevPage());
                    break;
                case "|<":
                    currentPage = getPageContent(page.toStart());
                    break;
                default:
                    if (choice.matches("[0-9]+")) { // is integer
                        currentPage = getPageContent(page.jumpTo(Integer.parseInt(choice) - 1));
                    }else if (!choice.equals("exit")) {
                        System.err.println("Invalid command.");
                        GeneralUtil.systemPause();
                    }
                    break;
            }

        } while (!choice.equals("exit"));
    }

    private String getPageContent(ListInterface<Tutor> list) {
        String outputStr = "";
        try {
            int number = tutorList.indexOf(list.getFirstEntry());
            Iterator<Tutor> it = list.getIterator();
            while (it.hasNext()) {
                outputStr += String.format("%2d.  ", ++number)
                        + it.next() + "\n";

            }
        } catch (Exception e) {
            GeneralUtil.systemPause();
        }

        return outputStr;
    }
}

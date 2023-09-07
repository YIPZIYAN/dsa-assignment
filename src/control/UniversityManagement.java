package control;

import boundary.UniversityManagementUI;
import utility.MessageUI;


/**
 *
 * @author Yip Zi Yan, Goh Chun Yen
 */
public class UniversityManagement {

    private UniversityManagementUI universityUI = new UniversityManagementUI();
    
    public UniversityManagement() {
    
    }
    
    public static void main(String[] args) {
        UniversityManagement universityCtrl = new UniversityManagement();
        universityCtrl.startUI(args);
    }
    
    private void startUI(String[] args) {
        int choice;
        do {
            choice = universityUI.getMenuChoice();
            switch (choice) {
                case 1:
                    TutorManagement.main(args);
                    break;
                case 2:
                    CourseManagement.main(args);
                    break;
                case 0:
                    MessageUI.displayExitMessage();
            }
        } while (choice != 0);
        
    }
}

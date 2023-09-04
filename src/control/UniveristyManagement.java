package control;

import boundary.UniversityManagementUI;
import utility.MessageUI;


/**
 *
 * @author Yip Zi Yan, Goh Chun Yen
 */
public class UniveristyManagement {

    private UniversityManagementUI universityUI = new UniversityManagementUI();
    
    public UniveristyManagement() {
    
    }
    
    public static void main(String[] args) {
        UniveristyManagement universityCtrl = new UniveristyManagement();
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

package boundary;

import utility.CustomScanner;
import utility.GeneralUtil;

/**
 *
 * @author Cy
 */
public class UniversityManagementUI {
    
     CustomScanner cScan = new CustomScanner();
     
    //UNIVERISTY MANAGEMENT MENU
    public int getMenuChoice() {

        GeneralUtil.clearScreen();
        System.out.println("TARUMT University Management");
        System.out.println("----------------------------");
        System.out.println(
                  "1. Tutor Management\n"
                + "2. Course Management\n"
                + "0. Quit");
        System.out.println("----------------------------");

        int choice = cScan.inputInt("Enter Selection > ", 0, 2);
        return choice;
    }
    
}

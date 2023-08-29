/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.util.*;

/**
 *
 * @author Yip Zi Yan
 */
public class CustomScanner {

    Scanner scan = new Scanner(System.in);

    public void clearBuffer() {
        scan.nextLine();
    }

    public String inputString(String str) {
        System.out.print(str);
        return scan.nextLine();
    }

    public char inputChar(String str) {
        System.out.print(str);
        return scan.nextLine().charAt(0);
    }

    public char inputChar(String str, String errorMsg, char[] checkChar) {
        char output;
        boolean error = true;

        do {
            output = this.inputChar(str);
            for (char ch : checkChar) {
                if (Character.toUpperCase(output) == ch) {
                    error = false;
                    break;
                }
            }

            if (error) {
                System.err.println(errorMsg);
            }
        } while (error);

        return output;
    }

    public int inputInt(String str) {
        int output = -999;
        boolean error;

        do {
            error = false;
            System.out.print(str);
            try {
                output = scan.nextInt();
            } catch (InputMismatchException ex) {
                System.err.println("Please enter number only.");
                scan.next(); //clear buffer
                error = true;
            }
        } while (error);

        this.clearBuffer();
        return output;
    }

    public int inputInt(String str, int min, int max) { //with constraint
        boolean error;
        int output = -999;

        do {
            error = false;
            output = this.inputInt(str);
            if (output < min || output > max) {
                error = true;
                System.err.println("Please enter number between [" + min
                        + "~" + max + "].");
            }
        } while (error);

        return output;
    }

    public boolean confimation() {
        char[] checkCh = {'Y', 'N'};
        char ch = inputChar("\n[Confirmation]\n [Y = yes N = no]\nAre You Sure? > ",
                "Please enter [Y] or [N].", checkCh);
        if (Character.toUpperCase(ch) == 'Y') {
            return true;
        }
        return false;
    }
}

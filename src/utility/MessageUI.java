package utility;

/**
 *
 * @author Kat Tan
 */
public class MessageUI {

    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static void displayInvalidChoiceMessage() {
        System.out.println("\nInvalid choice");
    }

    public static void displayExitMessage() {
        System.out.println(PURPLE + "\n     TARUMT      " + RESET);
        System.out.println(CYAN + "Beyond Education!\n" + RESET);
    }

    public static void displayNoResultMessage() {
        System.out.println(CYAN + "\nNo Result Found." + RESET);
    }

    public static void displayInfoMessage(String str) {
        System.out.println(YELLOW + str + RESET);
    }

}

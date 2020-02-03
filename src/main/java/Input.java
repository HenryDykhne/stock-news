import java.util.Scanner;

public class Input {

    private Scanner scanner;

    /**
     * Create the user input manager.
     */
    public Input() {
        scanner = new Scanner(System.in);
    }

    /**
     * Print out text for the player.
     * @return String text the text to display
     */
    public String getUserInput() {
        return scanner.nextLine();
    }
}

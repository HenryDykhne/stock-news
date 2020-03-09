package stockNews.ioUtilities;

import java.util.Scanner;

//This abstraction is designed to make switching imput streams more simple.
public class Input {

    private Scanner scanner;

    public Input() {
        scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.nextLine();
    }
}

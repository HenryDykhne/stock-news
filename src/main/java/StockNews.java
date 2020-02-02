/**
 * This is an app to view stocks and relevant news.
 *
 * @version 0.1
 */
final class StockNews {
    private User user;
    private Display display;
    private Input input;
    private CSVParser csvParser;

    /**
     * controller class for stock news
     **/
    private StockNews() {
        user = new User();
        display = new Display();
        input = new Input();
        csvParser = new CSVParser();
    }

    //private void setup(){ }

    /**
     *
     * @return String of viable commands
     */
    private String getHelpText() {
        String helpText = "";
        helpText += "Valid Commands:\n";
        helpText += "add blacklist\n";
        helpText += "show blacklists\n";
        helpText += "help\n";
        helpText += "exit\n";
        return helpText;
    }

    private void run() {
        display.showUser("StockNews:");

        String command = "";
        display.showUser(getHelpText());

        while (!command.equalsIgnoreCase("exit")) {
            command = input.getUserInput();
            switch (command) {
                case "add blacklist":
                    display.showUser(user.importBlackList());
                    break;
                case "show blacklists":
                    display.showUser(user.blackListNamesToString());
                    break;
                case "help":
                    display.showUser(getHelpText());
                    break;
                case "exit":
                    display.showUser("Exiting");
                    break;
                default:
                    display.showUser("Not a valid command.\n" + getHelpText());
                    break;
            }

        }
    }

    /**
     * Main method.
     * @param args command line arguments
     **/
    public static void main(String[] args) {
        StockNews app = new StockNews();
        //app.setup();
        app.run();
    }

}

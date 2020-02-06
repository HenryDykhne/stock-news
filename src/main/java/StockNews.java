import news.pojo.NewsJSON;

import java.util.ArrayList;

/**
 * This is an app to view stocks and relevant news.
 *
 * @version 0.1
 */
final class StockNews {
    private User user;
    private Display display;
    private Input input;
    private NewsApi newsApi;

    /**
     * controller class for stock news
     **/
    private StockNews() {
        user = new User();
        display = new Display();
        input = new Input();
        newsApi = new NewsApi();
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
        helpText += "check news\n";
        helpText += "help\n";
        helpText += "exit\n";
        return helpText;
    }

    /**
     * runs the operation loop for the app
     */
    private void run() {
        display.showUser("StockNews:");

        String command = "";
        display.showUser(getHelpText());

        while (!command.equalsIgnoreCase("exit")) {
            command = input.getUserInput();
            switch (command) {
                case "add blacklist":
                    try {
                        user.addBlackList(Blacklist.importBlackList());
                        display.showUser("Success");
                    } catch (Exception e) {
                        display.showUser("Error. Check your file.");
                    }
                    break;
                case "show blacklists":
                    display.showUser(user.blacklistListToString());
                    break;
                case "check news":
                    try {
                        ArrayList<String> testSearchList = new ArrayList<>();
                        testSearchList.add("apple");
                        NewsJSON newsJSON = newsApi.mapNewsJSONToPoJo(newsApi.getNewsInfo(testSearchList));
                        display.showUser("output:" + newsJSON.getArticles().get(0).getTitle());

                    } catch (Exception  e) {
                        display.showUser("Error: " + e);
                    }
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

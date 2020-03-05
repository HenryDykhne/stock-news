package stockNews;

import stockNews.newsPojo.Article;
import stockNews.newsPojo.NewsJSON;
import stockNews.roles.Actor;
import stockNews.roles.Admin;
import stockNews.roles.User;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class App {
    private Actor actor;
    private Display display;
    private Input input;
    private NewsApi newsApi;
    private Map<String, Stock> stocks;

    App() {
        display = new Display();
        input = new Input();
        newsApi = new NewsApi();
        stocks = new HashMap<>();
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public String getHelpText() {
        String helpText = "";
        helpText += "Valid Commands:\n";
        helpText += "add blacklists\n";
        helpText += "show blacklists\n";
        helpText += "activate blacklist\n";
        helpText += "deactivate blacklist\n";
        helpText += "add to blacklist\n";
        helpText += "remove from blacklist\n";
        helpText += "create blacklist\n";
        helpText += "check news\n";
        helpText += "add stocks (admin privileges required)\n";
        helpText += "show stocks\n";
        helpText += "help\n";
        helpText += "exit\n";
        return helpText;
    }

    public void setup() {
        display.showUser("StockNews:");
        display.showUser(("Be admin for demonstration (Gives full permissions)? (y/n):"));
        if (input.getUserInput().equalsIgnoreCase("y")) {
            actor = new Admin();
        } else {
            actor = new User();
        }
    }

    ///this method is only intended to demo the applications capabilities and thus breaks single responsibility.
    //CHECKSTYLE:OFF
    public void run() {
        String command = "";
        String chosenBlacklist;
        String word;
        display.showUser(getHelpText());
        while (!command.equalsIgnoreCase("exit")) {
            command = input.getUserInput();
            switch (command) {
                case "add blacklists":
                    display.showUser(addBlacklist());
                    break;
                case "show blacklists":
                    display.showUser(actor.blacklistsToString());
                    break;
                case "activate blacklist":
                    display.showUser("Enter the name of the blacklist you would like to activate: ");
                    display.showUser(setBlacklistActivation(input.getUserInput(), true));
                    break;
                case "deactivate blacklist":
                    display.showUser("Enter the name of the blacklist you would like to deactivate: ");
                    display.showUser(setBlacklistActivation(input.getUserInput(), false));
                    break;
                case "add to blacklist":
                    display.showUser("Enter the name of the blacklist you would like to add to: ");
                    chosenBlacklist = input.getUserInput();
                    display.showUser("Enter the text to add: ");
                    word = input.getUserInput();
                    display.showUser(addToBlacklist(chosenBlacklist,  word));
                    break;
                case "remove from blacklist":
                    display.showUser("Enter the name of the blacklist you would like to remove from: ");
                    chosenBlacklist = input.getUserInput();
                    display.showUser("Enter the text to remove: ");
                    word = input.getUserInput();
                    display.showUser(removeFromBlacklist(chosenBlacklist,  word));
                    break;
                case "create blacklist":
                    display.showUser("Enter the name of the new blacklist: ");
                    display.showUser(createBlacklist(input.getUserInput()));
                    break;
                case "check news":
                    display.showUser("Enter the stock you want to view");
                    display.showUser(checkNews(input.getUserInput()));
                    break;
                case "add stocks":
                    display.showUser(addStocks());
                    break;
                case "show stocks":
                    display.showUser(showStocks());
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
    //CHECKSTYLE:ON

    public String createBlacklist(String name) {
        actor.addBlacklist(new Blacklist(name));
        return "Success";
    }

    public String addToBlacklist(String chosenBlacklist, String word) {
        try {
            actor.getBlackLists().get(chosenBlacklist).addWord(word);
            return "Success";
        } catch (Exception e) {
            return "Chosen blacklist not found.";
        }
    }

    public String removeFromBlacklist(String chosenBlacklist, String word) {
        try {
            actor.getBlackLists().get(chosenBlacklist).removeWord(word);
            return "Success";
        } catch (Exception e) {
            return "Chosen blacklist not found.";
        }
    }

    public String setBlacklistActivation(String chosenBlacklist, Boolean activate) {
        try {
            actor.getBlackLists().get(chosenBlacklist).setActive(activate);
            return "Success";
        } catch (Exception e) {
            return "Unable to find selected blacklist.";
        }
    }

    public String addBlacklist() {
        try {
            actor.addBlacklists(Blacklist.importBlacklists());
            return "Success";
        } catch (Exception e) {
            return "Error. Check your file.";
        }
    }

    public String checkNews(String name) {
        try {
            if (stocks.get(name) == null) {
                throw new Exception("Stock does not exist.");
            }
            List<String> searchTermsList = stocks.get(name).getNewsKeywords();
            NewsJSON newsJSON = newsApi.mapNewsJSONToPoJo(newsApi.getNewsInfo(searchTermsList));

            //blacklisted articles have a bool value of false
            Map<Article, Boolean> filteredNews = NewsFilter.filterNews(newsJSON, actor.getBlackLists().values());
            StringBuilder text = new StringBuilder();
            text.append("Filtered Articles:\n");
            for (Article article : filteredNews.keySet()) {
                if (filteredNews.get(article)) {
                    text.append("(Trustworthy)").append(article.getUrl()).append("\n");
                } else {
                    text.append("(Untrustworthy)").append(article.getUrl()).append("\n");
                }
            }
            return text.toString();
        } catch (Exception  e) {
            return "Error: " + e;
        }
    }

    public String addStocks() {
        try {
            if (actor.addStocksFromFile(stocks)) {
                return "Stocks added.";
            } else {
                return "Not enough permissions";
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "Something went wrong. We could not add your stocks.";
        }
    }

    public String showStocks() {
        StringBuilder text = new StringBuilder();
        for (String key : stocks.keySet()) {
            text.append(key).append("\n");
        }
        return text.toString();
    }

    public static void main(String[] args) {
        App app = new App();
        app.setup();
        app.run();
    }
}

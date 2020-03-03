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

    private App() {
        display = new Display();
        input = new Input();
        newsApi = new NewsApi();
        stocks = new HashMap<>();
    }

    public String getHelpText() {
        String helpText = "";
        helpText += "Valid Commands:\n";
        helpText += "add blacklists\n";
        helpText += "show blacklists\n";
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
        //I could not figure out how to correctly structure admin so that I could
        //store it and user in one variable and user whichever methods when nescesary.
        if (input.getUserInput().equalsIgnoreCase("y")) {
            actor = new Admin();
        } else {
            actor = new User();
        }
    }

    ///this method is only intended to demo the applications capabilities and thus breaks single responsibility.
    public void run() {
        String command = "";
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
                case "check news":
                    display.showUser("Please enter the stock you want to view");
                    checkNews(input.getUserInput());
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

    public String addBlacklist() {
        try {
            actor.addBlacklists(Blacklist.importBlacklists());
            return "Success";
        } catch (Exception e) {
            return "Error. Check your file.";
        }
    }

    public void checkNews(String name) {
        try {
            if (stocks.get(name) == null) {
                throw new Exception("Stock does not exist.");
            }
            List<String> searchTermsList = stocks.get(name).getNewsKeywords();
            NewsJSON newsJSON = newsApi.mapNewsJSONToPoJo(newsApi.getNewsInfo(searchTermsList));

            //blacklisted articles have a bool value of false
            Map<Article, Boolean> filteredNews = NewsFilter.filterNews(newsJSON, actor.getBlackLists().values());
            display.showUser("Filtered Articles:");
            for (Article article : filteredNews.keySet()) {
                if (filteredNews.get(article)) {
                    display.showUser("(Trustworthy)" + article.getUrl());
                } else {
                    display.showUser("(Untrustworthy)" + article.getUrl());
                }
            }
        } catch (Exception  e) {
            display.showUser("Error: " + e);
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

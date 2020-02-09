import news.pojo.Article;
import news.pojo.NewsJSON;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class StockNews {
    private User actor;
    private Admin admin;
    private Display display;
    private Input input;
    private NewsApi newsApi;
    private Map<String, Stock> stocks;

    private StockNews() {
        actor = new User();
        display = new Display();
        input = new Input();
        newsApi = new NewsApi();
        stocks = new HashMap<>();
    }

    private String getHelpText() {
        String helpText = "";
        helpText += "Valid Commands:\n";
        helpText += "add blacklist\n";
        helpText += "show blacklists\n";
        helpText += "check news\n";
        helpText += "add stock (admin privileges required)\n";
        helpText += "help\n";
        helpText += "exit\n";
        return helpText;
    }

    private void setup() {
        display.showUser("StockNews:");
        display.showUser(("Be admin for demonstration (Gives full permissions)? (y/n):"));
        //I could not figure out how to correctly structure admin so that I could
        //store it and user in one variable and user whichever methods when nescesary.
        if (input.getUserInput().equalsIgnoreCase("y")) {
            admin = new Admin();
        }
    }

    ///this method is only intended to demo the applications capabilities and thus breaks single responsibility.
    private void run() {
        String command = "";
        display.showUser(getHelpText());

        while (!command.equalsIgnoreCase("exit")) {
            command = input.getUserInput();
            switch (command) {
                case "add blacklist":
                    addBlacklist();
                    break;
                case "show blacklists":
                    showBlacklists();
                    break;
                case "check news":
                    checkNews();
                    break;
                case "add stock":
                    addStock();
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

    private void addBlacklist() {
        try {
            actor.addBlackList(Blacklist.importBlackList());
            display.showUser("Success");
        } catch (Exception e) {
            display.showUser("Error. Check your file.");
        }
    }

    private void checkNews() {
        try {
            List<String> searchTermsList = stocks.get(input.getUserInput()).getNewsKeywords();
            NewsJSON newsJSON = newsApi.mapNewsJSONToPoJo(newsApi.getNewsInfo(searchTermsList));
            List<Article> filteredNews = NewsFilter.filterNews(newsJSON, actor.getBlackLists().values());
            display.showUser("output:" + filteredNews.get(0).getUrl());

        } catch (Exception  e) {
            display.showUser("Error: " + e);
        }
    }

    private void showBlacklists() {
        display.showUser(actor.blacklistListToString());
    }

    private void addStock() {
        if (admin != null) {
            try {
                admin.addStocksFromFile(stocks);
                display.showUser("Stocks added.");
            } catch (IOException e) {
                e.printStackTrace();
                display.showUser("Something went wrong. We could not add your stocks.");
            }
        } else {
            display.showUser("Not enough permissions.");
        }
    }

    public static void main(String[] args) {
        StockNews app = new StockNews();
        app.setup();
        app.run();
    }

}

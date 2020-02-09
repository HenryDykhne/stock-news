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
        helpText += "add blacklists\n";
        helpText += "show blacklists\n";
        helpText += "check news\n";
        helpText += "add stocks (admin privileges required)\n";
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
                case "add blacklists":
                    addBlacklist();
                    break;
                case "show blacklists":
                    showBlacklists();
                    break;
                case "check news":
                    checkNews();
                    break;
                case "add stocks":
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
            actor.addBlacklists(Blacklist.importBlacklists());
            display.showUser("Success");
        } catch (Exception e) {
            display.showUser("Error. Check your file.");
        }
    }

    private void checkNews() {
        display.showUser("Please enter the stock you want to view");
        String name = input.getUserInput();
        try {
            List<String> searchTermsList = stocks.get(name).getNewsKeywords();
            NewsJSON newsJSON = newsApi.mapNewsJSONToPoJo(newsApi.getNewsInfo(searchTermsList));

            //blacklisted articles have a bool value of false
            Map<Article, Boolean> filteredNews = NewsFilter.filterNews(newsJSON, actor.getBlackLists().values());
            display.showUser("Filtered Articles:");
            for(Article article : filteredNews.keySet()){
                if(filteredNews.get(article)){
                    display.showUser("(Trustworthy)" + article.getUrl());
                } else {
                    display.showUser("(Untrustworthy)" + article.getUrl());
                }
            }
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

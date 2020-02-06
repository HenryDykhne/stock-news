import news.pojo.Article;
import news.pojo.NewsJSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class StockNews {
    private User user;
    private Display display;
    private Input input;
    private NewsApi newsApi;
    private Map<String, Stock> stockmap;

    private StockNews() {
        user = new User();
        display = new Display();
        input = new Input();
        newsApi = new NewsApi();
        stockmap = new HashMap<>();
    }

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
                        List<Article> filteredNews = NewsFilter.filterNews(newsJSON, user.getBlackLists().values());
                        display.showUser("output:" + filteredNews.get(0).getUrl());

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

    public static void main(String[] args) {
        StockNews app = new StockNews();
        app.run();
    }

}

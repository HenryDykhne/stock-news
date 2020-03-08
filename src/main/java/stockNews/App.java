package stockNews;

import stockNews.newsPojo.Article;
import stockNews.newsPojo.NewsJSON;
import stockNews.roles.Actor;
import stockNews.roles.Admin;
import stockNews.roles.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
        helpText += "import stocks (admin privileges required)\n";
        helpText += "show stocks\n";
        helpText += "save stocks\n";
        helpText += "load stocks\n";
        helpText += "save profile\n";
        helpText += "load profile\n";
        helpText += "help\n";
        helpText += "exit\n";
        return helpText;
    }

    public void setup() {
        display.showUser("StockNews:");
        String command;
        String name;

        while (actor == null) {
            display.showUser(("Enter as new user, new admin, or load existing profile? (U/A/L): "));
            command = input.getUserInput();
            display.showUser("Enter a name:");
            name = input.getUserInput();
            if (command.equalsIgnoreCase("A")) {
                actor = new Admin(name);
            } else if (command.equalsIgnoreCase("U")) {
                actor = new User(name);
            } else {
                display.showUser(loadActor(name));
            }
        }
        display.showUser(loadStocks());
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
                case "import stocks":
                    display.showUser(importStocks());
                    break;
                case "show stocks":
                    display.showUser(showStocks());
                    break;
                case "save stocks":
                    display.showUser(saveStocks());
                    break;
                case "load stocks":
                    display.showUser(loadStocks());
                    break;
                case "save profile":
                    display.showUser(saveProfile());
                    break;
                case "load profile":
                    display.showUser("Enter the name of the profile to be loaded: ");
                    display.showUser(loadActor(input.getUserInput()));
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

    private String saveProfile() {
        try {
            actor.save();
            return "Success";
        } catch (IOException e) {
            return "Failed to save";
        }
    }

    public String loadActor(String name) {
        try {
            Object object = Actor.load(name);
            display.showUser(object.getClass().getName());
            if (object.getClass().getName().equals("stockNews.roles.Admin")) {
                actor = (Admin) object;
            } else if (object.getClass().getName().equals("stockNews.roles.User")) {
                actor = (User) object;
            } else {
                throw new RuntimeException(); //add unable to read actor exception here
            }
            return "Success";
        } catch (IOException e) {
            return "Load Failed: IO Exception";
        } catch (ClassNotFoundException e) {
            return "Load Failed: Class not found.";
        } catch (RuntimeException e) {
            return "Load Failed: " + e;
        }
    }

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

    public String importStocks() {
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

    public String saveStocks() {
        try {
            FileOutputStream fout = new FileOutputStream("stockStorage/stocks");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(stocks);
            out.flush();
            out.close();
            return "Stocks saved";
        } catch (FileNotFoundException e) {
            return "Stocks not saved: File not found";
        } catch (IOException e) {
            return "Stocks not saved: IO exception";
        }
    }

    @SuppressWarnings("unchecked")
    public String loadStocks() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("stockStorage/stocks"));
            stocks = (HashMap<String, Stock>) in.readObject();
            in.close();
            return "Success: Stocks loaded";
        } catch (FileNotFoundException e) {
            return "Stocks not loaded: File not found";
        } catch (IOException e) {
            return "Stocks not saved: IO exception";
        } catch (ClassNotFoundException e) {
            return "Stocks not saved";
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

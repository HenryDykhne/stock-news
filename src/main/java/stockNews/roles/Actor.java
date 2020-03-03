package stockNews.roles;

import stockNews.Blacklist;
import stockNews.Stock;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class Actor {
    private Map<String, Blacklist> blacklists;

    public Actor() {
        blacklists = new HashMap<>();
    }

    public Map<String, Blacklist> getBlackLists() {
        return blacklists;
    }

    public void setBlackLists(Map<String, Blacklist> blacklists) {
        this.blacklists = blacklists;
    }

    public void addBlacklists(Map<String, Blacklist> blacklists) {
        this.blacklists.putAll(blacklists);
    }

    public String blacklistsToString() {
        StringBuilder text = new StringBuilder();
        for (String key : blacklists.keySet()) {
            text.append(key).append(": ").append(String.join(", ",
                    blacklists.get(key).getRestrictedText())).append("\n");
        }
        return text.toString();
    }

    public abstract Boolean addStocksFromFile(Map<String, Stock> stocks) throws IOException;
}

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

    public void addBlacklist(Blacklist blacklist) {
        this.blacklists.put(blacklist.getName(), blacklist);
    }

    public String blacklistsToString() {
        StringBuilder text = new StringBuilder();
        for (Blacklist blacklist : blacklists.values()) {
            text.append(blacklist.toString()).append("\n");
        }
        return text.toString();
    }

    public abstract Boolean addStocksFromFile(Map<String, Stock> stocks) throws IOException;
}

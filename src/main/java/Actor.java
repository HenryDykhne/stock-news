import java.io.IOException;
import java.util.Map;

public abstract class Actor {
    private Map<String, Blacklist> blacklists;

    Map<String, Blacklist> getBlackLists() {
        return blacklists;
    }

    void setBlackLists(Map<String, Blacklist> blacklists) {
        this.blacklists = blacklists;
    }

    void addBlacklists(Map<String, Blacklist> blacklists) {
        this.blacklists.putAll(blacklists);
    }

    String blacklistsToString() {
        StringBuilder text = new StringBuilder();
        for (String key : blacklists.keySet()) {
            text.append(key).append(": ").append(String.join(", ",
                    blacklists.get(key).getRestrictedText())).append("\n");
        }
        return text.toString();
    }

    abstract Boolean addStocksFromFile(Map<String, Stock> stocks) throws IOException;
}

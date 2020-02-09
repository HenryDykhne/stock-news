
import java.util.HashMap;
import java.util.Map;

public class User extends Actor {

    private Map<String, Blacklist> blacklists;

    public User() {
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

    public Boolean addStocksFromFile(Map<String, Stock> stocks) {
        return false;
    }

}

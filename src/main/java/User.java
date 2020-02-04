
import java.util.HashMap;
import java.util.Map;

/**
 * The type User.
 */
public class User {

    private Map<String, Blacklist> blacklists;

    /**
     * creating a new default user
     */
    public User() {
        blacklists = new HashMap<>();
    }

    /**
     * Gets black lists.
     *
     * @return blacklists Map<String, Blacklist>  A filename, string[] map.
     */
    public Map<String, Blacklist> getBlackLists() {
        return blacklists;
    }


    /**
     * Sets black lists.
     *
     * @param blacklists the map of blacklists
     */
    public void setBlackLists(Map<String, Blacklist> blacklists) {
        this.blacklists = blacklists;
    }

    /**
     * Add black list.
     *
     * @param blacklist     the black list
     */
    public void addBlackList(Blacklist blacklist) {
        this.blacklists.put(blacklist.getName(), blacklist);
    }

    /**
     * Prints the list of blacklists.
     *
     * @return the string
     */
    public String blacklistListToString() {
        StringBuilder text = new StringBuilder();
        for (String key : blacklists.keySet()) {
           text.append(key).append(": ").append(String.join(", ", blacklists.get(key).getRestrictedText())).append("\n");
        }
        return text.toString();
    }


}

package stockNews.roles;

import stockNews.Blacklist;
import stockNews.Stock;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

public abstract class Actor implements Serializable {
    private String name;
    private Map<String, Blacklist> blacklists;

    public Actor(String name) {
        blacklists = new HashMap<>();
        this.name = name;
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

    public void save() throws IOException {
        FileOutputStream fout = new FileOutputStream("actorStorage/"+ name +".actor");
        ObjectOutputStream out = new ObjectOutputStream(fout);
        out.writeObject(this);
        out.flush();
        out.close();
    }

    public static Object load(String name) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("actorStorage/" + name + ".actor"));
        Object object = in.readObject();
        in.close();
        return object;
    }

    public abstract Boolean addStocksFromFile(Map<String, Stock> stocks) throws IOException;
}

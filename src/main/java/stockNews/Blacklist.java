package stockNews;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Blacklist implements Serializable {
    private String name;
    private List<String> restrictedText;
    private boolean active;

    public Blacklist(String name) {
        this.name = name;
        this.restrictedText = new ArrayList<>();
        this.active = false;
    }

    public Blacklist(String name, String[] list) {
        this(name);
        this.restrictedText = new ArrayList<>(Arrays.asList(list));
    }

    public Blacklist(String name, String[] list, Boolean active) {
        this(name, list);
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRestrictedText() {
        return restrictedText;
    }

    public void setRestrictedText(String[] restrictedText) {
        this.restrictedText = new ArrayList<>(Arrays.asList(restrictedText));
    }

    public static Map<String, Blacklist> importBlacklists() throws Exception {
        DataImporter importer = new CSVImporter();
        //add line to list of blacklists
        importer.importData();
        Map<String, Blacklist> newBlacklists = new HashMap<>();
        for (String[] row: importer.parse()) {
            if (row != null && row.length >= 2) {
                newBlacklists.put(row[0], new Blacklist(row[0], Arrays.copyOfRange(row, 1, row.length)));
            }
        }
        return newBlacklists;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return name + ", restrictedText: "
                + Arrays.toString(restrictedText.toArray())
                + ", active: " + active;
    }

    public void addWord(String word) {
        restrictedText.add(word);
    }

    public void removeWord(String word) {
        restrictedText.remove(word);
    }
}

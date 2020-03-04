package stockNews;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Blacklist {
    private String name;
    private String[] restrictedText;
    private boolean active;

    public Blacklist(String name, String[] list) {
        this.name = name;
        this.restrictedText = list;
        this.active = false;
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

    public String[] getRestrictedText() {
        return restrictedText;
    }

    public void setRestrictedText(String[] restrictedText) {
        this.restrictedText = restrictedText;
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
                + Arrays.toString(restrictedText)
                + ", active: " + active;
    }
}

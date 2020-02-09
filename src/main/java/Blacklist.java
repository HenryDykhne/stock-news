import java.io.File;

public class Blacklist {
    private String name;
    private String[] restrictedText;
    private boolean active;

    public Blacklist(String name, String[] list) {
        this.name = name;
        this.restrictedText = list;
        this.active = true;
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

    public static Blacklist importBlackList() throws Exception {
        DataImporter importer = new CSVImporter();
        File file = FileChooser.selectFile();
        //add line to list of blacklists
        importer.importData();
        return new Blacklist(file.getName(), importer.parse().get(0));

    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

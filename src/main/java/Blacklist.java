import java.io.File;
import javax.swing.JFileChooser;

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
        File file;
        final JFileChooser fc = new JFileChooser();
        // Opening the dialog using null as parent component
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            // Retrieve the selected file
            file = fc.getSelectedFile();

            //add line to list of blacklists
            return new Blacklist(file.getName(), CSVParser.readFullCSV(file).get(0));
        } else {
            throw new Exception();
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

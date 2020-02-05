import java.io.File;
import javax.swing.JFileChooser;

/**
 * The type Blacklist.
 */
public class Blacklist {
    private String name;
    private String[] restrictedText;

    /**
     * Instantiates a new Blacklist.
     *
     * @param name the name
     * @param list the list
     */
    public Blacklist(String name, String[] list) {
        this.name = name;
        this.restrictedText = list;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get restricted text string [ ].
     *
     * @return the string [ ]
     */
    public String[] getRestrictedText() {
        return restrictedText;
    }

    /**
     * Sets restricted text.
     *
     * @param restrictedText the restricted text
     */
    public void setRestrictedText(String[] restrictedText) {
        this.restrictedText = restrictedText;
    }

    /**
     * Import black list string.
     *
     * @return the string
     * @throws Exception the exception
     */
    public static Blacklist importBlackList() throws Exception {
        CSVParser csvParser = new CSVParser();
        File file;
        final JFileChooser fc = new JFileChooser();
        // Opening the dialog using null as parent component
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            // Retrieve the selected file
            file = fc.getSelectedFile();

            //add line to list of blacklists
            return new Blacklist(file.getName(), csvParser.readFullCSV(file).get(0));
        } else {
            throw new Exception();
        }
    }

}


import javax.swing.JFileChooser;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * The type User.
 */
public class User {

    private Map<String, String[]> blackLists;

    /**
     * creating a new default user
     */
    public User() {
        blackLists = new HashMap<>();
    }

    /**
     * Gets black lists.
     *
     * @return blacklists Map<String, String [ ]>  A filename, string[] map.
     */
    public Map<String, String[]> getBlackLists() {
        return blackLists;
    }


    /**
     * Sets black lists.
     *
     * @param blackLists the map of blacklists
     */
    public void setBlackLists(Map<String, String[]> blackLists) {
        this.blackLists = blackLists;
    }

    /**
     * Add black list.
     *
     * @param blackListName the black list name
     * @param blackList     the black list
     */
    public void addBlackList(String blackListName, String[] blackList) {
        this.blackLists.put(blackListName, blackList);
    }

    /**
     * Black list names to string string.
     *
     * @return the string
     */
    public String blackListNamesToString() {
        return getBlackLists().keySet().toString();
    }

    /**
     * Import black list string.
     *
     * @return the string
     */
    public String importBlackList() {
        CSVParser csvParser = new CSVParser();
        File file;
        try {
            final JFileChooser fc = new JFileChooser();
            // Opening the dialog using null as parent component
            int returnVal = fc.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                // Retrieve the selected file
                file = fc.getSelectedFile();

                //add line to list of blacklists
                addBlackList(file.getName(), csvParser.readFullCSV(file).get(0));
                return "Success: \nImported file: " + file.getName() + "\n";
            }
            return "Operation Canceled\n";
        } catch (Exception e) {
            return "Failure: \n" + e + "\n";
        }
    }

}

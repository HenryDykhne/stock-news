package stockNews.ioUtilities;

import javax.swing.JFileChooser;
import java.io.IOException;

public final class FileChooser {

    private FileChooser() {
        //notToBe instantiated
    }

    public static java.io.File selectFile() throws IOException {
        final JFileChooser fc = new JFileChooser();
        // Opening the dialog using null as parent component
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            // Retrieve the selected file
            return fc.getSelectedFile();
        } else {
            throw new IOException();
        }
    }

}

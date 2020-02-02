import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * The type Csv parser.
 */
public class CSVParser {

    /**
     * Gets the first and last name of this Student.
     *
     * @param file the file that needs to be read
     * @return List<String [ ]>  A csv as a list of strings.
     * @throws IOException the io exception
     */
    public List<String[]> readFullCSV(File file) throws IOException {
        // Create an object of file reader
        // class with CSV file as a parameter.
        FileReader filereader = new FileReader(file);

        CSVReader csvReader = new CSVReaderBuilder(filereader).build();
        return csvReader.readAll();
    }
}

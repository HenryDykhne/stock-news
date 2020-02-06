import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public final class CSVParser {

    private CSVParser() {
        //notToBe instantiated
    }

    public static List<String[]> readFullCSV(File file) throws IOException {
        FileReader filereader = new FileReader(file);

        CSVReader csvReader = new CSVReaderBuilder(filereader).build();
        return csvReader.readAll();
    }
}

package stockNews;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import stockNews.ioUtilities.FileChooser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public final class CSVImporter implements DataImporter {
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void importData() throws IOException {
        setFile(FileChooser.selectFile());
    }

    public List<String[]> parse() throws IOException {
        FileReader filereader = new FileReader(getFile());
        CSVReader csvReader = new CSVReaderBuilder(filereader).build();
        return csvReader.readAll();
    }
}

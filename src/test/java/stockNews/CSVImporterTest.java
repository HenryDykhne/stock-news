package stockNews;

import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CSVImporterTest {
    CSVImporter importer;
    File file;
    String testFileName;
    String expectedString;
    String returnedToString;
    List<String[]> returnedList;
    List<String[]> expectedList;

    @Before
    public void setUp() {
        expectedString = "";
        returnedToString = "";
        expectedList = new ArrayList<>();
        importer = new CSVImporter();
        testFileName = "blacklists.csv";
        file = new File("testFiles/" + testFileName);
    }

    @Test
    public void parseTest() throws IOException {
        importer.setFile(file);
        expectedList.add(new String[]{"obviousTestBlacklist", "breitbart.com", "clickhole.com", "lifehacker.com", "theonion.com"});
        expectedList.add(new String[]{"slightlyDifferentBlacklist","cracked.com","rt.com"});
        returnedList = importer.parse();
        for(String[] arr: expectedList){
           expectedString += Arrays.toString(arr);
        }
        for(String[] arr: returnedList){
            returnedToString += Arrays.toString(arr);
        }
        assertEquals(expectedString, returnedToString);
    }
}
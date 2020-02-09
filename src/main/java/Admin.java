import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Admin extends User {
    public void addStocksFromFile(Map<String, Stock> stocks) throws IOException {
        DataImporter importer = new CSVImporter();
        importer.importData();
        List<String[]> rawStockInfo = importer.parse();
        for (String[] row : rawStockInfo) {
            stocks.put(row[0], new Stock(row[0], Arrays.asList(Arrays.copyOfRange(row, 1, row.length))));
        }
    }
}

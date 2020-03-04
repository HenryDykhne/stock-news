package stockNews.roles;

import stockNews.CSVImporter;
import stockNews.DataImporter;
import stockNews.Stock;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Admin extends Actor {

    public Admin() {
        super();
    }

    public Boolean addStocksFromFile(Map<String, Stock> stocks) throws IOException {
        DataImporter importer = new CSVImporter();
        importer.importData();
        List<String[]> rawStockInfo = importer.parse();
        for (String[] row : rawStockInfo) {
            stocks.put(row[0], new Stock(row[0], Arrays.asList(Arrays.copyOfRange(row, 1, row.length))));
        }
        return true;
    }
}
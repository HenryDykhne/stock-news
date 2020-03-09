package stockNews.roles;

import stockNews.Stock;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Admin extends Actor {

    public Admin(String name) {
        super(name);
    }

    public void addStocksFromRawData(Map<String, Stock> stocks, List<String[]> rawStockInfo) {
        for (String[] row : rawStockInfo) {
            stocks.put(row[0], new Stock(row[0], Arrays.asList(Arrays.copyOfRange(row, 1, row.length))));
        }
    }
}

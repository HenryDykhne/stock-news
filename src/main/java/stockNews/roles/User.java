package stockNews.roles;

import stockNews.Stock;
import java.util.List;
import java.util.Map;

public class User extends Actor {

    public User(String name) {
        super(name);
    }

    public void addStocksFromRawData(Map<String, Stock> stocks, List<String[]> rawStockInfo) {
        throw new InvalidPrivilageException("User does not have the right to add stocks.");
    }
}

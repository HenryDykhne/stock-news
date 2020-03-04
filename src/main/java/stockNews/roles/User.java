package stockNews.roles;

import stockNews.Stock;
import java.util.Map;

public class User extends Actor {

    public User() {
        super();
    }

    public Boolean addStocksFromFile(Map<String, Stock> stocks) {
        return false;
    }
}

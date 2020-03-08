package stockNews.roles;

import stockNews.Stock;
import java.util.Map;

public class User extends Actor {

    public User(String name) {
        super(name);
    }

    public Boolean addStocksFromFile(Map<String, Stock> stocks) {
        return false;
    }
}

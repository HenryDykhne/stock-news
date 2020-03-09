package stockNews.roles;

import org.junit.Before;
import org.junit.Test;
import stockNews.Blacklist;
import stockNews.Stock;
import stockNews.roles.Actor;
import stockNews.roles.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserTest {
    private Actor validUser;
    private String username;

    @Before
    public void setup() {
        username = "userName";
        validUser = new User(username);
    }

    @Test(expected = InvalidPrivilageException.class)
    public void addStocksFromRawDataPrivledgeTest() throws InvalidPrivilageException{
        Map<String, Stock> inputMap = new HashMap<>();
        List<String[]> inputStockData = new ArrayList<>();
        validUser.addStocksFromRawData(inputMap, inputStockData);
    }
}

package stockNews.roles;

import org.junit.Before;
import org.junit.Test;
import stockNews.Stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AdminTest {
    private Actor validUser;
    private Map<String, Stock> inputMap;
    private Map<String, Stock> expectedMap;
    private Map<String, Stock> returnedMap;
    private String username;
    private Stock testStock;
    private String testStockName;
    private String testStockNewsKeyword;
    private List<String>testStockNewsKeywords;
    private List<String[]> inputStockData;
    private String[] singleStockRow;

    @Before
    public void setup(){
        inputMap = new HashMap<>();
        testStockName = "testStock";
        testStockNewsKeyword = "word";
        testStockNewsKeywords = new ArrayList<>();
        testStockNewsKeywords.add(testStockNewsKeyword);
        testStock = new Stock(testStockName, testStockNewsKeywords);
        inputStockData = new ArrayList<>();
        singleStockRow = new String[]{testStockName, testStockNewsKeyword};
        inputStockData.add(singleStockRow);
        username = "userName";
        validUser = new Admin(username);
        expectedMap = new HashMap<>();
    }

    @Test
    public void addStocksFromRawDataTest() throws InvalidPrivilageException{
        validUser.addStocksFromRawData(inputMap, inputStockData);
        returnedMap = inputMap;
        expectedMap.put(testStockName, testStock);
        assertEquals(expectedMap.get(testStockName).getNewsKeywords(), returnedMap.get(testStockName).getNewsKeywords());
    }
}

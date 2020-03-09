package stockNews;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StockTest {
    private Stock stock;
    private List<String> validSearchTerms;
    private List<String> invalidSearchTerms;
    private String validStockName;
    private String invalidStockName;

    @Before
    public void setUp() {
        validSearchTerms = new ArrayList<>();
        validSearchTerms.add("term1");
        invalidSearchTerms = new ArrayList<>();
        validStockName = "validName";
        invalidStockName = "";
    }

    @Test(expected = InvalidStockException.class)
    public void invalidCreationBadNameTest() throws InvalidStockException {
        stock = new Stock(invalidStockName, validSearchTerms);
    }

    @Test(expected = InvalidStockException.class)
    public void invalidCreationBadSearchTermsTest() throws InvalidStockException {
        stock = new Stock(validStockName, invalidSearchTerms);
    }
}

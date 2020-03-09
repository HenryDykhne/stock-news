package stockNews;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class BlacklistTest {
    Blacklist blacklist;
    String blacklistName;
    String invalidName;
    Boolean activeState;
    String returnedString;
    String expectedString;
    String[] restrictedWords;

    @Before
    public void setup() {
        invalidName = "";
        blacklistName = "testBlacklist";
        activeState = false;
        restrictedWords = new String[]{"item1", "item2", "item3"};
        blacklist = new Blacklist(blacklistName, restrictedWords, activeState);
    }

    @Test
    public void toStringTest() {
        returnedString = blacklist.toString();
        expectedString = blacklistName + ", restrictedText: " + Arrays.toString(restrictedWords) + ", active: " + activeState;
        assertEquals(returnedString, expectedString);
    }

    @Test(expected = InvalidBlacklistException.class)
    public void invalidCreationNameTest() throws InvalidBlacklistException {
        blacklist = new Blacklist(invalidName);
    }
}
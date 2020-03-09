package stockNews;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class BlacklistTest {
    Blacklist blacklist;
    String blacklistName;
    String invalidName;
    Boolean activeState;
    String returnedString;
    String expectedString;
    String[] restrictedWords;
    List<String[]> blackistsToImportRaw;
    String[] rawBlacklist;
    Map<String, Blacklist> returnedMap;

    @Before
    public void setup() {
        invalidName = "";
        blacklistName = "testBlacklist";
        activeState = false;
        restrictedWords = new String[]{"item1", "item2", "item3"};
        blacklist = new Blacklist(blacklistName, restrictedWords, activeState);
        rawBlacklist = new String[]{blacklistName, restrictedWords[0], restrictedWords[1], restrictedWords[2]};
        blackistsToImportRaw = new ArrayList<>();
        blackistsToImportRaw.add(rawBlacklist);
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

    @Test
    public void importBlacklistTest() {
        returnedMap = Blacklist.importBlacklists(blackistsToImportRaw);
        expectedString = "[" + blacklistName + ", restrictedText: " + Arrays.toString(restrictedWords) + ", active: " + activeState + "]";
        returnedString = returnedMap.values().toString();
        assertEquals(expectedString, returnedString);
    }
}
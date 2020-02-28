
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


public class UserTest {
    Actor validUser;
    String expectedString;
    String returnedString;
    Map<String, Blacklist> blacklistMap;
    Blacklist validBlacklist1;
    Blacklist validBlacklist2;

    @Before
    public void setup() {
        validBlacklist1 = new Blacklist("obviousTestBlacklist", new String[] {"breitbart.com", "clickhole.com", "lifehacker.com", "theonion.com"}, true);
        validBlacklist2 = new Blacklist("slightlyDifferentBlacklist", new String[] {"cracked.com", "rt.com"}, true);
        blacklistMap = new HashMap<>();
        blacklistMap.put(validBlacklist1.getName(), validBlacklist1);
        blacklistMap.put(validBlacklist2.getName(), validBlacklist2);
        validUser = new User();
        validUser.addBlacklists(blacklistMap);
    }

    @Test
    public void blacklistsToString() {
        returnedString = validUser.blacklistsToString();
        expectedString = "obviousTestBlacklist: breitbart.com, clickhole.com, lifehacker.com, theonion.com"+
                "\nslightlyDifferentBlacklist: cracked.com, rt.com\n";
        assertEquals(expectedString, returnedString);
    }
}

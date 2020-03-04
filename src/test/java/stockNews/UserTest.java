package stockNews;

import org.junit.Before;
import org.junit.Test;
import stockNews.roles.Actor;
import stockNews.roles.User;

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
        validBlacklist1 = new Blacklist("obviousTestBlacklist", new String[] {"breitbart.com", "clickhole.com", "lifehacker.com", "theonion.com"}, false);
        validBlacklist2 = new Blacklist("slightlyDifferentBlacklist", new String[] {"cracked.com", "rt.com"}, false);
        blacklistMap = new HashMap<>();
        blacklistMap.put(validBlacklist1.getName(), validBlacklist1);
        blacklistMap.put(validBlacklist2.getName(), validBlacklist2);
        validUser = new User();
        validUser.addBlacklists(blacklistMap);
    }

    @Test
    public void blacklistsToStringTest() {
        returnedString = validUser.blacklistsToString();
        expectedString = "obviousTestBlacklist, restrictedText: [breitbart.com, clickhole.com, lifehacker.com, theonion.com], active: false\n" +
                "slightlyDifferentBlacklist, restrictedText: [cracked.com, rt.com], active: false\n";
        assertEquals(expectedString, returnedString);
    }
}

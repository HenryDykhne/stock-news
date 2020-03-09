package stockNews.roles;

import org.junit.Before;
import org.junit.Test;
import stockNews.Blacklist;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ActorTest {

    private Actor validUser;
    private String expectedString;
    private String returnedString;
    private Map<String, Blacklist> blacklistMap;
    private Blacklist validBlacklist1;
    private Blacklist validBlacklist2;
    private String username;

    @Before
    public void setup() {
        username = "userName";
        validBlacklist1 = new Blacklist("obviousTestBlacklist", new String[] {"breitbart.com", "clickhole.com", "lifehacker.com", "theonion.com"}, false);
        validBlacklist2 = new Blacklist("slightlyDifferentBlacklist", new String[] {"cracked.com", "rt.com"}, false);
        blacklistMap = new HashMap<>();
        blacklistMap.put(validBlacklist1.getName(), validBlacklist1);
        blacklistMap.put(validBlacklist2.getName(), validBlacklist2);
        validUser = new User(username);
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

package stockNews;

import org.junit.Before;
import org.junit.Test;
import stockNews.roles.Actor;
import stockNews.roles.User;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class AppTest {
    App app;
    Actor validUser;
    Map<String, Blacklist> blacklistMap;
    Blacklist validBlacklist1;
    Boolean returnedBool;
    Boolean expectedBool;
    String blacklistName;

    @Before
    public void setUp() {
        blacklistName = "obviousTestBlacklist";
        validBlacklist1 = new Blacklist(blacklistName, new String[] {"breitbart.com", "clickhole.com", "lifehacker.com", "theonion.com"}, false);
        blacklistMap = new HashMap<>();
        blacklistMap.put(validBlacklist1.getName(), validBlacklist1);
        validUser = new User();
        validUser.addBlacklists(blacklistMap);
        app = new App();
        app.setActor(validUser);
    }

    @Test
    public void setBlacklistActivationTest() {
        Boolean activation = true;
        app.setBlacklistActivation(blacklistName, activation);
        expectedBool = activation;
        returnedBool = app.getActor().getBlackLists().get(blacklistName).isActive();
        assertEquals(returnedBool, expectedBool);
    }
}
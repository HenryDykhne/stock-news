package stockNews;

import org.junit.Before;
import org.junit.Test;
import stockNews.roles.Actor;
import stockNews.roles.User;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class AppTest {
    private App app;
    private Actor validUser;
    private Map<String, Blacklist> blacklistMap;
    private Blacklist validBlacklist1;
    private Boolean returnedBool;
    private Boolean expectedBool;
    private String blacklistName;
    private String userName;
    private String text;

    @Before
    public void setUp() {
        userName = "userName";
        blacklistName = "obviousTestBlacklist";
        validBlacklist1 = new Blacklist(blacklistName, new String[] {"breitbart.com", "clickhole.com", "lifehacker.com", "theonion.com"}, false);
        blacklistMap = new HashMap<>();
        blacklistMap.put(validBlacklist1.getName(), validBlacklist1);
        validUser = new User(userName);
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

    @Test
    public void addToBlacklistTest() {
        text = "food.com";
        app.addToBlacklist(validBlacklist1.getName(), text);
        assertTrue(validBlacklist1.getRestrictedText().contains(text));
    }

    @Test
    public void removeFromBlacklistTest() {
        text = validBlacklist1.getRestrictedText().get(0);
        app.removeFromBlacklist(validBlacklist1.getName(), text);
        assertFalse(validBlacklist1.getRestrictedText().contains(text));
    }

    @Test
    public void createBlacklistTest() {
        text = "newBlacklist";
        app.createBlacklist(text);
        assertTrue(app.getActor().getBlackLists().containsKey(text));
    }
}
package stockNews;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stockNews.roles.Actor;
import stockNews.roles.User;
import java.io.File;
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
    private Blacklist validBlacklist2;
    private Boolean returnedBool;
    private Boolean expectedBool;
    private String blacklistName1;
    private String blacklistName2;
    private String userName;
    private String text;

    @Before
    public void setUp() {
        userName = "userName";
        blacklistName1 = "obviousTestBlacklist";
        blacklistName2 = "newBlacklist";
        validBlacklist1 = new Blacklist(blacklistName1, new String[] {"breitbart.com", "clickhole.com", "lifehacker.com", "theonion.com"}, false);
        validBlacklist2 = new Blacklist(blacklistName2, new String[] {"dood.com", "mood.com", "rood.com"}, true);
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
        app.setBlacklistActivation(blacklistName1, activation);
        expectedBool = activation;
        returnedBool = app.getActor().getBlackLists().get(blacklistName1).isActive();
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

    @Test
    public void saveProfileTest() {
        app.saveProfile();
        File file = new File("actorStorage/" + userName + ".actor");
        assertTrue(file.exists());
    }

    @After
    public void tearDown() {
        File file = new File("actorStorage/" + userName + ".actor");
        returnedBool = file.delete();
    }
}
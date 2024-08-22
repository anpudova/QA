package test;

import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;
import model.Account;
import org.junit.Assert;
import org.junit.Test;
import settings.Settings;

public class LoginTests extends TestBase {

    private final Account validAccount = new Account(Settings.getUsername(), Settings.getPassword());
    private final Account invalidAccount = new Account(Settings.getUsername(), Settings.getInvalidPassword());

    @Test
    public void loginWithValidData() {
        app.getNav().openLoginPage();
        app.getAuth().login(validAccount);
        app.getAuth().openProfile();
        String receivedUsername = app.getAuth().getUsername();

        Assert.assertEquals(validAccount.getUsername(), receivedUsername);
        app.getAuth().logout();
    }

    @Test
    public void loginWithInvalidData() {
        app.getNav().openLoginPage();
        app.getAuth().login(invalidAccount);

        Assert.assertEquals(true, app.getAuth().isInvalidData());
    }

}

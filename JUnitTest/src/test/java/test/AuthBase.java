package test;

import model.Account;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import settings.Settings;

public class AuthBase extends TestBase {

    protected static final Account user = new Account(Settings.getUsername(), Settings.getPassword());

    @BeforeClass
    @BeforeAll
    public static void SetupAuthTest() {
        app.getAuth().login(user);
    }
}

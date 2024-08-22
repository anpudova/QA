package test;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    protected static ApplicationManager app;

    @BeforeClass
    @BeforeAll
    public static void SetupTest() {
        app = ApplicationManager.GetInstance();
        app.getNav().openHomePage();
    }

    @AfterClass
    public static void TeardownTest() {
        app.stop();
    }
}

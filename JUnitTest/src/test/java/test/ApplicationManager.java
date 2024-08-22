package test;

import helper.LoginHelper;
import helper.NavigationHelper;
import helper.NoteHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import settings.Settings;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    public WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors;

    private LoginHelper auth;
    private NavigationHelper nav;
    private NoteHelper note;

    private static ThreadLocal<ApplicationManager> app = new ThreadLocal<>();

    private ApplicationManager() {
        System.setProperty(Settings.getChromeWebdriver(), Settings.getDriverPath());
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        baseUrl = Settings.getBaseURL();
        verificationErrors = new StringBuffer();
        note = new NoteHelper(this);
        auth = new LoginHelper(this);
        nav = new NavigationHelper(this, baseUrl);
    }

    public static ApplicationManager GetInstance() {
        if (app.get() == null) {
            ApplicationManager newInstance = new ApplicationManager();
            newInstance.getNav().openHomePage();
            app.set(newInstance);
        }
        return app.get();
    }

    public void stop() {
        driver.quit();
    }

    public LoginHelper getAuth() {
        return auth;
    }

    public NavigationHelper getNav() {
        return nav;
    }

    public NoteHelper getNote() {
        return note;
    }

}

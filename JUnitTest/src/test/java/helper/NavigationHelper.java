package helper;

import org.openqa.selenium.By;
import test.ApplicationManager;

public class NavigationHelper extends HelperBase {

    private String baseUrl;

    public NavigationHelper(ApplicationManager manager, String baseUrl) {
        super(manager);
        this.baseUrl = baseUrl;
    }

    public void openHomePage() {
        driver.get("https://text4.mega8.ru/user/");
    }

    public void openLoginPage() {
        driver.get("https://text4.mega8.ru/user/");
    }

    public void openNotesPage() {
        driver.findElement(By.linkText("Мои записи")).click();
        driver.get("https://text4.mega8.ru/user/posts.php");
    }
}

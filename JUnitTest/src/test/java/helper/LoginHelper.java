package helper;

import model.Account;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import test.ApplicationManager;

public class LoginHelper extends HelperBase {

    public LoginHelper(ApplicationManager manager) {
        super(manager);
    }

    public boolean isLoggedIn() {
        try {
            driver.findElement(By.linkText("Мой блокнот"));
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean isLoggedIn(String username) {
        openProfile();
        return username.equals(getUsername());
    }

    public void login(Account user) {
        if (isLoggedIn()) {
            if (isLoggedIn(user.getUsername())) {
                return;
            }
            logout();
        }
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys(user.getUsername());
        driver.findElement(By.name("pass")).click();
        driver.findElement(By.name("pass")).clear();
        driver.findElement(By.name("pass")).sendKeys(user.getPassword());
        driver.findElement(By.name("submit")).click();

    }

    public void logout() {
        driver.findElement(By.linkText("Выход")).click();
    }

    public void openProfile() {
        driver.findElement(By.linkText("Изменить профиль")).click();
        driver.get("https://text4.mega8.ru/user/profile.php");
    }

    public String getUsername() {
        return driver.findElement(By.xpath("//div[@id='content']/table/tbody/tr[2]/td[2]")).getText();
    }

    public Boolean isInvalidData() {
        return !driver.findElement(By.id("status")).getText().equals("");
    }
}

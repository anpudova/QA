package helper;

import com.sun.xml.internal.txw2.output.XmlSerializer;
import model.Note;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.ApplicationManager;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class NoteHelper extends HelperBase {

    public NoteHelper(ApplicationManager manager) {
        super(manager);
    }
    private final Random random = new Random();

    public void createNote(Note note) {
        driver.findElement(By.linkText("Новая запись")).click();
        String url = driver.getCurrentUrl();
        String num = StringUtils.right(url, 3);
        driver.findElement(By.id("name_" + num)).sendKeys(note.getName());
        driver.findElement(By.id("save")).click();
    }

    public Note updateNote() {
        driver.findElement(By.linkText("Мои записи")).click();
        driver.findElement(By.xpath("//div[@id='content']/table/tbody/tr[2]/td[2]/a")).click();
        String url = driver.getCurrentUrl();
        String num = StringUtils.right(url, 3);
        driver.findElement(By.id("name_" + num)).clear();
        driver.findElement(By.id("name_" + num)).sendKeys("note" + random.nextInt(100));
        driver.findElement(By.id("save")).click();
        return new Note(driver.findElement(By.id("name_" + num)).getAttribute("value"));
    }

    public void deleteNote() {
        driver.findElement(By.linkText("Мои записи")).click();
        driver.findElement(By.xpath("//div[@id='content']/table/tbody/tr[2]/td[2]/a")).click();
        driver.findElement(By.xpath("//img[@alt='Удалить']")).click();
        assertTrue(closeAlertAndGetItsText().matches("^Удалить эту запись[\\s\\S]$"));
    }

    public void openLastCreatedNote() {
        driver.findElement(By.linkText("Мои записи")).click();
        driver.findElement(By.xpath("//div[@id='content']/table/tbody/tr[2]/td[2]/a")).click();
    }

    public Note getCreatedNoteData() {
        String url = driver.getCurrentUrl();
        String num = StringUtils.right(url, 3);
        String name = driver.findElement(By.id("name_" + num)).getAttribute("value");
        return new Note(name);
    }

    public Integer getCountNotes() {
        List<WebElement> elements = driver.findElements(By.xpath("//div[@id='content']/table/tbody/tr"));
        if (elements.size() > 1) {
            return elements.size() - 1;
        }
        return 0;
    }

}


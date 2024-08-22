package settings;

import model.Note;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import sun.rmi.runtime.Log;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;

public class Settings {

    private static final String filePath = "settings.xml";
    private static Element settingElement;
    private static String baseURL;
    private static String username;
    private static String password;
    private static String driverPath;
    private static String chromeWebdriver;
    private static String invalidPassword;

    static {
        try {
            File xmlFile = new File(filePath);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
            doc.getDocumentElement().normalize();
            settingElement = (Element) doc.getElementsByTagName("setting").item(0);
        } catch (Exception ex) {
            System.out.println("Message: " + ex.getMessage());
        }
    }

    private Settings() {}

    public static String getBaseURL() {
        if (baseURL == null) {
            baseURL = settingElement.getElementsByTagName("baseURL").item(0).getTextContent();
        }
        return baseURL;
    }

    public static String getUsername() {
        if (username == null) {
            username = settingElement.getElementsByTagName("username").item(0).getTextContent();
        }
        return username;
    }

    public static String getPassword() {
        if (password == null) {
            password = settingElement.getElementsByTagName("password").item(0).getTextContent();
        }
        return password;
    }

    public static String getDriverPath() {
        if (driverPath == null) {
            driverPath = settingElement.getElementsByTagName("driverPath").item(0).getTextContent();
        }
        return driverPath;
    }

    public static String getChromeWebdriver() {
        if (chromeWebdriver == null) {
            chromeWebdriver = settingElement.getElementsByTagName("chromeWebdriver").item(0).getTextContent();
        }
        return chromeWebdriver;
    }

    public static String getInvalidPassword() {
        if (invalidPassword == null) {
            invalidPassword = settingElement.getElementsByTagName("invalidPassword").item(0).getTextContent();
        }
        return invalidPassword;
    }
}

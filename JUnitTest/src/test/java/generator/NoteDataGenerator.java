package generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class NoteDataGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите имя файла:");
        String filename = scanner.nextLine();

        System.out.println("Введите количество записей:");
        int numberOfNotes = scanner.nextInt();

        scanner.close();

        generateTestData(filename, numberOfNotes);
    }

    public static void generateTestData(String filename, int numberOfNotes) {
        List<String> noteTitles = generateNoteTitles(numberOfNotes);

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Создание корневого элемента
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Notes");
            doc.appendChild(rootElement);

            // Добавление записей в XML
            for (String title : noteTitles) {
                Element noteElement = doc.createElement("Note");
                noteElement.appendChild(doc.createTextNode(title));
                rootElement.appendChild(noteElement);
            }

            // Запись данных в файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filename));
            transformer.transform(source, result);

            System.out.println("Файл " + filename + " успешно создан.");

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    // Генерация случайных названий записей
    public static List<String> generateNoteTitles(int numberOfNotes) {
        List<String> noteTitles = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numberOfNotes; i++) {
            StringBuilder titleBuilder = new StringBuilder();
            int titleLength = random.nextInt(5) + 2;
            String allowedCharacters = "abcdefghijklmnopqrstuvwxyz";

            for (int j = 0; j < titleLength; j++) {
                int randomIndex = random.nextInt(allowedCharacters.length());
                char randomChar = allowedCharacters.charAt(randomIndex);
                titleBuilder.append(randomChar);
            }

            noteTitles.add(titleBuilder.toString().trim());
        }

        return noteTitles;
    }
}

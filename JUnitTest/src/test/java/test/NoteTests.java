package test;

import model.Note;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.testng.collections.Lists;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.List;
import java.util.stream.Stream;

public class NoteTests extends AuthBase {

    public static String filePath = "notes.xml";

    public static Stream<Note> parseXmlFile() {
        List<Note> notes = Lists.newArrayList();
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("Note");
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Element nodeElement = (Element) nodeList.item(temp);
                String name = nodeElement.getTextContent();
                notes.add(new Note(name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notes.stream();
    }

    @ParameterizedTest
    @MethodSource("parseXmlFile")
    public void testCreateTestCase(Note note) throws Exception {
        app.getNav().openNotesPage();
        app.getNote().createNote(note);
        app.getNote().openLastCreatedNote();
        Note receivedNote = app.getNote().getCreatedNoteData();

        Assertions.assertEquals(note.getName(), receivedNote.getName());
    }

    @Test
    public void testUpdateTestCase() throws Exception {
        Note updateNote = app.getNote().updateNote();
        app.getNote().openLastCreatedNote();
        Note receivedUpdateNote = app.getNote().getCreatedNoteData();

        Assertions.assertEquals(updateNote.getName(), receivedUpdateNote.getName());
    }

    @Test
    public void testDeleteTestCase() throws Exception {
        app.getNav().openNotesPage();
        Integer beforeRows = app.getNote().getCountNotes();
        app.getNote().deleteNote();
        app.getNav().openNotesPage();
        Integer afterRows = app.getNote().getCountNotes();

        if (beforeRows > 0) {
            Assertions.assertEquals(beforeRows - afterRows, 1);
        }
    }

}

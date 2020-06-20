package ManagerTest;

import Manager.Controllers.ControllerAdd;
import Manager.Services.AddJSON;
import ParentCode.Exceptions.AlreadyExistsException;
import ParentCode.Exceptions.EmptyField;
import Register.services.FileSystemService;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import java.io.IOException;
import static Manager.Services.AddJSON.books;
import static org.junit.Assert.*;

public class ControllerAddTest extends ApplicationTest {

    public static final String TITLE = "testTitle";
    public static final String AUTHOR = "testAuthor";
    public static final String GENRE = "testGenre";
    public static final String DETAILS = "testDetails";

    private ControllerAdd controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".testLibrariesAppRegister";
        FileSystemService.initApplicationHomeDirIfNeeded();
        AddJSON.loadBooksFromFile();
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        AddJSON.loadBooksFromFile();

        controller = new ControllerAdd();

        controller.setTitle(new TextField());
        controller.setAuthor(new TextField());
        controller.setGenre(new TextField());
        controller.setDetails(new TextArea());
        controller.setEmpty(new Label());
        controller.setBookAlreadyExist(new Label());
        controller.setListView(new ListView());
        controller.setListView1(new ListView());

        controller.getTitle().setText(TITLE);
        controller.getAuthor().setText(AUTHOR);
        controller.getGenre().setText(GENRE);
        controller.getDetails().setText(DETAILS);
    }

    @Test
    public void testHandleSubmit() throws IOException, EmptyField, AlreadyExistsException {
        AddJSON.loadBooksFromFile();
        controller.getListView().setAccessibleText("lv1");
        controller.getListView1().setAccessibleText("lv2");
        AddJSON.addBook(controller.getTitle().getText(), controller.getAuthor().getText(), controller.getGenre().getText(), controller.getDetails().getText(), controller.getListView().getAccessibleText(), controller.getListView1().getAccessibleText());
        controller.handleSubmit(new javafx.event.ActionEvent());
        assertNotNull(books);
        assertEquals(1, books.size());
    }

    @Test
    public void testEmptyField() throws IOException, EmptyField, AlreadyExistsException {
        controller.getTitle().setText("");
        controller.handleSubmit(new javafx.event.ActionEvent());
        assertEquals("Empty field!", controller.getEmpty().getText());
    }
}
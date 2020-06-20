package ManagerTest;

import Manager.Controllers.ControllerEdit;
import Manager.Services.AddJSON;
import ParentCode.Exceptions.AlreadyExistsException;
import ParentCode.Exceptions.EmptyField;
import Register.services.FileSystemService;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import java.io.IOException;
import static Manager.Services.AddJSON.books;
import static org.junit.Assert.*;

public class ControllerEditTest extends ApplicationTest {

    public static final String TEST_TITLE = "testTitle";
    public static final String TEST_AUTHOR = "testAuthor";
    public static final String TEST_GENRE = "testGenre";
    public static final String TEST_DETAILS = "testDetails";

    private ControllerEdit controller;

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

        controller = new ControllerEdit();
        controller.setTitle(new TextField());
        controller.setAuthor(new TextField());
        controller.setGenre(new TextField());
        controller.setDetails(new TextArea());
        controller.setBookAlreadyExist(new Label());
        controller.setEmpty(new Label());
        controller.setSave(new Button());

        controller.getTitle().setText(TEST_TITLE);
        controller.getAuthor().setText(TEST_AUTHOR);
        controller.getGenre().setText(TEST_GENRE);
        controller.getDetails().setText(TEST_DETAILS);
    }

    @Test
    public void testEmptyField() throws IOException {
        controller.getTitle().setText("");
        controller.handleSave(new javafx.event.ActionEvent());
        assertEquals("Empty field!", controller.getEmpty().getText());
    }

}
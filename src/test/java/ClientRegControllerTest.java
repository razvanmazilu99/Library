import Register.controllers.ClientRegController;
import Register.services.FileSystemService;
import Register.services.UserService;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import java.io.IOException;
import static ParentCode.Service.users;
import static org.junit.Assert.*;

public class ClientRegControllerTest extends ApplicationTest {

    public static final String TEST_USER = "testUser";
    public static final String TEST_PASSWORD = "testPassword";
    public static final String TEST_NAME = "testName";
    public static final String TEST_SURNAME = "testSurname";
    public static final String TEST_ADDRESS = "testAddress";
    public static final String TEST_EMAIL = "testEmail";
    public static final String TEST_PHONE_NUMBER = "testPhoneNumber";

    private ClientRegController controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".testLibrariesAppRegister";
        FileSystemService.initApplicationHomeDirIfNeeded();
        UserService.loadUsersFromFile();
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        UserService.loadUsersFromFile();

        controller = new ClientRegController();
        controller.setName(new TextField());
        controller.setSurname(new TextField());
        controller.setAddress(new TextField());
        controller.setEmail(new TextField());
        controller.setPhoneNumber(new TextField());
        controller.setUsername(new TextField());
        controller.setPassword(new PasswordField());
        controller.setRegistrationMessage(new Label());
        controller.setEmpty(new Label());
        controller.setClose(new Button());

        controller.getPassword().setText(TEST_PASSWORD);
        controller.getUsername().setText(TEST_USER);
        controller.getName().setText(TEST_NAME);
        controller.getSurname().setText(TEST_SURNAME);
        controller.getAddress().setText(TEST_ADDRESS);
        controller.getEmail().setText(TEST_EMAIL);
        controller.getPhoneNumber().setText(TEST_PHONE_NUMBER);

    }

    @Test
    public void testAddUser() throws IOException {
        try {
            controller.ClientRegister(new javafx.event.ActionEvent());
            assertEquals(1, users.size());
        } catch (ClassCastException e) { }
    }

    @Test
    public void testAddSameUserTwice() throws IOException {
        try {
            controller.ClientRegister(new javafx.event.ActionEvent());
            controller.ClientRegister(new javafx.event.ActionEvent());
            assertEquals("Username already exists!", controller.getRegistrationMessage().getText());
        } catch (ClassCastException e) { }
    }

    @Test
    public void testEmptyField() throws IOException {
        controller.getPassword().setText("");
        try {
            controller.ClientRegister(new javafx.event.ActionEvent());
            assertEquals("Empty field!", controller.getEmpty().getText());
        } catch (ClassCastException e) { }
    }

}
package LoginTest;

import Login.controllers.ControllerLogin;
import Login.services.LoginService;
import ParentCode.Exceptions.AlreadyExistsException;
import ParentCode.Exceptions.EmptyField;
import Register.services.FileSystemService;
import Register.services.UserService;
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

public class ControllerLoginTest extends ApplicationTest {

    public static final String TEST_USER = "testUser";
    public static final String TEST_PASSWORD = "testPassword";

    private ControllerLogin controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".testLibrariesAppRegister";
        FileSystemService.initApplicationHomeDirIfNeeded();
        LoginService.loadUsersFromFile();
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        LoginService.loadUsersFromFile();

        controller = new ControllerLogin();
        controller.setUsername(new TextField());
        controller.setPassword(new PasswordField());
        controller.setUsernotexist(new Label());
        controller.setEmpty(new Label());
        controller.setIncorrectpass(new Label());

        controller.getUsername().setText(TEST_USER);
        controller.getPassword().setText(TEST_PASSWORD);
    }

    @Test
    public void testHandleButtonAction() throws IOException, AlreadyExistsException, EmptyField {
        LoginService.loadUsersFromFile();
        UserService.addUserClient("nametest", "surnametest", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest");
        controller.handleButtonAction(new javafx.event.ActionEvent());
        assertEquals("usernametest", users.get(0).getUsername());
        UserService.addUserManager("librarytest", "addresstest", "emailtest", "phonenumbertest", "usernametest1", "passwordtest");
        controller.handleButtonAction(new javafx.event.ActionEvent());
        assertEquals("usernametest1", users.get(1).getUsername());
        assertEquals(2, users.size());
    }

    @Test
    public void testEmptyField() throws IOException {
        controller.getUsername().setText("");
        controller.handleButtonAction(new javafx.event.ActionEvent());
        assertEquals("Empty field!", controller.getEmpty().getText());
    }

    @Test
    public void testUserDoesNotExist() throws IOException, AlreadyExistsException, EmptyField {
        LoginService.loadUsersFromFile();
        UserService.addUserClient("nametest", "surnametest", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest");
        controller.handleButtonAction(new javafx.event.ActionEvent());
        assertNotEquals("username", users.get(0).getUsername());
        UserService.addUserManager("librarytest", "addresstest", "emailtest", "phonenumbertest", "usernametest1", "passwordtest");
        controller.handleButtonAction(new javafx.event.ActionEvent());
        assertNotEquals("username1", users.get(1).getUsername());
        assertEquals(2, users.size());
    }

    @Test
    public void testIncorrectPassword() throws IOException, AlreadyExistsException, EmptyField {
        LoginService.loadUsersFromFile();
        UserService.addUserClient("nametest", "surnametest", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest");
        controller.handleButtonAction(new javafx.event.ActionEvent());
        assertNotEquals(UserService.encodePassword("usernametest", "password"), users.get(0).getPassword());
        UserService.addUserManager("librarytest", "addresstest", "emailtest", "phonenumbertest", "usernametest1", "passwordtest");
        controller.handleButtonAction(new javafx.event.ActionEvent());
        assertNotEquals(UserService.encodePassword("usernametest1", "password"), users.get(1).getPassword());
        assertEquals(2, users.size());
    }
}
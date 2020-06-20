package LoginTest;

import Login.exceptions.IncorrectPassword;
import Login.exceptions.UserDoesNotExistException;
import Login.services.LoginService;
import ParentCode.Exceptions.AlreadyExistsException;
import ParentCode.Exceptions.EmptyField;
import Register.services.FileSystemService;
import Register.services.UserService;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import static ParentCode.Service.users;
import static org.junit.Assert.*;

public class LoginServiceTest {

    @BeforeClass
    public static void setupClass() {
        FileSystemService.APPLICATION_FOLDER = ".testLibrariesAppRegister";
        FileSystemService.initApplicationHomeDirIfNeeded();
    }

    @Before
    public void setUp() throws IOException {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
    }

    @Test
    public void testCopyDefaultFileIfNotExists() throws Exception {
        LoginService.loadUsersFromFile();
        assertTrue(Files.exists(UserService.USERS_PATH));
    }

    @Test
    public void testLoadUsersFromFile() throws Exception {
        LoginService.loadUsersFromFile();
        assertNotNull(users);
        assertEquals(0, users.size());
    }

    @Test
    public void verifyLogin() throws IOException, AlreadyExistsException, EmptyField, UserDoesNotExistException, IncorrectPassword {
        LoginService.loadUsersFromFile();
        UserService.addUserClient("nametest", "surnametest", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest");
        LoginService.verifyLogin("usernametest", "passwordtest");
        assertNotNull(users);
        assertEquals(1, users.size());
        assertNotEquals("passwordtest", UserService.encodePassword("usernametest", "passwordtest"));
        assertEquals("usernametest", users.get(0).getUsername());
    }

    @Test(expected = UserDoesNotExistException.class)
    public void testUserDoesNotExist() throws Exception {
        LoginService.loadUsersFromFile();
        UserService.addUserManager("librarynametest", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest");
        assertNotNull(users);
        assertEquals(1, users.size());
        LoginService.checkUserDoesNotExist("usernametest1");
    }

    @Test(expected = IncorrectPassword.class)
    public void testCheckPassword() throws Exception {
        LoginService.loadUsersFromFile();
        UserService.addUserManager("librarynametest", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest");
        assertNotNull(users);
        assertEquals(1, users.size());
        LoginService.checkPassword("usernametest", "passwordtest1");
    }

    @Test(expected = EmptyField.class)
    public void testEmptyFieldClient() throws Exception {
        LoginService.loadUsersFromFile();
        UserService.addUserClient("nametest", "surnametest", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest");
        assertNotNull(users);
        LoginService.checkEmptyField("nametest", "");
    }
}
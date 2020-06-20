package RegisterTest;

import ParentCode.Exceptions.AlreadyExistsException;
import ParentCode.Exceptions.EmptyField;
import Register.actionMode.User;
import Register.services.FileSystemService;
import Register.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import static org.junit.Assert.*;

public class UserServiceTest {

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
        UserService.loadUsersFromFile();
        assertTrue(Files.exists(UserService.USERS_PATH));
    }

    @Test
    public void testLoadUsersFromFile() throws Exception {
        UserService.loadUsersFromFile();
        assertNotNull(UserService.users);
        assertEquals(0, UserService.users.size());
    }

    @Test
    public void testAddOneUserClient() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUserClient("nametest", "surnametest", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest");
        assertNotNull(UserService.users);
        assertEquals(1, UserService.users.size());
    }

    @Test
    public void testAddOneUserManager() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUserManager("librarynametest", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest");
        assertNotNull(UserService.users);
        assertEquals(1, UserService.users.size());
    }

    @Test
    public void testAddTwoUsers() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUserClient("nametest", "surnametest", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest");
        UserService.addUserManager("librarynametest", "addresstest", "emailtest", "phonenumbertest", "usernametest1", "passwordtest");
        assertNotNull(UserService.users);
        assertEquals(2, UserService.users.size());
    }

    @Test(expected = AlreadyExistsException.class)
    public void testAddUserAlreadyExists() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUserManager("librarynametest", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest");
        assertNotNull(UserService.users);
        UserService.checkUserDoesNotAlreadyExist("usernametest");
    }

    @Test
    public void testAddOneUserIsPersisted() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUserManager("librarynametest", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest");
        List<User> users = new ObjectMapper().readValue(UserService.USERS_PATH.toFile(), new TypeReference<List<User>>() {});
        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    public void testAddTwoUserArePersisted() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUserClient("nametest", "surnametest", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest");
        UserService.addUserManager("librarynametest", "addresstest", "emailtest", "phonenumbertest", "usernametest1", "passwordtest");
        List<User> users = new ObjectMapper().readValue(UserService.USERS_PATH.toFile(), new TypeReference<List<User>>() {});
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    public void testPasswordEncoding() {
        assertNotEquals("testPass1", UserService.encodePassword("username1", "testPass1"));
    }

    @Test(expected = EmptyField.class)
    public void testEmptyFieldClient() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUserClient("nametest", "surnametest", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest");
        assertNotNull(UserService.users);
        UserService.checkEmptyFieldClient("nametest", "", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest");
    }

    @Test(expected = EmptyField.class)
    public void testEmptyFieldManager() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUserManager("librarynametest", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest");
        assertNotNull(UserService.users);
        UserService.checkEmptyFieldClient("nametest", "", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest");
    }
}
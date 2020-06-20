package ClientTest;

import Client.ActionMode.Request;
import Client.Exception.TenRequestsException;
import Client.Services.AddRequest;
import Manager.ActionMode.Book;
import ParentCode.Exceptions.AlreadyExistsException;
import Register.actionMode.User;
import Register.services.FileSystemService;
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

public class AddRequestTest {

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
        AddRequest.loadRequestsFromFile();
        assertTrue(Files.exists(AddRequest.REQUESTS_PATH));
    }

    @Test
    public void testLoadRequestsFromFile() throws Exception {
        AddRequest.loadRequestsFromFile();
        assertNotNull(AddRequest.requests);
        assertEquals(0, AddRequest.requests.size());
    }

    @Test
    public void testAddOneRequest() throws Exception {
        AddRequest.loadRequestsFromFile();
        AddRequest.addRequest(new User("nametest", "surnametest", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest"), new Book("titletest", "authortest", "genretest", "detailstest", "imagetest", "pdftest"));
        assertNotNull(AddRequest.requests);
        assertEquals(1, AddRequest.requests.size());
    }

    @Test
    public void testAddTwoRequests() throws Exception {
        AddRequest.loadRequestsFromFile();
        AddRequest.addRequest(new User("nametest", "surnametest", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest"), new Book("titletest", "authortest", "genretest", "detailstest", "imagetest", "pdftest"));
        AddRequest.addRequest(new User("nametest", "surnametest", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest"), new Book("titletest1", "authortest1", "genretest", "detailstest", "imagetest", "pdftest"));
        assertNotNull(AddRequest.requests);
        assertEquals(2, AddRequest.requests.size());
    }

    @Test(expected = AlreadyExistsException.class)
    public void testAddRequestAlreadyExists() throws Exception {
        AddRequest.loadRequestsFromFile();
        AddRequest.addRequest(new User("nametest", "surnametest", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest"), new Book("titletest", "authortest", "genretest", "detailstest", "imagetest", "pdftest"));
        assertNotNull(AddRequest.requests);
        AddRequest.checkRequestDoesNotAlreadyExist("usernametest", "titletest", "authortest");
    }

    @Test
    public void testAddOneRequestIsPersisted() throws Exception {
        AddRequest.loadRequestsFromFile();
        AddRequest.addRequest(new User("nametest", "surnametest", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest"), new Book("titletest", "authortest", "genretest", "detailstest", "imagetest", "pdftest"));
        List<Request> requests = new ObjectMapper().readValue(AddRequest.REQUESTS_PATH.toFile(), new TypeReference<List<Request>>() {
        });
        assertNotNull(requests);
        assertEquals(1, requests.size());
    }

    @Test
    public void testAddTwoUserArePersisted() throws Exception {
        AddRequest.loadRequestsFromFile();
        AddRequest.addRequest(new User("nametest", "surnametest", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest"), new Book("titletest", "authortest", "genretest", "detailstest", "imagetest", "pdftest"));
        AddRequest.addRequest(new User("nametest", "surnametest", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest"), new Book("titletest1", "authortest1", "genretest", "detailstest", "imagetest", "pdftest"));
        List<Request> requests = new ObjectMapper().readValue(AddRequest.REQUESTS_PATH.toFile(), new TypeReference<List<Request>>() {
        });
        assertNotNull(requests);
        assertEquals(2, requests.size());
    }

    @Test (expected = TenRequestsException.class)
    public void checkTenRequestsExist() throws IOException, AlreadyExistsException, TenRequestsException {
        AddRequest.loadRequestsFromFile();
        for(int i = 0; i <= 10; i++)
            AddRequest.addRequest(new User("nametest", "surnametest", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest"), new Book("titletest" + i, "authortest" + i, "genretest", "detailstest", "imagetest", "pdftest"));
        assertNotNull(AddRequest.requests);
        AddRequest.checkTenRequestsExist("usernametest", "librarynametest");
}
}
package ManagerTest;

import Manager.ActionMode.Book;
import Manager.Services.AddJSON;
import ParentCode.Exceptions.AlreadyExistsException;
import ParentCode.Exceptions.EmptyField;
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
import static Manager.Services.AddJSON.books;
import static org.junit.Assert.*;

public class AddJSONTest {

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
        AddJSON.loadBooksFromFile();
        assertTrue(Files.exists(AddJSON.BOOKS_PATH));
    }

    @Test
    public void testLoadBooksFromFile() throws Exception {
        AddJSON.loadBooksFromFile();
        assertNotNull(books);
        assertEquals(0, books.size());
    }

    @Test
    public void testAddOneBook() throws Exception {
        AddJSON.loadBooksFromFile();
        AddJSON.addBook("titletest", "authortest", "genretest", "detailstest", "imagetest", "pdftest");
        assertNotNull(books);
        assertEquals(1, books.size());
    }

    @Test
    public void testAddTwoBooks() throws Exception {
        AddJSON.loadBooksFromFile();
        AddJSON.addBook("titletest", "authortest", "genretest", "detailstest", "imagetest", "pdftest");
        AddJSON.addBook("titletest1", "authortest", "genretest", "detailstest", "imagetest", "pdftest");
        assertNotNull(books);
        assertEquals(2, books.size());
    }

    @Test
    public void testAddOneBookIsPersisted() throws Exception {
        AddJSON.loadBooksFromFile();
        AddJSON.addBook("titletest", "authortest", "genretest", "detailstest", "imagetest", "pdftest");
        List<Book> books = new ObjectMapper().readValue(AddJSON.BOOKS_PATH.toFile(), new TypeReference<List<Book>>() {});
        assertNotNull(books);
        assertEquals(1, books.size());
    }

    @Test
    public void testAddTwoUserArePersisted() throws Exception {
        AddJSON.loadBooksFromFile();
        AddJSON.addBook("titletest", "authortest", "genretest", "detailstest", "imagetest", "pdftest");
        AddJSON.addBook("titletest1", "authortest", "genretest", "detailstest", "imagetest", "pdftest");
        List<Book> books = new ObjectMapper().readValue(AddJSON.BOOKS_PATH.toFile(), new TypeReference<List<Book>>() {});
        assertNotNull(books);
        assertEquals(2, books.size());
    }

    @Test(expected = AlreadyExistsException.class)
    public void testBooksDoesNotAlreadyExist() throws Exception {
        AddJSON.loadBooksFromFile();
        AddJSON.addBook("titletest", "authortest", "genretest", "detailstest", "imagetest", "pdftest");
        assertNotNull(books);
        AddJSON.checkBookDoesNotAlreadyExist("titletest", "authortest", books.get(0).getUser());
    }

    @Test(expected = AlreadyExistsException.class)
    public void testBooksDoesNotAlreadyExist1() throws Exception {
        AddJSON.loadBooksFromFile();
        AddJSON.addBook("titletest", "authortest", "genretest", "detailstest", "imagetest", "pdftest");
        assertNotNull(books);
        AddJSON.checkBookDoesNotAlreadyExist1("titletest", "authortest", books.get(0).getUser(), 2);
    }

    @Test(expected = EmptyField.class)
    public void testEmptyField() throws Exception {
        AddJSON.loadBooksFromFile();
        AddJSON.addBook("titletest", "authortest", "genretest", "detailstest", "imagetest", "pdftest");
        assertNotNull(books);
        AddJSON.checkEmptyField("titletest", "", "genretest", "detailstest", "imagetest", "pdftest");
    }

    @Test(expected = EmptyField.class)
    public void testEmptyField1() throws Exception {
        AddJSON.loadBooksFromFile();
        AddJSON.addBook("titletest", "authortest", "genretest", "detailstest", "imagetest", "pdftest");
        assertNotNull(books);
        AddJSON.checkEmptyField1("titletest", "", "genretest", "detailstest");
    }

}
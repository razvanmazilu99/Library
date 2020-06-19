package Manager.Services;

import Login.controllers.ControllerLogin;
import Manager.ActionMode.Book;
import ParentCode.Exceptions.AlreadyExistsException;
import ParentCode.Exceptions.CouldNotWriteObjectException;
import ParentCode.Exceptions.EmptyField;
import Register.services.FileSystemService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class AddJSON {

    public static List<Book> books;
    public static final Path BOOKS_PATH = FileSystemService.getPathToFile("config", "books.json");

    public static void loadBooksFromFile() throws IOException {

        if (!Files.exists(BOOKS_PATH)) {
            FileUtils.copyURLToFile(AddJSON.class.getClassLoader().getResource("Manager/books.json"), BOOKS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        books = objectMapper.readValue(BOOKS_PATH.toFile(), new TypeReference<List<Book>>() { });
    }

    public static void addBook(String title, String author, String genre, String details, String image, String pdf) throws EmptyField, AlreadyExistsException {

        checkEmptyField(title, author, genre, details, image, pdf);
        checkBookDoesNotAlreadyExist(title, author, ControllerLogin.saveUser);
        books.add(new Book(title, author, genre, details, image, pdf));
        persistBooks();
    }

    public static void persistBooks() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(BOOKS_PATH.toFile(), books);
        } catch (IOException e) {
            throw new CouldNotWriteObjectException();
        }
    }

    public static void checkEmptyField(String title, String author, String genre, String details, String image, String pdf) throws EmptyField {
        if (title.equals("") || author.equals("") || genre.equals("") || details.equals("") || image == null)
            throw new EmptyField();
    }

    public static void checkEmptyField1(String title, String author, String genre, String details) throws EmptyField {
        if (title.equals("") || author.equals("") || genre.equals("") || details.equals(""))
            throw new EmptyField();
    }

    public static void checkBookDoesNotAlreadyExist(String title, String author, String username) throws AlreadyExistsException {
        for (Book b : books) {
            if (Objects.equals(title, b.getTitle()) && Objects.equals(author, b.getAuthor()) && Objects.equals(username, b.getUser()))
                throw new AlreadyExistsException("Book already exists!");
        }
    }

    public static void checkBookDoesNotAlreadyExist1(String title, String author, String username, int id1) throws AlreadyExistsException {
        for (Book b : books) {
            if (Objects.equals(title, b.getTitle()) && Objects.equals(author, b.getAuthor()) && Objects.equals(username, b.getUser()) && id1 != books.indexOf(b))
                throw new AlreadyExistsException("Book already exists!");
        }
    }
}


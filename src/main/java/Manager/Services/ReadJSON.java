package Manager.Services;

import Manager.ActionMode.Book;
import Register.services.FileSystemService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReadJSON {

    public static List<Book> books_read;
    public static final Path BOOKS_PATH = FileSystemService.getPathToFile("config", "books.json");

    public static List<Book> readBooksFromFile() throws IOException {

        if (!Files.exists(BOOKS_PATH)) {
            FileUtils.copyURLToFile(AddJSON.class.getClassLoader().getResource("Manager/books.json"), BOOKS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        books_read = objectMapper.readValue(BOOKS_PATH.toFile(), new TypeReference<List<Book>>() { });
        return books_read;
    }
}
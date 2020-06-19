package Client.Services;

import Client.ActionMode.Request;
import ParentCode.Exceptions.CouldNotWriteObjectException;
import ParentCode.Exceptions.AlreadyExistsException;
import Client.Exception.TenRequestsException;
import Manager.ActionMode.Book;
import Register.actionMode.User;
import Register.services.FileSystemService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import static Client.Controllers.ControllerClient.libraryNameSave;

public class AddRequest {

    public static List<Client.ActionMode.Request> requests;
    public static final Path REQUESTS_PATH = FileSystemService.getPathToFile("config", "requests.json");

    public static void loadRequestsFromFile() throws IOException {

        if (!Files.exists(REQUESTS_PATH)) {
            FileUtils.copyURLToFile(AddRequest.class.getClassLoader().getResource("Client/requests.json"), REQUESTS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        requests = objectMapper.readValue(REQUESTS_PATH.toFile(), new TypeReference<List<Request>>() { });
    }

    public static void addRequest(User u, Book b) throws AlreadyExistsException, TenRequestsException {
        checkRequestDoesNotAlreadyExist(u.getUsername(), b.getTitle(), b.getAuthor());
        checkTenRequestsExist(u.getUsername(), libraryNameSave);
        requests.add(new Request(u, b));
        persistRequest();
    }

    public static void persistRequest() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(REQUESTS_PATH.toFile(), requests);
        } catch (IOException e) {
            throw new CouldNotWriteObjectException();
        }
    }

    public static void checkRequestDoesNotAlreadyExist(String username, String title, String author) throws AlreadyExistsException {
        if(requests != null)
            for (Request r : requests) {
                if (Objects.equals(title, r.getTitle_book()) && Objects.equals(username, r.getUsername_user()) && Objects.equals(author, r.getAuthor_book()) && r.getStatus() == 0)
                    throw new AlreadyExistsException("Request already exists!");
            }
    }

    public static void checkTenRequestsExist(String username, String library) throws TenRequestsException {
        int count = 0;
        if(requests == null)
            return;
        for (Request r : requests) {
            if (Objects.equals(username, r.getUsername_user()) && Objects.equals(library, r.getLibraryName_book()) && r.getStatus() == 0)
                count++;
        }
        if(count == 10)
            throw new TenRequestsException();
    }
}

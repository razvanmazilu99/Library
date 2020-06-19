package ParentCode;

import Register.actionMode.User;
import Register.services.FileSystemService;
import Register.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Service {

    public static List<User> users;
    public static final Path USERS_PATH = FileSystemService.getPathToFile("config", "users.json");

    public static void loadUsersFromFile() throws IOException {

        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("Register/users.json"), USERS_PATH.toFile());
        }

        ObjectMapper objectMapperClient = new ObjectMapper();

        users = objectMapperClient.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
    }
}

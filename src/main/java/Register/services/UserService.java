package Register.services;

import Register.actionMode.User;
import Register.exceptions.CouldNotWriteUsersException;
import Register.exceptions.UserAlreadyExistsException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UserService {

    private static List<User> users;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("config", "users.json");

    public static void loadUsersFromFile() throws IOException {

        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("Register/users.json"), USERS_PATH.toFile());
        }

        ObjectMapper objectMapperClient = new ObjectMapper();

        users = objectMapperClient.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>() { });
    }

    public static void addUserClient(String name, String surname, String address, String email, String phoneNumber, String username, String password) throws UserAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        users.add(new User(name, surname, address, email, phoneNumber, username, password));
        persistUsers();
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UserAlreadyExistsException {

    }

    private static void persistUsers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), users);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

}

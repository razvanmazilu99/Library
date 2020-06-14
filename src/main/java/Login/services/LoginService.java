package Login.services;

import Login.exceptions.IncorrectPassword;
import Login.exceptions.UserDoesNotExistException;
import ParentCode.Exceptions.EmptyField;
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
import static Register.services.UserService.encodePassword;


public class LoginService {

    private static List<User> users;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("config", "users.json");

    public static void loadUsersFromFile() throws IOException {

        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("Register/users.json"), USERS_PATH.toFile());
        }

        ObjectMapper objectMapperClient = new ObjectMapper();

        users = objectMapperClient.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
    }

    public static void verifyLogin (String username, String password) throws UserDoesNotExistException, IncorrectPassword, EmptyField {

        checkEmptyField(username, password);
        checkUserDoesNotExist(username);
        checkPassword(username, password);
    }

    private static void checkUserDoesNotExist(String username) throws UserDoesNotExistException {

        int count = 1;

        for (User user : users) {
            if (username.equals(user.getUsername()))
                count = 0;
        }

        if(count == 1)
            throw new UserDoesNotExistException();
    }

    private static void checkPassword(String username, String password) throws IncorrectPassword {
        int count = 1;

        for (User user : users) {
            if (encodePassword(username, password).equals(user.getPassword()))
                count = 0;
        }

        if (count == 1)
            throw new IncorrectPassword();
    }

    public static void checkEmptyField(String username, String password) throws EmptyField {
        if (username.equals("") || password.equals(""))
            throw new EmptyField();
    }
}

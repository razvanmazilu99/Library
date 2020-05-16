package Register.services;

import Register.actionMode.User;
import Register.exceptions.CouldNotWriteUsersException;
import Register.exceptions.EmptyField;
import Register.exceptions.UserAlreadyExistsException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

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

    public static void addUserClient(String name, String surname, String address, String email, String phoneNumber, String username, String password) throws UserAlreadyExistsException, EmptyField {
        checkUserDoesNotAlreadyExist(username);
        checkEmptyField(name, surname, address, email, phoneNumber, username, password);
        users.add(new User(name, surname, address, email, phoneNumber, username, encodePassword(username, password)));
        persistUsers();
    }

    public static void addUserManager(String libraryName, String address, String email, String phoneNumber, String username, String password) throws UserAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        users.add(new User(libraryName, address, email, phoneNumber, username, encodePassword(username, password)));
        persistUsers();
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UserAlreadyExistsException {
        for (User user : users) {
            if (Objects.equals(username, user.getUsername()))
                throw new UserAlreadyExistsException(username);
        }
    }

    public static void checkEmptyField(String name, String surname, String address, String email, String phoneNumber, String username, String password) throws EmptyField {
        if (username.equals("") || password.equals("") || name.equals("") || surname.equals("") || address.equals("") || email.equals("") || phoneNumber.equals(""))
            throw new EmptyField();
    }

    private static void persistUsers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), users);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
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

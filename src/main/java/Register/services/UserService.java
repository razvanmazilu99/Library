package Register.services;

import ParentCode.Exceptions.AlreadyExistsException;
import ParentCode.Exceptions.CouldNotWriteObjectException;
import ParentCode.Exceptions.EmptyField;
import Register.actionMode.User;
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

    public static List<User> users;
    public static final Path USERS_PATH = FileSystemService.getPathToFile("config", "users.json");

    public static void loadUsersFromFile() throws IOException {

        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("Register/users.json"), USERS_PATH.toFile());
        }

        ObjectMapper objectMapperClient = new ObjectMapper();

        users = objectMapperClient.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>() { });
    }

    public static void addUserClient(String name, String surname, String address, String email, String phoneNumber, String username, String password) throws AlreadyExistsException, EmptyField {
        checkUserDoesNotAlreadyExist(username);
        checkEmptyFieldClient(name, surname, address, email, phoneNumber, username, password);
        users.add(new User(name, surname, address, email, phoneNumber, username, encodePassword(username, password)));
        persistUsers();
    }

    public static void addUserManager(String libraryName, String address, String email, String phoneNumber, String username, String password) throws AlreadyExistsException, EmptyField {
        checkUserDoesNotAlreadyExist(username);
        checkEmptyFieldManager(libraryName, address, email, phoneNumber, username, password);
        users.add(new User(libraryName, address, email, phoneNumber, username, encodePassword(username, password)));
        persistUsers();
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws AlreadyExistsException {
        for (User user : users) {
            if (Objects.equals(username, user.getUsername()))
                throw new AlreadyExistsException("Username already exists!");
        }
    }

    public static void checkEmptyFieldClient(String name, String surname, String address, String email, String phoneNumber, String username, String password) throws EmptyField {
        if (username.equals("") || password.equals("") || name.equals("") || surname.equals("") || address.equals("") || email.equals("") || phoneNumber.equals(""))
            throw new EmptyField();
    }

    public static void checkEmptyFieldManager(String libraryName, String address, String email, String phoneNumber, String username, String password) throws EmptyField {
        if (username.equals("") || password.equals("") || libraryName.equals("") || address.equals("") || email.equals("") || phoneNumber.equals(""))
            throw new EmptyField();
    }

    private static void persistUsers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), users);
        } catch (IOException e) {
            throw new CouldNotWriteObjectException();
        }
    }

    public static String encodePassword(String salt, String password) {
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

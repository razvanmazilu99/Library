package Register.services;

import ParentCode.Exceptions.AlreadyExistsException;
import ParentCode.Exceptions.CouldNotWriteObjectException;
import ParentCode.Exceptions.EmptyField;
import ParentCode.Service;
import Register.actionMode.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class UserService extends Service {

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

    public static void checkUserDoesNotAlreadyExist(String username) throws AlreadyExistsException {
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

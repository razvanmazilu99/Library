package Login.services;

import Login.exceptions.IncorrectPassword;
import Login.exceptions.UserDoesNotExistException;
import ParentCode.Exceptions.EmptyField;
import ParentCode.Service;
import Register.actionMode.User;
import static Register.services.UserService.encodePassword;


public class LoginService extends Service {

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

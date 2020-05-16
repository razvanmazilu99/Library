package Register.exceptions;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException() {
        super(String.format("Username already exists!"));
    }

}
package Login.exceptions;

public class UserDoesNotExistException extends Exception {

    public UserDoesNotExistException() { super(String.format("User does not exist!")); }
}
package Register.exceptions;

public class UserAlreadyExistsException extends Exception {

    private String username;

    public UserAlreadyExistsException(String username) {

    }

    public String getUsername() {
        return username;
    }

}

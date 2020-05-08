package Register.exceptions;

public class UserAlreadyExistsException extends Exception {

    private String username;

    public UserAlreadyExistsException(String username) {
        super(String.format("Username already exists!"));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
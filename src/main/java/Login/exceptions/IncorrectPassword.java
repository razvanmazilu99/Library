package Login.exceptions;

public class IncorrectPassword extends Exception {

    public IncorrectPassword() {
        super(String.format("Incorrect password!"));
    }
}

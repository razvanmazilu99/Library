package Manager.exceptions;

public class WrongDateException extends Exception {

    public WrongDateException() {
        super(String.format("Return date is wrong!"));
    }
}

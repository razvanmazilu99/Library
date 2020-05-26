package Manager.exceptions;

public class BookAlreadyExistsException extends Exception {

    public BookAlreadyExistsException() {
        super(String.format("Book already exists!"));
    }
}

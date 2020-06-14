package ParentCode.Exceptions;

public class EmptyField extends Exception{

    public EmptyField() {
        super(String.format("Empty field!"));
    }
}

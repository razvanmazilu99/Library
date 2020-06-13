package ParentCode.Exceptions;

public class TenRequestsException extends Exception {
    public TenRequestsException() {
        super(String.format("You already have 10 requests!"));
    }
}

package ParentCode.Exceptions;

public class AlreadyExistsException extends Exception {
        public AlreadyExistsException(String message) {
            super(String.format(message));
        }
}

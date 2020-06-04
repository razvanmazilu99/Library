package Client.Exceptions;

public class RequestAlreadyExistsException extends Exception {
        public RequestAlreadyExistsException() {
            super(String.format("Request already exists!"));
        }
}

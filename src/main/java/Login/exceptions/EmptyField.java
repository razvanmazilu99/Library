package Login.exceptions;

public class EmptyField extends Exception {

        public EmptyField() {
            super(String.format("Empty field!"));
        }
}

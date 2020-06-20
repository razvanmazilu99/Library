package LoginTest;

import Login.exceptions.UserDoesNotExistException;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDoesNotExistExceptionTest {

    @Test
    public void testUserDoesNotExistException() {
        UserDoesNotExistException except = new UserDoesNotExistException();
        assertEquals("User does not exist!", except.getMessage());
    }
}
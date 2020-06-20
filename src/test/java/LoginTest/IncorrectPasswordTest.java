package LoginTest;

import Login.exceptions.IncorrectPassword;
import org.junit.Test;
import static org.junit.Assert.*;

public class IncorrectPasswordTest {

    @Test
    public void testIncorrectPassword() {
        IncorrectPassword except = new IncorrectPassword();
        assertEquals("Incorrect password!", except.getMessage());
    }

}
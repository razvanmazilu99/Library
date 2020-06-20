import ParentCode.Exceptions.AlreadyExistsException;
import org.junit.Test;
import static org.junit.Assert.*;

public class AlreadyExistsExceptionTest {

    @Test
    public void testAlreadyExistsException() {
        String message = "testmessage";
        AlreadyExistsException except = new AlreadyExistsException(message);
        assertEquals(message, except.getMessage());
    }
}
import ParentCode.Exceptions.EmptyField;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmptyFieldTest {

    @Test
    public void testAlreadyExistsException() {
        EmptyField except = new EmptyField();
        assertEquals("Empty field!", except.getMessage());
    }
}
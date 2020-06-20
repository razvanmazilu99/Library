package ClientTest;

import Client.Exception.TenRequestsException;
import org.junit.Test;
import static org.junit.Assert.*;

public class TenRequestsExceptionTest {

    @Test
    public void testTenRequestsException() {
        TenRequestsException except = new TenRequestsException();
        assertEquals("You already have 10 requests!", except.getMessage());
    }
}
package ManagerTest;

import Manager.exceptions.WrongDateException;
import org.junit.Test;

import static org.junit.Assert.*;

public class WrongDateExceptionTest {

    @Test
    public void testWrongDateException() {
        WrongDateException except = new WrongDateException();
        assertEquals("Return date is wrong!", except.getMessage());
    }
}
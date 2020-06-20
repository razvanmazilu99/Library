package ManagerTest;

import Client.Exception.TenRequestsException;
import Client.Services.AddRequest;
import Manager.ActionMode.Book;
import Manager.Controllers.ControllerAccept;
import ParentCode.Exceptions.AlreadyExistsException;
import Register.actionMode.User;
import Register.services.FileSystemService;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class ControllerAcceptTest extends ApplicationTest {

    public static final String PICK_UP_DATE = "pickUpDate";
    public static final String RETURN_DATE = "ReturnDate";
    private ControllerAccept controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".testLibrariesAppRegister";
        FileSystemService.initApplicationHomeDirIfNeeded();
        AddRequest.loadRequestsFromFile();
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        AddRequest.loadRequestsFromFile();

        controller = new ControllerAccept();
        controller.setPickUpDate(new DatePicker());
        controller.setReturnDate(new DatePicker());
        controller.setEmpty(new Label());
        controller.setMessage(new Label());

        controller.getPickUpDate().setAccessibleText(PICK_UP_DATE);
        controller.getReturnDate().setAccessibleText(RETURN_DATE);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse("11-11-2020", formatter);
        controller.getPickUpDate().setValue(localDate);
        LocalDate localDate1 = LocalDate.parse("06-11-2020", formatter);
        controller.getReturnDate().setValue(localDate1);

    }

    @Test
    public void testHandleAccept() throws IOException, AlreadyExistsException, TenRequestsException {
        AddRequest.loadRequestsFromFile();
        AddRequest.addRequest(new User("nametest", "surnametest", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest"), new Book("titletest", "authortest", "genretest", "detailstest", "imagetest", "pdftest"));
        AddRequest.requests.get(0).setPickUpDate(controller.getPickUpDate().getAccessibleText());
        AddRequest.requests.get(0).setReturnDate(controller.getReturnDate().getAccessibleText());
        controller.handleAccept(new ActionEvent());
        assertNotNull(AddRequest.requests);
        assertEquals(1, AddRequest.requests.size());
        assertNotEquals(null, AddRequest.requests.get(0).getPickUpDate());
        assertNotEquals(null, AddRequest.requests.get(0).getReturnDate());
    }

    @Test
    public void testEmptyField() throws IOException {
        controller.getReturnDate().setValue(null);
        controller.handleAccept(new javafx.event.ActionEvent());
        assertEquals("Empty field!", controller.getEmpty().getText());
    }

    @Test
    public void testWrongDate() throws IOException {
        controller.handleAccept(new javafx.event.ActionEvent());
        assertEquals("Return date is wrong!", controller.getMessage().getText());
    }

}
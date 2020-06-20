package ClientTest;

import Client.Controllers.ControllerBookDetails;
import Client.Services.AddRequest;
import Manager.ActionMode.Book;
import ParentCode.Decode;
import Register.actionMode.User;
import Register.services.FileSystemService;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import java.io.IOException;
import static Client.Controllers.ControllerBooks.bookSave;
import static Login.controllers.ControllerLogin.userSave;
import static org.junit.Assert.*;

public class ControllerBookDetailsTest extends ApplicationTest {

        public static final String DETAILS = "testDetails";
        public static final Book COVER = new Book("titletest", "authortest", "genretest", "detailstest", "igbfiwvfiuwvgiuwvgizwvgziwgvwvgiwvgiuewvgieruvgiuegvmagetest", "pdftest");

        private ControllerBookDetails controller;

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

        controller = new ControllerBookDetails();
        controller.setDetails(new Label());
        controller.setBookCover(new ImageView());
        controller.setExists(new Label());
        controller.setSent(new Label());
        controller.setTen(new Label());

        Decode d = new Decode();

        controller.getDetails1().setText(DETAILS);
        controller.getBookCover().setImage(d.DecodePicture(COVER));

        bookSave = new Book("titletest", "authortest", "genretest", "detailstest", "igbfiwvfiuwvgiuwvgizwvgziwgvwvgiwvgiuewvgieruvgiuegvmagetest", "pdftest");
        userSave = new User("nametest", "surnametest", "addresstest", "emailtest", "phonenumbertest", "usernametest", "passwordtest");
        }

    @Test
    public void testHandleRequest() throws IOException {
            controller.handleRequest();
            assertNotNull(AddRequest.requests);
            assertEquals(1, AddRequest.requests.size());
            assertEquals("Request successfully sent!", controller.getSent().getText());
    }

    @Test
    public void testAddSameRequestTwice() throws IOException {
            controller.handleRequest();
            controller.handleRequest();
            assertNotNull(AddRequest.requests);
            assertEquals(1, AddRequest.requests.size());
            assertEquals("Request already exists!", controller.getExists().getText());
    }

    @Test
    public void testTenRequests() throws IOException {
            for(int i = 0; i <= 10; i++) {
                bookSave = new Book("titletest" + i, "authortest" + i, "genretest", "detailstest", "igbfiwvfiuwvgiuwvgizwvgziwgvwvgiwvgiuewvgieruvgiuegvmagetest", "pdftest");
                controller.handleRequest();
            }
            assertNotNull(AddRequest.requests);
            assertEquals(10, AddRequest.requests.size());
            assertEquals("You already have 10 requests!", controller.getTen().getText());
    }

    @Test
    public void testGetDetails() {
        String st = "Title: " + bookSave.getTitle() + "\n\nAuthor: " + bookSave.getAuthor() + "\n\nGenre: " + bookSave.getGenre() + "\n\nDetails: " + bookSave.getDetails() + '\n';
            controller.getDetails();
            assertEquals(st, controller.getDetails1().getText());
    }
}
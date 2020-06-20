package ManagerTest;

import Client.Services.AddRequest;
import Manager.Controllers.ControllerDecline;
import Register.services.FileSystemService;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import java.io.IOException;
import static org.junit.Assert.*;

public class ControllerDeclineTest extends ApplicationTest {

    public static final String DECLINE_MESSAGE = "DECLINEMESSAGE";

    private ControllerDecline controller;

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

        controller = new ControllerDecline();
        controller.setDeclineMessage(new TextArea());
        controller.setEmpty(new Label());

        controller.getDeclineMessage().setText(DECLINE_MESSAGE);
    }

    @Test
    public void testHandleDecline() throws IOException {
        controller.handleDecline(new javafx.event.ActionEvent());
        assertEquals("DECLINEMESSAGE", controller.getDeclineMessage().getText());
    }

    @Test
    public void testEmptyField() throws IOException {
        controller.getDeclineMessage().setText("");
        controller.handleDecline(new javafx.event.ActionEvent());
        assertEquals("Empty field!", controller.getEmpty().getText());
    }

}
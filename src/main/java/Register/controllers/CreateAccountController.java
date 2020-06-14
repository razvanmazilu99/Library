package Register.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class CreateAccountController extends Controller {

    @FXML
    private Button close;

    @FXML
    private void handleClose() {
        super.handleCloseSimple(close);
    }

    @FXML
    public void handleBack(javafx.event.ActionEvent event) throws IOException {
            URL url = new File("src/main/resources/Login/Login.fxml").toURI().toURL();
            super.handle(event, url);
    }

    @FXML
    public void AccountClient(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/Register/Client Registration Page.fxml").toURI().toURL();
        super.handle(event, url);
    }

    @FXML
    public void AccountManager(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/Register/Manager Registration Page.fxml").toURI().toURL();
        super.handle(event, url);
    }

}

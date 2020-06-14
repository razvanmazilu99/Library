package Register.controllers;

import ParentCode.Exceptions.AlreadyExistsException;
import ParentCode.Exceptions.EmptyField;
import Register.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ClientRegController extends Controller {

    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField address;
    @FXML
    private TextField email;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    private Button close;

    @FXML
    private Label registrationMessage;

    @FXML
    private Label empty;

    @FXML
    private void handleClose() {
        super.handleCloseSimple(close);
    }

    @FXML
    public void handleBack(javafx.event.ActionEvent event) throws IOException {

        URL url = new File("src/main/resources/Register/Create Account Page.fxml").toURI().toURL();
        super.handle(event, url);
    }

    @FXML
    public void ClientRegister(javafx.event.ActionEvent event) throws IOException {

        try {
            UserService.addUserClient(name.getText(), surname.getText(), address.getText(), email.getText(), phoneNumber.getText(), username.getText(), password.getText());
            URL url = new File("src/main/resources/Login/Login.fxml").toURI().toURL();
            super.handle(event, url);
        } catch (AlreadyExistsException e) {
            empty.setText(null);
            registrationMessage.setText(e.getMessage());
        } catch (EmptyField e) {
            registrationMessage.setText(null);
            empty.setText(e.getMessage());
        }
    }
}

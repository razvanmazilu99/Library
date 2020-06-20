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

public class ManagerRegController extends Controller {

    @FXML
    private TextField libraryName;
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
    public void ManagerRegister(javafx.event.ActionEvent event) throws IOException {

        try {
            UserService.addUserManager(libraryName.getText(), address.getText(), email.getText(), phoneNumber.getText(), username.getText(), password.getText());
            URL url = new File("src/main/resources/Login/Login.fxml").toURI().toURL();
            super.handle(event, url);
            UserService.loadUsersFromFile();
        } catch (AlreadyExistsException e) {
            empty.setText(null);
            registrationMessage.setText(e.getMessage());
        } catch (EmptyField e) {
            registrationMessage.setText(null);
            empty.setText(e.getMessage());
        }
    }

    public TextField getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(TextField libraryName) {
        this.libraryName = libraryName;
    }

    public TextField getAddress() {
        return address;
    }

    public void setAddress(TextField address) {
        this.address = address;
    }

    public TextField getEmail() {
        return email;
    }

    public void setEmail(TextField email) {
        this.email = email;
    }

    public TextField getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(TextField phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public TextField getUsername() {
        return username;
    }

    public void setUsername(TextField username) {
        this.username = username;
    }

    public PasswordField getPassword() {
        return password;
    }

    public void setPassword(PasswordField password) {
        this.password = password;
    }

    public Button getClose() {
        return close;
    }

    public void setClose(Button close) {
        this.close = close;
    }

    public Label getRegistrationMessage() {
        return registrationMessage;
    }

    public void setRegistrationMessage(Label registrationMessage) {
        this.registrationMessage = registrationMessage;
    }

    public Label getEmpty() {
        return empty;
    }

    public void setEmpty(Label empty) {
        this.empty = empty;
    }
}

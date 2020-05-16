package Register.controllers;

import Register.exceptions.EmptyField;
import Register.exceptions.UserAlreadyExistsException;
import Register.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ManagerRegController {

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

        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleBack(javafx.event.ActionEvent event) throws IOException {

        URL url = new File("src/main/resources/Register/Create Account Page.fxml").toURI().toURL();
        Parent home = FXMLLoader.load(url);
        Scene scene = new Scene(home);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void ManagerRegister(javafx.event.ActionEvent event) throws IOException {

        try {
            UserService.addUserManager(libraryName.getText(), address.getText(), email.getText(), phoneNumber.getText(), username.getText(), password.getText());
            URL url = new File("src/main/resources/Login/Login.fxml").toURI().toURL();
            Parent home = FXMLLoader.load(url);
            Scene scene = new Scene(home);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (UserAlreadyExistsException e) {
            empty.setText(null);
            registrationMessage.setText(e.getMessage());
        } catch (EmptyField e) {
            registrationMessage.setText(null);
            empty.setText(e.getMessage());
        }
    }

}

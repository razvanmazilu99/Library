package Login.controllers;

import Login.exceptions.IncorrectPassword;
import Login.exceptions.UserDoesNotExistException;
import ParentCode.Controller;
import ParentCode.Exceptions.EmptyField;
import Register.actionMode.User;
import javafx.event.ActionEvent;
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
import javafx.stage.StageStyle;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import static Login.services.LoginService.verifyLogin;
import static Register.services.UserService.users;

public class ControllerLogin extends Controller {

    @FXML
    private Button button;

    @FXML
    private Label usernotexist;

    @FXML
    private Label incorrectpass;

    @FXML
    private Label empty;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    private void handleClose() {
        super.handleCloseSimple(button);
    }

    @FXML
    public void handleCreateAccount(javafx.event.ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/Register/Create Account Page.fxml").toURI().toURL();
        Parent home = FXMLLoader.load(url);
        Scene scene = new Scene(home);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public static String saveUser;
    public static User userSave;
    public static User userSaveManager;

    public static Stage save;

    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException {

        try {
            verifyLogin(username.getText(), password.getText());
            saveUser = username.getText();
            URL url = null;
            for(User u : users)
                if(u.getUsername().equals(saveUser)) {
                    if (u.getRole().equals("Manager")) {
                        userSaveManager = u;
                        url = new File("src/main/resources/Manager/ManagerPage.fxml").toURI().toURL();
                    } else {
                        userSave = u;
                        url = new File("src/main/resources/Client/ClientPage.fxml").toURI().toURL();
                    }
                }
            Parent home = FXMLLoader.load(url);
            Stage stage = (Stage) login.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(home);
            Stage stage1 = new Stage();
            stage1.initStyle(StageStyle.UNDECORATED);
            stage1.setScene(scene);
            stage1.show();

            save = stage1;

        } catch (EmptyField e) {
            usernotexist.setText(null);
            incorrectpass.setText(null);
            empty.setText(e.getMessage());
        } catch (UserDoesNotExistException e) {
            incorrectpass.setText(null);
            empty.setText(null);
            usernotexist.setText(e.getMessage());
        } catch (IncorrectPassword e) {
            usernotexist.setText(null);
            empty.setText(null);
            incorrectpass.setText(e.getMessage());
        }
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Label getUsernotexist() {
        return usernotexist;
    }

    public void setUsernotexist(Label usernotexist) {
        this.usernotexist = usernotexist;
    }

    public Label getIncorrectpass() {
        return incorrectpass;
    }

    public void setIncorrectpass(Label incorrectpass) {
        this.incorrectpass = incorrectpass;
    }

    public Label getEmpty() {
        return empty;
    }

    public void setEmpty(Label empty) {
        this.empty = empty;
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

    public Button getLogin() {
        return login;
    }

    public void setLogin(Button login) {
        this.login = login;
    }
}

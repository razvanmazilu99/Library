package Login.Controller;

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


public class ControllerLogin {

    @FXML
    private Button button, button1;

    @FXML
    private void handleClose() {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleCreateAccount(javafx.event.ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/Register/Create Account Page.fxml").toURI().toURL(); //Login page
        Parent home = FXMLLoader.load(url);
        Scene scene = new Scene(home);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void handleButtonAction() {
        System.out.println("You clicked me");
        Stage stage = (Stage) button1.getScene().getWindow();
        button1.setText("Hello world");
    }
}

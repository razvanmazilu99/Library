package Login.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class ControllerLogin {

    @FXML
    private Button button, button1;

    @FXML
    private void handleClose() {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    public void handleButtonAction() {
        System.out.println("You clicked me");
        Stage stage = (Stage) button1.getScene().getWindow();
        button1.setText("Hello world");
    }
}

package Manager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerManager {

    @FXML
    private Button close;

    @FXML
    private void handleClose() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
}

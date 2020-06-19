package Manager.Controllers;

import ParentCode.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControllerPdf extends Controller {

    @FXML
    private Button close;

    @FXML
    private Button close1;

    @FXML
    private void handleClose() {
        super.handleCloseSimple(close);
    }

    @FXML
    private void handleCloseOK() {
        super.handleCloseSimple(close1);
    }
}

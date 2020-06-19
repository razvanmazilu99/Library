package Manager.Controllers;

import Client.ActionMode.Request;
import Client.Services.AddRequest;
import ParentCode.Exceptions.EmptyField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.io.IOException;
import static Manager.Controllers.ControllerRequest.id_request;

public class ControllerDecline extends ControllerRefresh {

    @FXML
    private Button close;

    @FXML
    private Button submit;

    @FXML
    private TextArea declineMessage;

    @FXML
    private Label empty;

    @FXML
    private void handleClose() {
        super.handleCloseSimple(close);
    }

    @FXML
    private void handleDecline(javafx.event.ActionEvent event) throws IOException {
        try {
            if (declineMessage.getText().equals(""))
                throw new EmptyField();
            for (Request r : AddRequest.requests) {
                if (AddRequest.requests.indexOf(r) == id_request) {
                    Request newRequest = r;
                    newRequest.setDeclineMessage(declineMessage.getText());
                    newRequest.setStatus(2);
                    AddRequest.persistRequest();
                    super.handleCloseSimple(submit);
                    super.refreshRequest(event);
                }
            }
        } catch (EmptyField e) {
            empty.setText(e.getMessage());
        }
    }
}

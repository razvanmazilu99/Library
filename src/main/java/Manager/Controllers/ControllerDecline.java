package Manager.Controllers;

import Client.ActionMode.Request;
import Client.Services.AddRequest;
import ParentCode.Exceptions.EmptyField;
import ParentCode.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import static Manager.Controllers.ControllerRequest.id_request;

public class ControllerDecline extends Controller {

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
    private void handleDecline() {
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
                }
            }
        } catch (EmptyField e) {
            empty.setText(e.getMessage());
        }
    }
}

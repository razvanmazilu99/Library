package Manager.Controllers;

import Client.ActionMode.Request;
import Client.Services.AddRequest;
import Manager.exceptions.BookAlreadyExistsException;
import Manager.exceptions.EmptyField;
import Manager.exceptions.WrongDateException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import static Manager.Controllers.ControllerRequest.id_request;

public class ControllerAccept {

    @FXML
    private Button close;

    @FXML
    private Button submit;

    @FXML
    private DatePicker pickUpDate;

    @FXML
    private DatePicker returnDate;

    @FXML
    private Label message;

    @FXML
    private Label empty;

    @FXML
    private void handleClose() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleAccept() {
        try {
            if (pickUpDate.getValue() == null || returnDate.getValue() == null)
                throw new EmptyField();
            else if (pickUpDate.getValue().compareTo(returnDate.getValue()) > 0)
                throw new WrongDateException();
            for (Request r : AddRequest.requests) {
                if (AddRequest.requests.indexOf(r) == id_request) {
                    Request newRequest = r;
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    newRequest.setPickUpDate(pickUpDate.getValue().format(formatter));
                    newRequest.setReturnDate(returnDate.getValue().format(formatter));
                    newRequest.setStatus(1);
                    AddRequest.persistRequest();
                    Stage stage = (Stage) submit.getScene().getWindow();
                    stage.close();
                }
            }
        } catch (EmptyField e) {
            message.setText(null);
            empty.setText(e.getMessage());
        } catch (WrongDateException e) {
            empty.setText(null);
            message.setText(e.getMessage());
        }
    }
}

package Client.Controllers;

import Client.ActionMode.Request;
import Client.ActionMode.RequestTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.*;
import java.net.URL;
import java.util.*;
import static Login.controllers.ControllerLogin.saveUser;

public class ControllerClientRequest extends Controller implements Initializable {

    @FXML
    private Button close;

    @FXML
    private TableView<RequestTable> table;

    @FXML
    private TableColumn<RequestTable, String> information;

    @FXML
    private TableColumn<RequestTable, String> status;

    private ObservableList<RequestTable> arrayRequests = FXCollections.observableArrayList();

    @FXML
    private void handleClose() throws IOException {
        super.handleClose(close);
    }

    public void initialize(URL location, ResourceBundle resources) {

        information.setCellValueFactory(new PropertyValueFactory<RequestTable, String>("information"));
        status.setCellValueFactory(new PropertyValueFactory<RequestTable, String>("status"));

        table.setPlaceholder(new Label("No requests!"));

        try {
            Client.Services.AddRequest.loadRequestsFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Client.Services.AddRequest.requests == null)
            return;

        for (Request r : Client.Services.AddRequest.requests) {
            if (r.getUsername_user().equals(saveUser)) {
                arrayRequests.add(new RequestTable(r.getTitle_book() + '\n' + r.getAuthor_book() + '\n' + r.getLibraryName_book(), r.getStatus() == 0 ? "NO STATUS!" : (r.getStatus() == 1 ? "ACCEPTED\n\nPick up date: " + r.getPickUpDate() + "\nReturn date: " + r.getReturnDate() : "DECLINED\n\n" + r.getDeclineMessage())));
            }
            table.setItems(arrayRequests);
        }
    }
}

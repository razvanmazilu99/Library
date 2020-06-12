package Client.Controllers;

import Client.ActionMode.Request;
import Client.ActionMode.RequestTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.*;
import java.net.URL;
import java.util.*;
import static Login.controllers.ControllerLogin.saveUser;

public class ControllerClientRequest implements Initializable {

    @FXML
    private Button close;

    @FXML
    private TableView<RequestTable> table;

    @FXML
    private TableColumn<RequestTable, String> information;

    @FXML
    private TableColumn<RequestTable, String> status;

    private ObservableList<RequestTable> arrayRequests = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {

        information.setCellValueFactory(new PropertyValueFactory<RequestTable, String>("information"));
        status.setCellValueFactory(new PropertyValueFactory<RequestTable, String>("status"));

        try {
            Client.Services.AddRequest.loadRequestsFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Client.Services.AddRequest.requests == null)
            return;

        for (Request r : Client.Services.AddRequest.requests) {
            if (r.getUsername_user().equals(saveUser)) {
                arrayRequests.add(new RequestTable(r.getTitle_book() + '\n' + r.getAuthor_book(), r.getStatus() == 0 ? "No status!" : (r.getStatus() == 1 ? "Accepted\nPick up date: " + r.getPickUpDate() + "\nReturn date: " + r.getReturnDate() : "Declined\n" + r.getDeclineMessage())));
            }
            table.setItems(arrayRequests);
        }
    }

    @FXML
    private void handleClose() throws IOException {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
        URL url = new File("src/main/resources/Login/Login.fxml").toURI().toURL();
        Parent home = FXMLLoader.load(url);
        Scene scene = new Scene(home);
        Stage stage1 = new Stage();
        stage1.initStyle(StageStyle.UNDECORATED);
        stage1.setScene(scene);
        stage1.show();
    }

    @FXML
    public void LibrariesPage(javafx.event.ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/Client/ClientPage.fxml").toURI().toURL();
        Parent home = FXMLLoader.load(url);
        Scene scene = new Scene(home);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void RequestPage(javafx.event.ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/Client/ClientRequestPage.fxml").toURI().toURL();
        Parent home = FXMLLoader.load(url);
        Scene scene = new Scene(home);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

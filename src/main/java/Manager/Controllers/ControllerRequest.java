package Manager.Controllers;

import Client.ActionMode.Request;
import Client.Services.AddRequest;
import Manager.ActionMode.RequestsT;
import Manager.Services.AddJSON;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import static Login.controllers.ControllerLogin.userSaveManager;

public class ControllerRequest implements Initializable {

    @FXML
    private Button close;

    @FXML
    private TableView<RequestsT> table;

    @FXML
    private TableColumn<RequestsT, ImageView> picture;

    @FXML
    private TableColumn<RequestsT, String> information;

    @FXML
    private TableColumn<RequestsT, Button> accept;

    @FXML
    private TableColumn<RequestsT, Button> decline;

    private ObservableList<RequestsT> arrayRequest = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {

        picture.setCellValueFactory(new PropertyValueFactory<RequestsT, ImageView>("picture"));
        information.setCellValueFactory(new PropertyValueFactory<RequestsT, String>("information"));
        accept.setCellValueFactory(new PropertyValueFactory<RequestsT, Button>("accept"));
        decline.setCellValueFactory(new PropertyValueFactory<RequestsT, Button>("decline"));

        if (Client.Services.AddRequest.requests == null)
            return;

        for (Request r : Client.Services.AddRequest.requests) {
            if(userSaveManager != null)
                if (r.getLibraryName_book().equals(userSaveManager.getLibraryName())) {
                    ImageView requestPic = new ImageView("Manager/requestIcon.jpg");
                    requestPic.setFitHeight(116);
                    requestPic.setFitWidth(116);

                    Button accept_button = new Button();
                    accept_button.setText("Accept");
                    accept_button.setPrefSize(124, 30);
                    accept_button.setOnAction(e -> {
                    });

                    Button decline_button = new Button();
                    decline_button.setText("Decline");
                    decline_button.setPrefSize(124, 30);
                    decline_button.setOnAction(e -> {
                        //books.remove(b);
                        //AddJSON.persistBooks();
                    });
                    arrayRequest.add(new RequestsT(requestPic, r.getName_user() + ' ' + r.getSurname_user() + '\n' + r.getAddress_user() + '\n' + r.getTitle_book() + ", " + r.getAuthor_book(), accept_button, decline_button));
                }
            table.setItems(arrayRequest);
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
    public void BooksPage(javafx.event.ActionEvent event) throws IOException {
        AddJSON.loadBooksFromFile();
        URL url = new File("src/main/resources/Manager/ManagerPage.fxml").toURI().toURL();
        Parent home = FXMLLoader.load(url);
        Scene scene = new Scene(home);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleRequest(javafx.event.ActionEvent event) throws IOException {
        AddRequest.loadRequestsFromFile();
        URL url = new File("src/main/resources/Manager/RequestPage.fxml").toURI().toURL();
        Parent home = FXMLLoader.load(url);
        Scene scene = new Scene(home);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

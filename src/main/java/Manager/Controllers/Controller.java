package Manager.Controllers;

import Client.Services.AddRequest;
import Manager.Services.AddJSON;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Controller extends ParentCode.Controller {

    @FXML
    public void handle(javafx.event.ActionEvent event, URL url) throws IOException {
        Parent home = FXMLLoader.load(url);
        Scene scene = new Scene(home);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void BooksPage(javafx.event.ActionEvent event) throws IOException {
        AddJSON.loadBooksFromFile();
        URL url = new File("src/main/resources/Manager/ManagerPage.fxml").toURI().toURL();
        this.handle(event, url);
    }

    @FXML
    public void handleRequest(javafx.event.ActionEvent event) throws IOException {
        AddRequest.loadRequestsFromFile();
        URL url = new File("src/main/resources/Manager/RequestPage.fxml").toURI().toURL();
        this.handle(event, url);
    }

    @FXML
    public void handleViews(ActionEvent event) throws IOException {
        AddJSON.loadBooksFromFile();
        URL url = new File("src/main/resources/Manager/ViewsPage.fxml").toURI().toURL();
        this.handle(event, url);
    }
}

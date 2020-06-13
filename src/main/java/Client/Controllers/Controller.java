package Client.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Controller {

    @FXML
    public void handle(javafx.event.ActionEvent event, URL url) throws IOException {
        Parent home = FXMLLoader.load(url);
        Scene scene = new Scene(home);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void handleCloseSimple(Button close) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void handleClose(Button close) throws IOException {
        this.handleCloseSimple(close);
        URL url = new File("src/main/resources/Login/Login.fxml").toURI().toURL();
        Parent home = FXMLLoader.load(url);
        Scene scene = new Scene(home);
        Stage stage1 = new Stage();
        stage1.initStyle(StageStyle.UNDECORATED);
        stage1.setScene(scene);
        stage1.show();
    }

    public void handleAction(URL url) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(url);
        Parent home = loader.load();
        Scene scene = new Scene(home);
        ControllerBookDetails control = loader.getController();
        control.getDetails();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
}

package Manager.Controllers;

import ParentCode.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import static Login.controllers.ControllerLogin.save;
import static Manager.Controllers.Controller.saveR;

public class ControllerRefresh extends Controller {

    public static Stage save1;

    public static void refreshPage(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/Manager/ManagerPage.fxml").toURI().toURL();
        Parent home = FXMLLoader.load(url);
        Scene scene = new Scene(home);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        save1 = stage;
        save.close();
        save = save1;
    }

    public static void refreshRequest(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/Manager/RequestPage.fxml").toURI().toURL();
        Parent home = FXMLLoader.load(url);
        Scene scene = new Scene(home);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        save1 = stage;
        saveR.close();
        saveR = save1;
    }
}

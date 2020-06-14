package ParentCode;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
}

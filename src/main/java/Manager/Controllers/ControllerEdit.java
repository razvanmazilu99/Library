package Manager.Controllers;

import Manager.ActionMode.Book;
import Manager.Services.AddJSON;
import Manager.exceptions.BookAlreadyExistsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static Login.controllers.ControllerLogin.saveUser;
import static Manager.Controllers.ControllerManager.*;
import static Manager.Services.AddJSON.books;
import static Manager.Services.AddJSON.checkBookDoesNotAlreadyExist;

public class ControllerEdit {

    @FXML
    private Button close;

    @FXML
    private Button save;

    @FXML
    private Label bookAlreadyExist;

    @FXML
    private TextField title;

    @FXML
    private TextField author;

    @FXML
    private TextField genre;

    @FXML
    private TextArea details;

    @FXML
    private void handleClose() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    public void populate(String t, String a, String g, String d) {
        title.setText(t);
        author.setText(a);
        genre.setText(g);
        details.setText(d);
    }

    @FXML
    private void handleSave(ActionEvent event) {

    }
}

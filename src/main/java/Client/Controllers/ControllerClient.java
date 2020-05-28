package Client.Controllers;

import Client.ActionMode.LibrariesTable;
import Manager.ActionMode.BooksModelTable;
import Register.actionMode.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static Register.services.UserService.users;

public class ControllerClient implements Initializable {

    @FXML
    private Button close;

    @FXML
    private TableView<LibrariesTable> table;

    @FXML
    private TableColumn<LibrariesTable, ImageView> image;

    @FXML
    private TableColumn<LibrariesTable, Hyperlink> libraryName;

    private ObservableList<LibrariesTable> arrayUser = FXCollections.observableArrayList();

    @FXML
    private void handleClose() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    public void initialize(URL location, ResourceBundle resources) {

        image.setCellValueFactory(new PropertyValueFactory<LibrariesTable, ImageView>("image"));
        libraryName.setCellValueFactory(new PropertyValueFactory<LibrariesTable, Hyperlink>("libraryName"));

        if (users == null)
            return;

        for (User u : users) {
            if(u.getRole().equals("Manager")) {
                ImageView libraryPic = new ImageView("Client/libraryIcon.jpg");
                libraryPic.setFitHeight(144);
                libraryPic.setFitWidth(144);
                Hyperlink hyp = new Hyperlink();
                hyp.setText(u.getLibraryName());

                hyp.setOnAction (e -> {
                    try {
                        URL url = new File("src/main/resources/Manager/EditBookPage.fxml").toURI().toURL();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(url);
                        Parent home = loader.load();
                        Scene scene = new Scene(home);
                        Stage stage = new Stage();
                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                 });

                arrayUser.add(new LibrariesTable(libraryPic, hyp));
            }
        }
        table.setItems(arrayUser);
    }
}

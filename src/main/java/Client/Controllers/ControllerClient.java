package Client.Controllers;

import Client.ActionMode.LibrariesTable;
import Register.actionMode.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import static Register.services.UserService.users;

public class ControllerClient extends Controller implements Initializable {

    @FXML
    private Button close;

    @FXML
    private TextField filter;

    @FXML
    private TableView<LibrariesTable> table;

    @FXML
    private TableColumn<LibrariesTable, ImageView> image;

    @FXML
    private TableColumn<LibrariesTable, Hyperlink> libraryName;

    public static List<User> listOfManagers = new ArrayList<>();
    private ObservableList<LibrariesTable> arrayUser = FXCollections.observableArrayList();

    public static String librarySave;
    public static String libraryNameSave;

    @FXML
    private void handleClose() throws IOException {
        super.handleClose(close);
    }

    public void initialize(URL location, ResourceBundle resources) {

        image.setCellValueFactory(new PropertyValueFactory<LibrariesTable, ImageView>("image"));
        libraryName.setCellValueFactory(new PropertyValueFactory<LibrariesTable, Hyperlink>("libraryName"));

        if (users == null)
            return;

        if(listOfManagers.size() == 0) {
            for (User u : users) {
                if (u.getLibraryName() != null) {
                    listOfManagers.add(u);
                }
            }
        }

        if (listOfManagers.size() > 0) {
            Collections.sort(listOfManagers, new Comparator<User>() {
                public int compare(final User object1, final User object2) {
                    return object1.getLibraryName().compareTo(object2.getLibraryName());
                }
            });
        }

        for (User u : listOfManagers) {
            if(u.getRole().equals("Manager")) {
                ImageView libraryPic = new ImageView("Client/libraryIcon.jpg");
                libraryPic.setFitHeight(144);
                libraryPic.setFitWidth(144);
                Hyperlink hyp = new Hyperlink();
                hyp.setText(u.getLibraryName());

                hyp.setOnAction (e -> {
                    try {
                        librarySave = u.getUsername();
                        libraryNameSave = u.getLibraryName();
                        URL url = new File("src/main/resources/Client/BooksPage.fxml").toURI().toURL();
                        super.handle(e, url);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                 });

                arrayUser.add(new LibrariesTable(libraryPic, hyp));

                FilteredList<LibrariesTable> filteredData = new FilteredList<>(arrayUser, b -> true);
                filter.textProperty().addListener((observable, oldValue, newValue) -> {
                    filteredData.setPredicate( user -> {

                        if (newValue == null || newValue.isEmpty())
                            return true;

                        String lowerCaseFilter = newValue.toLowerCase();

                        if (user.getLibraryName().getText().toLowerCase().indexOf(lowerCaseFilter) != -1)
                            return true;
                        else
                            return false;
                    });
                });

                SortedList<LibrariesTable> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(table.comparatorProperty());
                table.setItems(sortedData);
            }
        }
    }
}

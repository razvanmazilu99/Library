package Manager.Controllers;

import Manager.ActionMode.Book;
import Manager.ActionMode.ViewsTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static Login.controllers.ControllerLogin.saveUser;
import static Manager.Services.AddJSON.books;

public class ControllerViews extends Controller implements Initializable {

    @FXML
    private Button close;

    @FXML
    private TableView<ViewsTable> table;

    @FXML
    private TableColumn<ViewsTable, String> information;

    @FXML
    private TableColumn<ViewsTable, Integer> noOfViews;

    private ObservableList<ViewsTable> arrayViews = FXCollections.observableArrayList();

    public void sorting(ObservableList<ViewsTable> av) {

        ViewsTable aux;
        for(int i = 0; i < av.size(); i++)
            for(int j = i+1; j < av.size(); j++)
                if(av.get(i).getNoOfViews() <  av.get(j).getNoOfViews()) {
                    aux = av.get(i);
                    av.set(i, av.get(j));
                    av.set(j, aux);
                }
    }

    public void initialize(URL location, ResourceBundle resources) {

        information.setCellValueFactory(new PropertyValueFactory<ViewsTable, String>("information"));
        noOfViews.setCellValueFactory(new PropertyValueFactory<ViewsTable, Integer>("noOfViews"));

        table.setPlaceholder(new Label("No views!"));

        if (books == null)
            return;

        for (Book b : books) {
            if (b.getUser().equals(saveUser)) {
                arrayViews.add(new ViewsTable(b.getTitle() + '\n' + b.getAuthor(), b.getNoViews()));
            }
            sorting(arrayViews);
            table.setItems(arrayViews);
        }
    }

    @FXML
    private void handleClose() throws IOException {
        super.handleClose(close);
    }
}

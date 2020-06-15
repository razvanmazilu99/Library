package Client.Controllers;

import Client.ActionMode.AllBooksTable;
import Manager.ActionMode.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.*;
import java.net.URL;
import java.util.*;
import static Manager.Services.AddJSON.books;

public class ControllerAllBooks extends Controller implements Initializable {

    @FXML
    private Button close;

    @FXML
    private TableView<AllBooksTable> table;

    @FXML
    private TableColumn<AllBooksTable, String> information;

    @FXML
    private TableColumn<AllBooksTable, String> library;

    private ObservableList<AllBooksTable> arrayAllBooks = FXCollections.observableArrayList();

    @FXML
    private void handleClose() throws IOException {
        super.handleClose(close);
    }

    public void initialize(URL location, ResourceBundle resources) {

        information.setCellValueFactory(new PropertyValueFactory<AllBooksTable, String>("information"));
        library.setCellValueFactory(new PropertyValueFactory<AllBooksTable, String>("library"));

        table.setPlaceholder(new Label("No books!"));

        try {
            Manager.Services.AddJSON.loadBooksFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (books.size() > 0) {
            Collections.sort(books, new Comparator<Book>() {
                public int compare(final Book object1, final Book object2) {
                    return object1.getTitle().compareTo(object2.getTitle());
                }
            });
        }

        if (books == null)
            return;

        for (Book b : books) {
            arrayAllBooks.add(new AllBooksTable( b.getTitle() + '\n' + b.getAuthor(), b.getUser()));
        }
        table.setItems(arrayAllBooks);
    }
}

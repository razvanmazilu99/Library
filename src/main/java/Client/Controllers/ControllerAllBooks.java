package Client.Controllers;

import Client.ActionMode.AllBooksTable;
import Manager.ActionMode.Book;
import Register.actionMode.User;
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

import static Client.Controllers.ControllerClient.libraryNameSave;
import static Manager.Services.AddJSON.books;
import static ParentCode.Service.users;

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

    public void sorting(List<Book> abt) {

        Book aux;
        for(int i = 0; i < abt.size(); i++)
            for(int j = i+1; j < abt.size(); j++)
                if(abt.get(i).getNoViews() <  abt.get(j).getNoViews()) {
                    aux = abt.get(i);
                    abt.set(i, abt.get(j));
                    abt.set(j, aux);
                }
    }

    public void initialize(URL location, ResourceBundle resources) {

        information.setCellValueFactory(new PropertyValueFactory<AllBooksTable, String>("information"));
        library.setCellValueFactory(new PropertyValueFactory<AllBooksTable, String>("library"));

        table.setPlaceholder(new Label("No books!"));

        if (books == null)
            return;

        sorting(books);

        for (Book b : books) {
            for(User u : users)
                if(u.getLibraryName() != null && u.getUsername().equals(b.getUser()))
                    arrayAllBooks.add(new AllBooksTable( b.getTitle() + '\n' + b.getAuthor(),  u.getLibraryName() + "\nViews: " + b.getNoViews()));
        }
        table.setItems(arrayAllBooks);
    }
}

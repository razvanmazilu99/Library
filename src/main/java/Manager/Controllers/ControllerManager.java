package Manager.Controllers;

import Client.ActionMode.Request;
import Client.Services.AddRequest;
import Manager.ActionMode.Book;
import Manager.ActionMode.BooksModelTable;
import Manager.Services.AddJSON;
import ParentCode.Decode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import static Login.controllers.ControllerLogin.saveUser;
import static Login.controllers.ControllerLogin.userSaveManager;
import static Manager.Services.AddJSON.books;

public class ControllerManager extends Controller implements Initializable {

    @FXML
    private Button close;

    @FXML
    private TableView<BooksModelTable> table;

    @FXML
    private TableColumn<BooksModelTable, ImageView> picture;

    @FXML
    private TableColumn<BooksModelTable, String> information;

    @FXML
    private TableColumn<BooksModelTable, Button> editBook;

    @FXML
    private TableColumn<BooksModelTable, Button> deleteBook;

    @FXML
    private TableColumn<BooksModelTable, Button> pdf;

    private ObservableList<BooksModelTable> arrayBook = FXCollections.observableArrayList();

    public static int id;

    public void initialize(URL location, ResourceBundle resources) {

        picture.setCellValueFactory(new PropertyValueFactory<BooksModelTable, ImageView>("picture"));
        information.setCellValueFactory(new PropertyValueFactory<BooksModelTable, String>("information"));
        editBook.setCellValueFactory(new PropertyValueFactory<BooksModelTable, Button>("editBook"));
        deleteBook.setCellValueFactory(new PropertyValueFactory<BooksModelTable, Button>("deleteBook"));
        pdf.setCellValueFactory(new PropertyValueFactory<BooksModelTable, Button>("pdf"));

        table.setPlaceholder(new Label("No books in your Library!"));

        refreshTable();
    }

    public void refreshTable() {

        arrayBook.clear();

        if (books == null)
            return;

        for (Book b : books) {
            if (b.getUser().equals(saveUser)) {
                Decode dp = new Decode();
                ImageView bookCover = new ImageView(dp.DecodePicture(b));
                bookCover.setFitHeight(150);
                bookCover.setFitWidth(150);
                Button edit = new Button();
                edit.setText("Edit book");
                edit.setPrefSize(140, 30);
                edit.setOnAction(e -> {
                    try {
                        URL url = new File("src/main/resources/Manager/EditBookPage.fxml").toURI().toURL();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(url);
                        Parent home = loader.load();
                        Scene scene = new Scene(home);
                        ControllerEdit ce =  loader.getController();
                        ce.populate(b.getTitle(), b.getAuthor(), b.getGenre(), b.getDetails());
                        id = books.indexOf(b);
                        Stage stage = new Stage();
                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });

                Button delete = new Button();
                delete.setText("Delete Book");
                delete.setPrefSize(140, 30);
                delete.setOnAction(e -> {
                    books.remove(b);
                    if(AddRequest.requests != null)
                        for (Request r : AddRequest.requests)
                            if (b.getTitle().equals(r.getTitle_book()) && b.getAuthor().equals(r.getAuthor_book()) && b.getUser().equals(userSaveManager.getUsername())) {
                                Request newRequest = r;
                                newRequest.setDeclineMessage("Book does not exist in the library anymore!");
                                newRequest.setStatus(2);
                                AddRequest.persistRequest();
                    }
                    AddJSON.persistBooks();
                    refreshTable();
                });

                if(b.getPdf() != null) {
                    Button bookPDF = new Button();
                    bookPDF.setText("Read Online");
                    bookPDF.setPrefSize(140, 30);
                    bookPDF.setOnAction(e -> {
                        dp.DecodePdf(b);
                    });
                    arrayBook.add(new BooksModelTable(bookCover, b, edit, delete, bookPDF));
                }
                else {
                    arrayBook.add(new BooksModelTable(bookCover, b, edit, delete));
                }
            }
            table.setItems(arrayBook);
        }
    }

    @FXML
    private void handleClose() throws IOException {
        super.handleClose(close);
    }

    @FXML
    public void handleAddBook(javafx.event.ActionEvent event) throws IOException {

        URL url = new File("src/main/resources/Manager/AddBookPage.fxml").toURI().toURL();
        Parent home = FXMLLoader.load(url);
        Scene scene = new Scene(home);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
}

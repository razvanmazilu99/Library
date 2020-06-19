package Client.Controllers;

import Client.ActionMode.BooksTable;
import Manager.ActionMode.Book;
import Manager.Services.AddJSON;
import ParentCode.Decode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.List;
import static Client.Controllers.ControllerClient.librarySave;
import static Manager.Services.AddJSON.books;

public class ControllerBooks extends Controller implements Initializable {

    @FXML
    private Button close;

    @FXML
    private TextField filter;

    @FXML
    private TableView<BooksTable> table;

    @FXML
    private TableColumn<BooksTable, ImageView> bookCover;

    @FXML
    private TableColumn<BooksTable, Hyperlink> title;

    @FXML
    private TableColumn<BooksTable, String> author;

    @FXML
    private TableColumn<BooksTable, String> genre;

    @FXML
    private TableColumn<BooksTable, Button> openBook;

    public static List<Book> listOfBooks = new ArrayList<>();
    private ObservableList<BooksTable> arrayBooks = FXCollections.observableArrayList();

    private static String libraryUser;
    public static Book bookSave;

    @FXML
    private void handleClose() throws IOException {
        super.handleClose(close);
    }

    public void initialize(URL location, ResourceBundle resources) {

        bookCover.setCellValueFactory(new PropertyValueFactory<BooksTable, ImageView>("bookCover"));
        title.setCellValueFactory(new PropertyValueFactory<BooksTable, Hyperlink>("title"));
        author.setCellValueFactory(new PropertyValueFactory<BooksTable, String>("author"));
        genre.setCellValueFactory(new PropertyValueFactory<BooksTable, String>("genre"));
        openBook.setCellValueFactory(new PropertyValueFactory<BooksTable, Button>("openBook"));

        table.setPlaceholder(new Label("No books in the library!"));

        if (books == null)
            return;

        if(listOfBooks.size() == 0) {
            for (Book b : books) {
                if (b.getUser().equals(librarySave)) {
                    listOfBooks.add(b);
                }
            }
        }

        if(libraryUser != librarySave) {
            listOfBooks = new ArrayList<>();
            for (Book b : books) {
                if (b.getUser().equals(librarySave)) {
                    listOfBooks.add(b);
                }
            }
        }

        if (listOfBooks.size() > 0) {
            Collections.sort(listOfBooks, new Comparator<Book>() {
                public int compare(final Book object1, final Book object2) {
                    return object1.getTitle().compareTo(object2.getTitle());
                }
            });
        }

        for (Book b : listOfBooks) {
            Decode dp = new Decode();
            ImageView bookCover = new ImageView(dp.DecodePicture(b));
            bookCover.setFitHeight(176);
            bookCover.setFitWidth(176);

            Hyperlink hyp = new Hyperlink(b.getTitle());

            if (b.getPdf() != null) {
                Button bookPDF = new Button();
                bookPDF.setText("Read Online");
                bookPDF.setPrefSize(150, 30);
                bookPDF.setOnAction(e -> {
                    try {
                        URL url = new File("src/main/resources/Client/PdfError.fxml").toURI().toURL();
                        dp.DecodePdf(b, url);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                arrayBooks.add(new BooksTable(bookCover, hyp, b.getAuthor(), b.getGenre(), bookPDF));
            } else {
                arrayBooks.add(new BooksTable(bookCover, hyp, b.getAuthor(), b.getGenre()));
            }

            hyp.setOnAction (e -> {
                try {
                    b.setNoViews(b.getNoViews() + 1);
                    bookSave = b;
                    AddJSON.persistBooks();
                    URL url = new File("src/main/resources/Client/BookDetails.fxml").toURI().toURL();
                    super.handleAction(url);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            FilteredList<BooksTable> filteredData = new FilteredList<>(arrayBooks, bo -> true);
            filter.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate( book -> {

                    if (newValue == null || newValue.isEmpty())
                        return true;

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (book.getTitle().getText().toLowerCase().indexOf(lowerCaseFilter) != -1)
                        return true;
                    else if (book.getAuthor().toLowerCase().indexOf(lowerCaseFilter) != -1)
                        return true;
                    else if (book.getGenre().toLowerCase().indexOf(lowerCaseFilter) != -1)
                        return true;
                    else return false;
                });
            });

            SortedList<BooksTable> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedData);
        }
        libraryUser = librarySave;
    }
}
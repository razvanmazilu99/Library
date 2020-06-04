package Client.Controllers;

import Client.ActionMode.BooksTable;
import Client.ActionMode.LibrariesTable;
import Manager.ActionMode.Book;
import Register.actionMode.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.List;

import static Client.Controllers.ControllerClient.librarySave;
import static Client.Controllers.ControllerClient.listOfManagers;
import static Manager.Services.AddJSON.books;
import static Register.services.UserService.users;

public class ControllerBooks<libraryUser> implements Initializable {

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

    public static List<Book> listOfBooks = new ArrayList<>();
    private ObservableList<BooksTable> arrayBooks = FXCollections.observableArrayList();

    @FXML
    private void handleClose() throws IOException {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
        URL url = new File("src/main/resources/Login/Login.fxml").toURI().toURL();
        Parent home = FXMLLoader.load(url);
        Scene scene = new Scene(home);
        Stage stage1 = new Stage();
        stage1.initStyle(StageStyle.UNDECORATED);
        stage1.setScene(scene);
        stage1.show();
    }

    private static String libraryUser;
    public static Book bookSave;

    public void initialize(URL location, ResourceBundle resources) {

        bookCover.setCellValueFactory(new PropertyValueFactory<BooksTable, ImageView>("bookCover"));
        title.setCellValueFactory(new PropertyValueFactory<BooksTable, Hyperlink>("title"));
        author.setCellValueFactory(new PropertyValueFactory<BooksTable, String>("author"));
        genre.setCellValueFactory(new PropertyValueFactory<BooksTable, String>("genre"));


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
            byte[] decodedBytes = Base64.getDecoder().decode(b.getImage().getBytes());
            ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
            BufferedImage img = null;
            try {
                img = ImageIO.read(bis);
            } catch (IOException e) {
                e.printStackTrace();
            }
            WritableImage image = null;
            if (img != null) {
                image = new WritableImage(img.getWidth(), img.getHeight());
                PixelWriter pw = image.getPixelWriter();
                for (int x = 0; x < img.getWidth(); x++) {
                    for (int y = 0; y < img.getHeight(); y++) {
                        pw.setArgb(x, y, img.getRGB(x, y));
                    }
                }
            }
            ImageView bookCover = new ImageView(image);
            bookCover.setFitHeight(176);
            bookCover.setFitWidth(176);

            Hyperlink hyp = new Hyperlink(b.getTitle());
            arrayBooks.add(new BooksTable(bookCover, hyp, b.getAuthor(), b.getGenre()));

            hyp.setOnAction (e -> {
                try {
                    bookSave = b;
                    URL url = new File("src/main/resources/Client/BookDetails.fxml").toURI().toURL();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(url);
                    Parent home = loader.load();
                    Scene scene = new Scene(home);
                    ControllerBookDetails control = loader.getController();
                    control.getDetails();
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(scene);
                    stage.show();
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

    @FXML
    public void LibrariesPage(javafx.event.ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/Client/ClientPage.fxml").toURI().toURL();
        Parent home = FXMLLoader.load(url);
        Scene scene = new Scene(home);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
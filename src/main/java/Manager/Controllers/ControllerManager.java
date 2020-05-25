package Manager.Controllers;

import Manager.ActionMode.Book;
import Manager.ActionMode.BooksModelTable;
import Manager.Services.ReadJSON;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static Login.controllers.ControllerLogin.saveUser;

public class ControllerManager implements Initializable {

    @FXML
    private Button close;

    @FXML
    protected TableView<BooksModelTable> table;

    @FXML
    private TableColumn<BooksModelTable, File> picture;

    @FXML
    private TableColumn<BooksModelTable, String> information;

    @FXML
    private TableColumn<BooksModelTable, File> pdf;

    private ObservableList<BooksModelTable> arrayBook = FXCollections.observableArrayList();

    private static List<Book> listOfBooks;

    static {
        try {
            listOfBooks = ReadJSON.readBooksFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String pathPdf;

    public void initialize(URL location, ResourceBundle resources) {

        picture.setCellValueFactory(new PropertyValueFactory<BooksModelTable, File>("picture"));
        information.setCellValueFactory(new PropertyValueFactory<BooksModelTable, String>("information"));
        pdf.setCellValueFactory(new PropertyValueFactory<BooksModelTable, File>("pdf"));
        if (listOfBooks == null)
            return;
        for (Book b : listOfBooks) {
            if (b.getUser().equals(saveUser)) {
                String pathImage = b.getImage().getName();
                ImageView bookCover;
                ImageView bookPDF;
                bookCover = new ImageView("Manager/Books./" + pathImage);
                bookCover.setFitHeight(150);
                bookCover.setFitWidth(150);
                bookPDF = new ImageView("Manager/OpenBook.png");
                bookPDF.setFitHeight(82);
                bookPDF.setFitWidth(82);
                try {
                    pathPdf = b.getPdf().getName();
                } catch (NullPointerException e) {
                }
                    bookPDF.setOnMouseClicked(new EventHandler<MouseEvent>() {

                        @Override
                        public void handle(MouseEvent event) {
                            try {
                                File file = new File(getClass().getClassLoader().getResource("Manager/PDF/" + pathPdf).getFile());
                                Desktop desktop = Desktop.getDesktop();
                                desktop.open(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            event.consume();
                        }
                    });
                arrayBook.add(new BooksModelTable(bookCover, bookPDF, b));
            }
            table.setItems(arrayBook);
        }
    }

    @FXML
    private void handleClose() throws IOException {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
        URL url = new File("src/main/resources/Login/Login.fxml").toURI().toURL();
        Parent home = FXMLLoader.load(url);
        Scene scene = new Scene(home);
        Stage stage1 = new Stage(); // = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage1.initStyle(StageStyle.UNDECORATED);
        stage1.setScene(scene);
        stage1.show();
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

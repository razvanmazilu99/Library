package Manager.Controllers;

import Manager.ActionMode.Book;
import Manager.ActionMode.BooksModelTable;
import Manager.Services.AddJSON;
import Manager.Services.ReadJSON;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;

import static Login.controllers.ControllerLogin.saveUser;

public class ControllerManager implements Initializable {

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

    public static List<Book> listOfBooks;

    static {
        try {
            listOfBooks = ReadJSON.readBooksFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int id;

    public void initialize(URL location, ResourceBundle resources) {

        picture.setCellValueFactory(new PropertyValueFactory<BooksModelTable, ImageView>("picture"));
        information.setCellValueFactory(new PropertyValueFactory<BooksModelTable, String>("information"));
        editBook.setCellValueFactory(new PropertyValueFactory<BooksModelTable, Button>("editBook"));
        deleteBook.setCellValueFactory(new PropertyValueFactory<BooksModelTable, Button>("deleteBook"));
        pdf.setCellValueFactory(new PropertyValueFactory<BooksModelTable, Button>("pdf"));
        if (listOfBooks == null)
            return;
        for (Book b : listOfBooks) {
            if (b.getUser().equals(saveUser)) {
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
                    bookCover.setFitHeight(150);
                    bookCover.setFitWidth(150);
                    Button edit = new Button();
                    edit.setText("Edit book");
                    edit.setPrefSize(140, 30);
                    Button delete = new Button();
                    delete.setText("Delete Book");
                    delete.setPrefSize(140, 30);
                    edit.setOnAction(e -> {
                        try {
                            URL url = new File("src/main/resources/Manager/EditBookPage.fxml").toURI().toURL();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(url);
                            Parent home = loader.load();
                            Scene scene = new Scene(home);
                            ControllerEdit ce =  loader.getController();
                            ce.populate(b.getTitle(), b.getAuthor(), b.getGenre(), b.getDetails());
                            id = listOfBooks.indexOf(b);
                            Stage stage = new Stage();
                            stage.initStyle(StageStyle.UNDECORATED);
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    if(b.getPdf() != null) {
                        Button bookPDF = new Button();
                        bookPDF.setText("Read Online");
                        bookPDF.setPrefSize(140, 30);
                        bookPDF.setOnAction(e -> {
                            Desktop desktop = Desktop.getDesktop();
                            try {
                                byte[] pdf1 = Base64.getDecoder().decode(b.getPdf().getBytes());
                                OutputStream fstream = new FileOutputStream("ReadOnline" + ".pdf");
                                for (Byte p : pdf1) {
                                    fstream.write(p);
                                }
                                fstream.close();
                                desktop.open(new File("ReadOnline" + ".pdf"));
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
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

    @FXML
    public void BooksPage(javafx.event.ActionEvent event) throws IOException {
        AddJSON.loadBooksFromFile();
        URL url = new File("src/main/resources/Manager/ManagerPage.fxml").toURI().toURL();
        Parent home = FXMLLoader.load(url);
        Scene scene = new Scene(home);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
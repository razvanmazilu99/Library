package Manager.Controllers;

import Client.ActionMode.BooksTable;
import Client.Services.AddRequest;
import Manager.ActionMode.Book;
import Manager.ActionMode.ViewsTable;
import Manager.Services.AddJSON;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;
import static Login.controllers.ControllerLogin.saveUser;
import static Manager.Services.AddJSON.books;

public class ControllerViews implements Initializable {

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

    @FXML
    public void handleRequest(javafx.event.ActionEvent event) throws IOException {
        AddRequest.loadRequestsFromFile();
        URL url = new File("src/main/resources/Manager/RequestPage.fxml").toURI().toURL();
        Parent home = FXMLLoader.load(url);
        Scene scene = new Scene(home);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleViews(ActionEvent event) throws IOException {
        AddJSON.loadBooksFromFile();
        URL url = new File("src/main/resources/Manager/ViewsPage.fxml").toURI().toURL();
        Parent home = FXMLLoader.load(url);
        Scene scene = new Scene(home);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

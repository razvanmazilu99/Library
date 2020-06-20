package Manager.Controllers;

import Client.ActionMode.Request;
import Client.Services.AddRequest;
import Manager.ActionMode.Book;
import Manager.Services.AddJSON;
import ParentCode.Exceptions.AlreadyExistsException;
import ParentCode.Exceptions.EmptyField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;
import static Login.controllers.ControllerLogin.saveUser;
import static Login.controllers.ControllerLogin.userSaveManager;
import static Manager.Controllers.ControllerManager.*;
import static Manager.Services.AddJSON.*;

public class ControllerEdit extends ControllerRefresh {

    @FXML
    private Button close;

    @FXML
    private Button save;

    @FXML
    private Label bookAlreadyExist;

    @FXML
    private Label empty;

    @FXML
    private TextField title;

    @FXML
    private TextField author;

    @FXML
    private TextField genre;

    @FXML
    private TextArea details;

    @FXML
    private void handleClose() {
        super.handleCloseSimple(close);
    }

    public void populate(String t, String a, String g, String d) {
        title.setText(t);
        author.setText(a);
        genre.setText(g);
        details.setText(d);
    }

    @FXML
    public void handleSave(ActionEvent event) throws IOException {
        try {
            checkBookDoesNotAlreadyExist1(title.getText(), author.getText(), saveUser, id);
            checkEmptyField1(title.getText(), author.getText(), genre.getText(), details.getText());
            for(Book b : books) {
                if (books.indexOf(b) == id) {
                    Book newBook = new Book(title.getText(), author.getText(), genre.getText(), details.getText(), b.getImage(), b.getPdf());
                    if (!b.getTitle().equals(newBook.getTitle())) {
                        if(AddRequest.requests != null)
                            for (Request r : AddRequest.requests)
                                if(b.getTitle().equals(r.getTitle_book()) && b.getAuthor().equals(r.getAuthor_book()) && userSaveManager.getLibraryName().equals(r.getLibraryName_book()))
                                    r.setTitle_book(newBook.getTitle());
                        b.setTitle(newBook.getTitle());
                    }
                    if (!b.getAuthor().equals(newBook.getAuthor())) {
                        if(AddRequest.requests != null)
                            for (Request r : AddRequest.requests)
                                if(b.getTitle().equals(r.getTitle_book()) && b.getAuthor().equals(r.getAuthor_book()) && userSaveManager.getLibraryName().equals(r.getLibraryName_book()))
                                    r.setAuthor_book(newBook.getAuthor());
                        b.setAuthor(newBook.getAuthor());
                    }
                    if (!b.getGenre().equals(newBook.getGenre())) {
                        b.setGenre(newBook.getGenre());
                    }
                    if (!b.getDetails().equals(newBook.getDetails())) {
                        b.setDetails(newBook.getDetails());
                    }
                    if(AddRequest.requests != null)
                        AddRequest.persistRequest();
                    AddJSON.persistBooks();
                    super.handleCloseSimple(save);
                    super.refreshPage(event);
                }
            }
        } catch (AlreadyExistsException e) {
            empty.setText(null);
            bookAlreadyExist.setText(e.getMessage());
        } catch (EmptyField e) {
            bookAlreadyExist.setText(null);
            empty.setText(e.getMessage());
        }
    }

    public Label getBookAlreadyExist() {
        return bookAlreadyExist;
    }

    public void setBookAlreadyExist(Label bookAlreadyExist) {
        this.bookAlreadyExist = bookAlreadyExist;
    }

    public Label getEmpty() {
        return empty;
    }

    public void setEmpty(Label empty) {
        this.empty = empty;
    }

    public TextField getTitle() {
        return title;
    }

    public void setTitle(TextField title) {
        this.title = title;
    }

    public TextField getAuthor() {
        return author;
    }

    public void setAuthor(TextField author) {
        this.author = author;
    }

    public TextField getGenre() {
        return genre;
    }

    public void setGenre(TextField genre) {
        this.genre = genre;
    }

    public TextArea getDetails() {
        return details;
    }

    public void setDetails(TextArea details) {
        this.details = details;
    }

    public Button getSave() {
        return save;
    }

    public void setSave(Button save) {
        this.save = save;
    }
}

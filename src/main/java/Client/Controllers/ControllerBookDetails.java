package Client.Controllers;

import ParentCode.Controller;
import ParentCode.Exceptions.AlreadyExistsException;
import Client.Exception.TenRequestsException;
import Client.Services.AddRequest;
import ParentCode.Decode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.io.IOException;
import static Client.Controllers.ControllerBooks.bookSave;
import static Login.controllers.ControllerLogin.userSave;

public class ControllerBookDetails extends Controller {

    @FXML
    private Button close;

    @FXML
    private Label details;

    @FXML
    private ImageView bookCover;

    @FXML
    private Label sent;

    @FXML
    private Label ten;

    @FXML
    private Label exists;

    @FXML
    public void handleRequest() throws IOException {
        AddRequest.loadRequestsFromFile();
        try {
            AddRequest.addRequest(userSave, bookSave);
            sent.setText("Request successfully sent!");
        } catch (AlreadyExistsException e) {
            sent.setText(null);
            ten.setText(null);
            exists.setText(e.getMessage());
        } catch (TenRequestsException e) {
            sent.setText(null);
            exists.setText(null);
            ten.setText(e.getMessage());
        }
    }

    @FXML
    private void handleClose() {
        super.handleCloseSimple(close);
    }

    @FXML
    public void getDetails() {

        Decode dp = new Decode();

        bookCover.setImage(dp.DecodePicture(bookSave));
        bookCover.setFitHeight(220);
        bookCover.setFitWidth(260);

        details.setText("Title: " + bookSave.getTitle() + "\n\nAuthor: " + bookSave.getAuthor() + "\n\nGenre: " + bookSave.getGenre() + "\n\nDetails: " + bookSave.getDetails() + '\n');
    }

    public Label getDetails1() {
        return details;
    }

    public Button getClose() {
        return close;
    }

    public void setClose(Button close) {
        this.close = close;
    }

    public void setDetails(Label details) {
        this.details = details;
    }

    public ImageView getBookCover() {
        return bookCover;
    }

    public void setBookCover(ImageView bookCover) {
        this.bookCover = bookCover;
    }

    public Label getSent() {
        return sent;
    }

    public void setSent(Label sent) {
        this.sent = sent;
    }

    public Label getTen() {
        return ten;
    }

    public void setTen(Label ten) {
        this.ten = ten;
    }

    public Label getExists() {
        return exists;
    }

    public void setExists(Label exists) {
        this.exists = exists;
    }
}

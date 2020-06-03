package Client.Controllers;

import Client.Services.AddRequest;
import Manager.Services.AddJSON;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import static Client.Controllers.ControllerBooks.bookSave;
import static Login.controllers.ControllerLogin.saveUser;
import static Login.controllers.ControllerLogin.userSave;


public class ControllerBookDetails {

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
    public void handleRequest() {
            //if (userSave != null)
               // AddRequest.addRequest(userSave, bookSave);
            sent.setText("Request successfully sent!");
    }

    @FXML
    private void handleClose() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void getDetails() {

        byte[] decodedBytes = Base64.getDecoder().decode(bookSave.getImage().getBytes());
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

        bookCover.setImage(image);
        bookCover.setFitHeight(180);
        bookCover.setFitWidth(180);

        details.setText("Title: " + bookSave.getTitle() + "\n\nAuthor: " + bookSave.getAuthor() + "\n\nGenre: " + bookSave.getGenre() + "\n\nDetails: " + bookSave.getDetails() + '\n');
    }
}

package Manager.Controllers;

import Manager.Services.AddJSON;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class ControllerAdd {

    @FXML
    private Button close;

    @FXML
    private ListView listView;

    @FXML
    private ListView listView1;

    @FXML
    private TextField title;

    @FXML
    private TextField author;

    @FXML
    private TextField genre;

    @FXML
    private TextArea details;

    @FXML
    private Button submit;

    @FXML
    private Label empty;

    @FXML
    private Label bookAlreadyExist;

    @FXML
    private void handleClose() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void selectBookPdf(ActionEvent event) {
        FileChooser file = new FileChooser();
        file.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File selectedFile = file.showOpenDialog(null);

        if(selectedFile != null) {
            listView1.getItems().add(selectedFile.getAbsoluteFile());
        }
    }

    @FXML
    private void selectBookPicture(ActionEvent event) {
        FileChooser file = new FileChooser();
        file.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Picture Files", "*.jpg", "*.jpeg", "*.png"));
        File selectedFile = file.showOpenDialog(null);

        if(selectedFile != null) {
            listView.getItems().add(selectedFile.getAbsoluteFile());
        }
    }

    @FXML
    private void handleSubmit(ActionEvent event) throws IOException {

        String encodedString1 = null, encodedString2 = null;
        try {
            List<File> element1 = listView.getItems();
            List<File> element2 = listView1.getItems();
            if(element1.size() != 0) {
                byte[] fileContent1 = FileUtils.readFileToByteArray(new File(element1.get(0).toURI()));
                encodedString1 = Base64.getEncoder().encodeToString(fileContent1);
            }
            if(element2.size() != 0) {
                byte[] fileContent2 = FileUtils.readFileToByteArray(new File(element2.get(0).toURI()));
                encodedString2 = Base64.getEncoder().encodeToString(fileContent2);
            }
            AddJSON.addBook(title.getText(), author.getText(), genre.getText(), details.getText(), encodedString1, encodedString2);
            Stage stage = (Stage) submit.getScene().getWindow();
            stage.close();
        } catch (Manager.exceptions.EmptyField e) {
            bookAlreadyExist.setText(null);
            empty.setText(e.getMessage());
        } catch (Manager.exceptions.BookAlreadyExistsException e) {
            empty.setText(null);
            bookAlreadyExist.setText(e.getMessage());
        }

    }
}


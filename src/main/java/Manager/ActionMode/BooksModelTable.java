package Manager.ActionMode;
import java.io.File;

public class BooksModelTable {

    private javafx.scene.image.ImageView picture;
    private String information;
    private javafx.scene.image.ImageView pdf;

    public BooksModelTable(javafx.scene.image.ImageView image, javafx.scene.image.ImageView openBook, Book book) {

        picture = image;
        information = book.getTitle() + '\n' + book.getAuthor() + '\n' + book.getGenre() + '\n' + book.getDetails();
        pdf = openBook;
    }

    public javafx.scene.image.ImageView getPicture() {
        return picture;
    }

    public void setPicture(javafx.scene.image.ImageView picture) {
        this.picture = picture;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public javafx.scene.image.ImageView getPdf() {
        return pdf;
    }

    public void setPdf(javafx.scene.image.ImageView pdf) {
        this.pdf = pdf;
    }
}

package Manager.ActionMode;
import javafx.scene.control.Button;

public class BooksModelTable {

    private javafx.scene.image.ImageView picture;
    private String information;
    private Button pdf;

    public BooksModelTable(javafx.scene.image.ImageView image, Button openBook, Book book) {

        picture = image;
        information = book.getTitle() + '\n' + book.getAuthor() + '\n' + book.getGenre() + '\n' + book.getDetails();
        pdf = openBook;
    }

    public BooksModelTable(javafx.scene.image.ImageView image, Book book) {

        picture = image;
        information = book.getTitle() + '\n' + book.getAuthor() + '\n' + book.getGenre() + '\n' + book.getDetails();
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

    public Button getPdf() {
        return pdf;
    }

    public void setPdf(Button pdf) {
        this.pdf = pdf;
    }
}
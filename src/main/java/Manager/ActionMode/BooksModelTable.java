package Manager.ActionMode;
import javafx.scene.control.Button;

public class BooksModelTable {

    private javafx.scene.image.ImageView picture;
    private String information;
    private Button pdf;
    private Button editBook;
    private Button deleteBook;

    public BooksModelTable(javafx.scene.image.ImageView image, Book book, Button editBook, Button deleteBook, Button openBook) {

        picture = image;
        information = book.getTitle() + '\n' + book.getAuthor() + '\n' + book.getGenre() + '\n' + book.getDetails();
        pdf = openBook;
        this.editBook = editBook;
        this.deleteBook = deleteBook;
    }

    public BooksModelTable(javafx.scene.image.ImageView image, Book book, Button editBook, Button deleteBook) {

        picture = image;
        information = book.getTitle() + '\n' + book.getAuthor() + '\n' + book.getGenre() + '\n' + book.getDetails();
        this.editBook = editBook;
        this.deleteBook = deleteBook;
    }

    public Button getEditBook() {
        return editBook;
    }

    public void setEditBook(Button editBook) {
        this.editBook = editBook;
    }

    public Button getDeleteBook() {
        return deleteBook;
    }

    public void setDeleteBook(Button deleteBook) {
        this.deleteBook = deleteBook;
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
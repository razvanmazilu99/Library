package Client.ActionMode;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;

public class BooksTable {

    private javafx.scene.image.ImageView bookCover;
    private Hyperlink title;
    private String author;
    private String genre;
    private Button openBook;

    public BooksTable(ImageView bookCover, Hyperlink title, String author, String genre, Button openBook) {
        this.bookCover = bookCover;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.openBook = openBook;
    }

    public BooksTable(ImageView bookCover, Hyperlink title, String author, String genre) {
        this.bookCover = bookCover;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public ImageView getBookCover() {
        return bookCover;
    }

    public void setBookCover(ImageView bookCover) {
        this.bookCover = bookCover;
    }

    public Hyperlink getTitle() {
        return title;
    }

    public void setTitle(Hyperlink title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Button getOpenBook() {
        return openBook;
    }

    public void setOpenBook(Button openBook) {
        this.openBook = openBook;
    }
}

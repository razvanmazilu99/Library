package Manager.ActionMode;

import Login.controllers.ControllerLogin;
import java.util.Objects;

public class Book {

    private String title;
    private String author;
    private String genre;
    private String details;
    private String image;
    private String pdf;
    private String user;
    private int noViews;

    public Book() {

    }

    public Book(String title, String author, String genre, String details, String image, String pdf) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.details = details;
        this.image = image;
        this.pdf = pdf;
        this.user = ControllerLogin.saveUser;
        this.noViews = 0;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public int getNoViews() {
        return noViews;
    }

    public void setNoViews(int noViews) {
        this.noViews = noViews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return noViews == book.noViews &&
                title.equals(book.title) &&
                author.equals(book.author) &&
                genre.equals(book.genre) &&
                details.equals(book.details) &&
                image.equals(book.image) &&
                user.equals(book.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, genre, details, image, pdf, user, noViews);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", details='" + details + '\'' +
                ", image='" + image + '\'' +
                ", pdf='" + pdf + '\'' +
                ", user='" + user + '\'' +
                ", noViews=" + noViews +
                '}';
    }
}


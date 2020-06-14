package Client.ActionMode;

import Manager.ActionMode.Book;
import Register.actionMode.User;
import java.util.Objects;
import static Client.Controllers.ControllerClient.libraryNameSave;

public class Request {

    private String name_user;
    private String surname_user;
    private String address_user;
    private String username_user;
    private String title_book;
    private String author_book;
    private String libraryName_book;
    private String pickUpDate;
    private String returnDate;
    private String declineMessage;
    private int status;

    public Request() {
    }

    public Request(User u, Book b) {
        this.name_user = u.getName();
        this.surname_user = u.getSurname();
        this.address_user = u.getAddress();
        this.username_user = u.getUsername();
        this.title_book = b.getTitle();
        this.author_book = b.getAuthor();
        this.libraryName_book = libraryNameSave;
        this.status = 0;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getSurname_user() {
        return surname_user;
    }

    public void setSurname_user(String surname_user) {
        this.surname_user = surname_user;
    }

    public String getAddress_user() {
        return address_user;
    }

    public void setAddress_user(String address_user) {
        this.address_user = address_user;
    }

    public String getUsername_user() {
        return username_user;
    }

    public void setUsername_user(String username_user) {
        this.username_user = username_user;
    }

    public String getTitle_book() {
        return title_book;
    }

    public void setTitle_book(String title_book) {
        this.title_book = title_book;
    }

    public String getAuthor_book() {
        return author_book;
    }

    public void setAuthor_book(String author_book) {
        this.author_book = author_book;
    }

    public String getLibraryName_book() {
        return libraryName_book;
    }

    public void setLibraryName_book(String libraryName_book) {
        this.libraryName_book = libraryName_book;
    }

    public String getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(String pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getDeclineMessage() {
        return declineMessage;
    }

    public void setDeclineMessage(String declineMessage) {
        this.declineMessage = declineMessage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return status == request.status &&
                name_user.equals(request.name_user) &&
                surname_user.equals(request.surname_user) &&
                address_user.equals(request.address_user) &&
                username_user.equals(request.username_user) &&
                title_book.equals(request.title_book) &&
                author_book.equals(request.author_book) &&
                libraryName_book.equals(request.libraryName_book) &&
                pickUpDate.equals(request.pickUpDate) &&
                returnDate.equals(request.returnDate) &&
                declineMessage.equals(request.declineMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name_user, surname_user, address_user, username_user, title_book, author_book, libraryName_book, pickUpDate, returnDate, declineMessage, status);
    }

    @Override
    public String toString() {
        return "Request{" +
                "name_user='" + name_user + '\'' +
                ", surname_user='" + surname_user + '\'' +
                ", address_user='" + address_user + '\'' +
                ", username_user='" + username_user + '\'' +
                ", title_book='" + title_book + '\'' +
                ", author_book='" + author_book + '\'' +
                ", libraryName_book='" + libraryName_book + '\'' +
                ", pickUpDate=" + pickUpDate +
                ", returnDate=" + returnDate +
                ", declineMessage='" + declineMessage + '\'' +
                ", status=" + status +
                '}';
    }
}

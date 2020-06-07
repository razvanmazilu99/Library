package Manager.ActionMode;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class RequestsT {

    private javafx.scene.image.ImageView picture;
    private String information;
    private Button accept;
    private Button decline;

    public RequestsT(ImageView picture, String information, Button accept, Button decline) {
        this.picture = picture;
        this.information = information;
        this.accept = accept;
        this.decline = decline;
    }

    public ImageView getPicture() {
        return picture;
    }

    public void setPicture(ImageView picture) {
        this.picture = picture;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Button getAccept() {
        return accept;
    }

    public void setAccept(Button accept) {
        this.accept = accept;
    }

    public Button getDecline() {
        return decline;
    }

    public void setDecline(Button decline) {
        this.decline = decline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestsT requestsT = (RequestsT) o;
        return picture.equals(requestsT.picture) &&
                information.equals(requestsT.information) &&
                accept.equals(requestsT.accept) &&
                decline.equals(requestsT.decline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(picture, information, accept, decline);
    }

    @Override
    public String toString() {
        return "RequestsT{" +
                "picture=" + picture +
                ", information='" + information + '\'' +
                ", accept=" + accept +
                ", decline=" + decline +
                '}';
    }
}

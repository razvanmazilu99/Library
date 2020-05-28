package Client.ActionMode;

import javafx.scene.control.Hyperlink;

public class LibrariesTable {

    private javafx.scene.image.ImageView image;
    private Hyperlink libraryName;

    public LibrariesTable(javafx.scene.image.ImageView image, Hyperlink hyp) {
        this.image = image;
        libraryName = hyp;
    }

    public javafx.scene.image.ImageView getImage() {
        return image;
    }

    public void setImage(javafx.scene.image.ImageView image) {
        this.image = image;
    }

    public Hyperlink getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(Hyperlink libraryName) {
        this.libraryName = libraryName;
    }

}


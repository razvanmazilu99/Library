package Client.ActionMode;

import java.util.Objects;

public class AllBooksTable {

    private String information;
    private String library;

    public AllBooksTable(String information, String library) {
        this.information = information;
        this.library = library;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllBooksTable that = (AllBooksTable) o;
        return information.equals(that.information) &&
                library.equals(that.library);
    }

    @Override
    public int hashCode() {
        return Objects.hash(information, library);
    }

    @Override
    public String toString() {
        return "AllBooksTable{" +
                "information='" + information + '\'' +
                ", library='" + library + '\'' +
                '}';
    }
}

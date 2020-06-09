package Manager.ActionMode;

import java.util.Objects;

public class  ViewsTable {

    private String information;
    private int noOfViews;

    public ViewsTable(String information, int noOfViews) {
        this.information = information;
        this.noOfViews = noOfViews;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getNoOfViews() {
        return noOfViews;
    }

    public void setNoOfViews(int noOfViews) {
        this.noOfViews = noOfViews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViewsTable)) return false;
        ViewsTable that = (ViewsTable) o;
        return noOfViews == that.noOfViews &&
                information.equals(that.information);
    }

    @Override
    public int hashCode() {
        return Objects.hash(information, noOfViews);
    }

    @Override
    public String toString() {
        return "ViewsTable{" +
                "information='" + information + '\'' +
                ", noOfViews=" + noOfViews +
                '}';
    }
}
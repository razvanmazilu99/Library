package Client.ActionMode;

import java.util.Objects;

public class RequestTable {

    private String information;
    private String status;

    public RequestTable(String information, String status) {
        this.information = information;
        this.status = status;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestTable that = (RequestTable) o;
        return information.equals(that.information) &&
                status.equals(that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(information, status);
    }

    @Override
    public String toString() {
        return "RequestTable{" +
                "information='" + information + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

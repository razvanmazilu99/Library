package ManagerTest;

import Manager.ActionMode.ViewsTable;
import Manager.Controllers.ControllerViews;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;
import static org.junit.Assert.*;

public class ControllerViewsTest {

    @Test
    public void sorting() {
        ObservableList<ViewsTable> testList = FXCollections.observableArrayList();
        ObservableList<ViewsTable> testList1 = FXCollections.observableArrayList();
        testList.add(new ViewsTable("information1", 2));
        testList.add(new ViewsTable("information2", 5));
        testList.add(new ViewsTable("information3", 3));
        testList1.add(new ViewsTable("information1", 2));
        testList1.add(new ViewsTable("information2", 5));
        testList1.add(new ViewsTable("information3", 3));
        ControllerViews cab = new ControllerViews();
        cab.sorting(testList1);
        assertNotNull(testList1);
        assertNotEquals(testList.get(0).getNoOfViews(), testList1.get(0).getNoOfViews());
        assertNotEquals(testList.get(1).getNoOfViews(), testList1.get(1).getNoOfViews());
        assertNotEquals(testList.get(2).getNoOfViews(), testList1.get(2).getNoOfViews());
    }
}
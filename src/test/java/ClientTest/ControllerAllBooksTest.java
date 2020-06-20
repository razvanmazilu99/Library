package ClientTest;

import Client.Controllers.ControllerAllBooks;
import Manager.ActionMode.Book;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class ControllerAllBooksTest {

    @Test
    public void sorting() {
        List<Book> testList = new ArrayList<>();
        List<Book> testList1 = new ArrayList<>();
        testList.add(new Book("testtitle", "testauthor", "testgenre", "testdetails", "testimage", "testpdf"));
        testList.add(new Book("testtitle1", "testauthor1", "testgenre1", "testdetails1", "testimage1", "testpdf1"));
        testList.get(1).setNoViews(3);
        testList.add(new Book("testtitle2", "testauthor2", "testgenre2", "testdetails2", "testimage2", "testpdf2"));
        testList.get(2).setNoViews(1);
        testList1.add(new Book("testtitle", "testauthor", "testgenre", "testdetails", "testimage", "testpdf"));
        testList1.add(new Book("testtitle1", "testauthor1", "testgenre1", "testdetails1", "testimage1", "testpdf1"));
        testList1.get(1).setNoViews(3);
        testList1.add(new Book("testtitle2", "testauthor2", "testgenre2", "testdetails2", "testimage2", "testpdf2"));
        testList1.get(2).setNoViews(1);
        ControllerAllBooks cab = new ControllerAllBooks();
        cab.sorting(testList1);
        assertNotNull(testList1);
        assertNotEquals(testList.get(0).getNoViews(), testList1.get(0).getNoViews());
        assertNotEquals(testList.get(1).getNoViews(), testList1.get(1).getNoViews());
        assertNotEquals(testList.get(2).getNoViews(), testList1.get(2).getNoViews());
    }
}
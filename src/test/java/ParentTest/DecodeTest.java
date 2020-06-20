package ParentTest;

import Manager.Services.AddJSON;
import ParentCode.Decode;
import ParentCode.Exceptions.AlreadyExistsException;
import ParentCode.Exceptions.EmptyField;
import Register.services.FileSystemService;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static Manager.Services.AddJSON.books;
import static org.junit.Assert.*;

public class DecodeTest {

    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
    }

    @Test
    public void testdecodePicture() throws EmptyField, AlreadyExistsException, IOException {
        Decode d = new Decode();
        AddJSON.loadBooksFromFile();
        AddJSON.addBook("titletest", "authortest", "genretest", "detailstest", "Bt4WZkI0d1vJBquPE13ust0ZUZ0JSxS72K5rM3i0ucqPEUqG9o1IyiRiAeJsPMqBhwdQy4dzASqU2fFita1SMu8SX51/Ul+4UucqnuhR/SW4N5ffb3V8Q1xVz/pt63qR51/Ul+4UucqNGj", "pdftest");
        assertNotNull(books);
        assertNotEquals("Bt4WZkI0d1vJBquPE13ust0ZUZ0JSxS72K5rM3i0ucqPEUqG9o1IyiRiAeJsPMqBhwdQy4dzASqU2fFita1SMu8SX51/Ul+4UucqnuhR/SW4N5ffb3V8Q1xVz/pt63qR51/Ul+4UucqNGj", d.DecodePicture(books.get(0)));
    }

    @Test
    public void testdecodePdf() throws IOException, EmptyField, AlreadyExistsException {
        Decode d = new Decode();
        AddJSON.loadBooksFromFile();
        AddJSON.addBook("titletest", "authortest", "genretest", "detailstest", "imagetest", "zyBGXqqaepdE56WfTYjH8mlEJMDlLHbhKfoQXja1HSz8xJ4ipZ+NmuKn8FTsA48hU+EJ7kanhSYLPPwXrPchidvTXBaBk91pBKaWRTfGJBS8CR0NK10UIBE+XEFdxV0Sacus+FJ1RPOo+ykIiR/OnN0dFefwKSlu9SJ6wn");
        assertNotNull(books);
        URL url = new File("src/main/resources/Manager/PdfError.fxml").toURI().toURL();
        d.DecodePdf(books.get(0), url);
        assertNotEquals("zyBGXqqaepdE56WfTYjH8mlEJMDlLHbhKfoQXja1HSz8xJ4ipZ+NmuKn8FTsA48hU+EJ7kanhSYLPPwXrPchidvTXBaBk91pBKaWRTfGJBS8CR0NK10UIBE+XEFdxV0Sacus+FJ1RPOo+ykIiR/OnN0dFefwKSlu9SJ6wn", d.testPdf);
    }
}
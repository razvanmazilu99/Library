package ParentCode;

import Manager.ActionMode.Book;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Base64;


public class Decode {

    public WritableImage DecodePicture(Book b) {

        byte[] decodedBytes = Base64.getDecoder().decode(b.getImage().getBytes());
        ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
        BufferedImage img = null;
        try {
            img = ImageIO.read(bis);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        WritableImage image = null;
        if (img != null) {
            image = new WritableImage(img.getWidth(), img.getHeight());
            PixelWriter pw = image.getPixelWriter();
            for (int x = 0; x < img.getWidth(); x++) {
                for (int y = 0; y < img.getHeight(); y++) {
                    pw.setArgb(x, y, img.getRGB(x, y));
                }
            }
        }
        return image;
    }

    public static byte[] testPdf;

    public void DecodePdf(Book b, URL url) throws IOException {

        Desktop desktop = Desktop.getDesktop();
        boolean isFileUnlocked = false;
        try {
            org.apache.commons.io.FileUtils.touch(new File("ReadOnline" + ".pdf"));
            isFileUnlocked = true;
            byte[] pdf1 = Base64.getDecoder().decode(b.getPdf().getBytes());
            testPdf = pdf1;
            OutputStream fstream = new FileOutputStream("ReadOnline" + ".pdf");
            for (Byte p : pdf1) {
                fstream.write(p);
            }
            fstream.close();
            desktop.open(new File("ReadOnline" + ".pdf"));
        } catch (IOException e) {
            Parent home = FXMLLoader.load(url);
            Scene scene = new Scene(home);
            Stage stage1 = new Stage();
            stage1.initStyle(StageStyle.UNDECORATED);
            stage1.setScene(scene);
            stage1.show();
            isFileUnlocked = false;
        }
    }
}

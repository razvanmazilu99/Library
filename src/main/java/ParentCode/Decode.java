package ParentCode;

import Manager.ActionMode.Book;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
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

    public void DecodePdf(Book b) {

        Desktop desktop = Desktop.getDesktop();
        try {
            byte[] pdf1 = Base64.getDecoder().decode(b.getPdf().getBytes());
            OutputStream fstream = new FileOutputStream("ReadOnline" + ".pdf");
            for (Byte p : pdf1) {
                fstream.write(p);
            }
            fstream.close();
            desktop.open(new File("ReadOnline" + ".pdf"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

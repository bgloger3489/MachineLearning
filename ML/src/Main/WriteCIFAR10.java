package Main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WriteCIFAR10 {

    public static void main(String[] args) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File("./image.jpg"));
        assert bufferedImage.getHeight() == 32;
        assert bufferedImage.getWidth() == 32;
        assert bufferedImage.getColorModel().getNumComponents() == 3;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write((byte) 0);
        for (int i = 0; i < 3; i++) {
            for (int row = 0; row < bufferedImage.getHeight(); row++) {
                for (int col = 0; col < bufferedImage.getWidth(); col++) {
                    Color color = new Color(bufferedImage.getRGB(col, row));
                    switch (i) {
                    case 0:
                        outputStream.write(color.getRed());
                        break;
                    case 1:
                        outputStream.write(color.getGreen());
                        break;
                    case 2:
                        outputStream.write(color.getBlue());
                        break;
                    default:
                        break;
                    }
                }
            }
        }
        assert outputStream.size() == 3073;
        outputStream.writeTo(new FileOutputStream("./out.bin"));
    }
}


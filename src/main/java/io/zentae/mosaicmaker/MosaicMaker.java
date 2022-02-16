package io.zentae.mosaicmaker;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

public class MosaicMaker {

    public MosaicMaker() throws IOException {
        // Get the image
        String imagePath = getClass().getResource("/11547842.jpg").getPath();
        BufferedImage image = ImageIO.read(new File(imagePath));

        ImageFilter filter = new GrayFilter(true, 50);
        ImageProducer producer = new FilteredImageSource(image.getSource(), filter);
        Image image1 = Toolkit.getDefaultToolkit().createImage(producer);
        ImageIO.write((BufferedImage)image1, "jpg", new File(System.getProperty("user.dir")));

        // Gets the exact color.
        //Color color = new Color(image.getRGB(0, 0));
        // Returns all the RGB color from a specific area.
        // I could do the median with this.
        // Returns the value in binary format.
        //int[] colors = image.getRGB(0, 0, 10, 20, null, 0, 0);

        // Do some drawing ?
        /*Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setStroke(new BasicStroke(3));
        graphics.setColor(Color.BLUE);
        graphics.drawRect(10,10, image.getWidth() - 20,
            image.getHeight() - 20);*/
    }

    public static void main(String[] args) throws IOException {
        new MosaicMaker();
    }
}

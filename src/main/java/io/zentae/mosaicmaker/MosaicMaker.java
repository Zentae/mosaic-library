package io.zentae.mosaicmaker;

import io.zentae.mosaicmaker.services.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MosaicMaker {

    private final Logger logger = LoggerFactory.getLogger("Mosaic-Maker");
    private final List<BufferedImage> images = new ArrayList<>();

    // input:
    // -> Images directory
    // -> Source image
    // -> Size in pixels

    // Actions to be performed
    // -> Appliquer le filtre gris, les resize et les stocker (vignettes)
    // -> Redimensionner l'image source (pour que largeur et hauteur = multiple de p)
    //


    public MosaicMaker() throws IOException {
        // Get the image
        /*String imagePath = getClass().getResource("/11547842.jpg").getPath();
        File output = new File(System.getProperty("user.dir") + "/salam.jpg");
        output.createNewFile();

        ImageIO.write(toGreyScale(new File(imagePath)), "JPEG", output); */
        String imagesDir = "/src/main/resources/database";

        int height = 20;
        int width = 20;

        ContentService.get().getFilesInDirectory(imagesDir, files ->
            files.forEach(file ->
                images.add(resizeImage(toGreyScale(file), width, height))));

        for(int i = 0; i < images.size(); i++) {
            try {
                File file = new File(System.getProperty("user.dir") + "/" + i + ".jpg");
                file.createNewFile();

                ImageIO.write(images.get(i), "JPEG", file);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }


    /*
     * Where bi is your image, (x0,y0) is your upper left coordinate, and (w,h)
     * are your width and height respectively
     */
    public Color averageColor(BufferedImage bi, int x0, int y0, int w, int h) {
        int x1 = x0 + w;
        int y1 = y0 + h;
        long sumr = 0, sumg = 0, sumb = 0;
        int num = w * h;

        for (int x = x0; x < x1; x++) {
            for (int y = y0; y < y1; y++) {
                Color pixel = new Color(bi.getRGB(x, y));
                sumr += pixel.getRed();
                sumg += pixel.getGreen();
                sumb += pixel.getBlue();
            }
        }

        return new Color(sumr / num, sumg / num, sumb / num);
    }

    /**
     * Converts a given Image into a BufferedImage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    public static void main(String[] args) {
        try {
            new MosaicMaker();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public Logger getLogger() {
        return logger;
    }
}

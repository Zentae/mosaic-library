package io.zentae.mosaicmaker;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MosaicMaker {

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

    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);

        return outputImage;
    }

    public void getGrayScale(BufferedImage image, int startX, int startY, int endX, int endY) {
        for(int y = startY; y < endY; y++) {
            for(int x = startX; x < endX; x++) {
                Color color = new Color(image.getRGB(x, y));
                color.getR
            }
        }
    }

    /**
     * Applies a grayscale filter on a given image.
     * @param input the source image.
     */
    public BufferedImage toGreyScale(File input) {
        try {
            ImageInputStream stream = ImageIO.createImageInputStream(input);
            BufferedImage image = ImageIO.read(stream);

            int width = image.getWidth();
            int height = image.getHeight();

            for(int y = 0; y < height; y++) {
                for(int x = 0; x < width; x++) {
                    Color color = new Color(image.getRGB(x, y));

                    int red = (int)(color.getRed() * .2126);
                    int green = (int)(color.getRed() * .7152);
                    int blue = (int)(color.getRed() * .0722);
                    int sum = red + green + blue;

                    Color grayShade = new Color(sum, sum, sum);
                    image.setRGB(x, y, grayShade.getRGB());
                }
            }

            return image;
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return null;
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
}

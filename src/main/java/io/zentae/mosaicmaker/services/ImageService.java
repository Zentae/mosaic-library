package io.zentae.mosaicmaker.services;

import io.zentae.mosaicmaker.services.manager.Service;
import io.zentae.mosaicmaker.services.manager.ServicesManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageService extends Service {

    public ImageService(ServicesManager servicesManager) {
        super(servicesManager);
    }

    /**
     * Processes the given {@link BufferedImage image}.
     * @param thumbnail the {@link BufferedImage image} to process.
     * @param width the target width of the {@link BufferedImage image}.
     * @param height the target height of the {@link BufferedImage image}.
     * @return the processed {@link BufferedImage image}.
     */
    public BufferedImage processImage(BufferedImage thumbnail, int width, int height) {
        // Apply grey shade on the thumbnail.
        thumbnail = toGreyScale(thumbnail);
        // Resize the thumbnail.
        thumbnail = resizeImage(thumbnail, width, height);
        // Returns the thumbnail.
        return thumbnail;
    }

    /**
     * Return the average {@link Color} in the specified area of the {@link BufferedImage}.
     * @param bi the {@link BufferedImage}.
     * @param x0 the x upper left coordinate.
     * @param y0 the y upper left coordinate.
     * @param w the width.
     * @param h the height.
     * @return the average {@link Color}.
     */
    public Color getAverageColor(BufferedImage bi, int x0, int y0, int w, int h) {
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

        return new Color((int)sumr / num, (int)sumg / num, (int)sumb / num);
    }

    /**
     * Resizes a given image into a target size.
     * @param originalImage the source {@link BufferedImage Image}.
     * @param targetWidth the target width.
     * @param targetHeight the target height.
     * @return the resized {@link BufferedImage Image}.
     */
    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);

        return outputImage;
    }

    /**
     * Applies a grayscale filter on a given image.
     * @param image the source image.
     */
    public BufferedImage toGreyScale(BufferedImage image) {
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
    }
}

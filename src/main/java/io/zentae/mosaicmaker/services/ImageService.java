package io.zentae.mosaicmaker.services;

import io.zentae.mosaicmaker.services.manager.Service;
import io.zentae.mosaicmaker.services.manager.ServicesManager;
import org.slf4j.Logger;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageService extends Service {

    private final Logger logger;

    public ImageService(ServicesManager servicesManager) {
        super(servicesManager);
        this.logger = servicesManager.getLogger();
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
            logger.error("Failed to apply grey scale: " + exception.getMessage());
        }

        return null;
    }
}

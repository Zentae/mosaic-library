package io.zentae.mosaic.drawers;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Drawer {

    private Image image;

    public Drawer(Image image) {
        this.image = image;
    }

    /**
     * Resizes the {@link Image} into a target size.
     * @param width the target width.
     * @param height the target height.
     */
    protected void resize(int width, int height) {
        this.image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        //this.image = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        //this.image.getGraphics().drawImage(this.image, 0, 0, null);
    }

    /**
     * Resize the {@link Image} into a target {@link Dimension}.
     * @param dimension the target {@link Dimension}.
     */
    protected void resize(Dimension dimension) {
        resize((int)dimension.getWidth(), (int)dimension.getHeight());
    }

    /**
     * Crop the {@link Image} into a target size.
     * @param height the target height.
     * @param width the target width
     */
    protected void crop(int height, int width) {
        BufferedImage croppedImage = ((BufferedImage)image).getSubimage(0, 0, 0, 0);
        BufferedImage copy = new BufferedImage(croppedImage.getWidth(), croppedImage.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics g = copy.createGraphics();
        g.drawImage(croppedImage, 0, 0, null);
        g.dispose();
    }

    /**
     * Crop the {@link Image} into a target {@link Dimension}.
     * @param dimension tne target {@link Dimension}.
     */
    protected void crop(Dimension dimension) {
        crop((int)dimension.getHeight(), (int)dimension.getWidth());
    }

    /**
     * Applies a grayscale filter on a given image.
     */
    protected void toGreyScale() {
        BufferedImage image = (BufferedImage) this.image;
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

        this.image = image;
    }

    /**
     * @return the {@link Image}
     */
    protected Image getImage() {
        return image;
    }

    /**
     * Draws and return the {@link Image}
     * @return the drawn {@link Image}
     */
    public abstract Image draw();
}

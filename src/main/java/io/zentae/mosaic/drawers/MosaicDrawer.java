package io.zentae.mosaic.drawers;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MosaicDrawer extends Drawer {

    private final Image[][] images;
    private final Dimension mosaicDimension;
    private final int thumbnailSize;

    public MosaicDrawer(Image image, Image[][] images, Dimension mosaicDimension) {
        super(image);
        this.images = images;
        this.mosaicDimension = mosaicDimension;
        this.thumbnailSize = images[0][0].getHeight(null);
    }

    @Override
    public Image draw() {
        BufferedImage image = (BufferedImage)getImage();
        // Resize the picture
        resize(mosaicDimension);
        // Draw the image on to the buffered image
        Graphics2D bGr = image.createGraphics();
        // Go through the image.
        for(int x = 0; x < image.getWidth(); x += thumbnailSize) {
            for(int y = 0; y < image.getHeight(); y += thumbnailSize) {
                // Draw the thumbnail.
                bGr.drawImage(images[x][y], x, y, null);
            }
        }
        // Stop drawing.
        bGr.dispose();
        // Return the mosaic.
        return image;
    }
}

package io.zentae.mosaicmaker;

import io.zentae.mosaicmaker.exceptions.ImageReadException;
import io.zentae.mosaicmaker.services.ImageService;
import io.zentae.mosaicmaker.thumbnails.Thumbnail;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Mosaic {

    private final BufferedImage bufferedImage;
    private final ImageService imageService;
    private final MosaicMaker mosaicMaker;

    private final int thumbnailSize;

    public Mosaic(BufferedImage bufferedImage, int thumbnailSize, MosaicMaker mosaicMaker) {
        this.bufferedImage = bufferedImage;
        this.imageService = mosaicMaker.getServicesManager().getService(ImageService.class).get();
        this.mosaicMaker = mosaicMaker;
        this.thumbnailSize = thumbnailSize;
    }

    /**
     * Draw the mosaic.
     * @return the freshly drawn {@link BufferedImage Mosaic}.
     */
    public BufferedImage drawMosaic() throws ImageReadException {
        // Draw the image on to the buffered image
        Graphics2D bGr = bufferedImage.createGraphics();
        // Go through the image.
        for(int x = 0; x < bufferedImage.getWidth(); x += thumbnailSize) {
            for(int y = 0; y < bufferedImage.getHeight(); y += thumbnailSize) {
                // Get the grey shade on a specific area.
                Color greyShade = imageService.getAverageColor(bufferedImage, x, y, thumbnailSize, thumbnailSize);
                // Get the closest thumbnail to this shade of grey.
                Thumbnail thumbnail = mosaicMaker.getThumbnailRepository().getThumbnail(greyShade);
                // Draw the thumbnail.
                bGr.drawImage(thumbnail.getImage(), x, y, null);
            }
        }
        // Stop drawing.
        bGr.dispose();
        // Return the mosaic.
        return bufferedImage;
    }
}

package io.zentae.mosaic.thumbnails;

import io.zentae.mosaic.exception.ImageReadException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Lightweight thumbnail. Lower memory usage, but higher processor usage.
 */
public class LightThumbnail extends Thumbnail {

    private final String thumbnailPath;

    public LightThumbnail(String thumbnailPath, Color grayShade) {
        super(grayShade);
        this.thumbnailPath = thumbnailPath;
    }

    /**
     * <p>Retrieves the image from its path.</p>
     * <p>Good for heavy {@link Thumbnail Thumbnails}.</p>
     * @return the result of the fetch.
     */
    @Override
    public BufferedImage getImage() throws ImageReadException {
        // Get thumbnail's file.
        File thumbnailFile = new File(thumbnailPath);
        // Check if the file exists.
        if(thumbnailFile.exists()) {
            // Returns the image
            try {
                return ImageIO.read(thumbnailFile);
            } catch (IOException e) {
                throw new ImageReadException("Unable to read the thumbnail: "  + thumbnailFile.getName());
            }
        }

        return null;
    }
}

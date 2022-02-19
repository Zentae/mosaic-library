package io.zentae.mosaicmaker.thumbnails;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

/**
 * Lightweight thumbnail. Low memory usage, but higher processor usage.
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
    public BufferedImage getThumbnail() {
        // Get thumbnail's file.
        File thumbnailFile = new File(thumbnailPath);
        // Check if the file exists.
        if(thumbnailFile.exists()) {
            try {
                // Returns the image
                return ImageIO.read(thumbnailFile);
            } catch (IOException ignored) {}
        }

        return null;
    }
}

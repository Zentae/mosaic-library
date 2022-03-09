package io.zentae.mosaic.thumbnails;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Basic thumbnail. Higher memory usage, but lower processor usage.
 */
public class BaseThumbnail extends Thumbnail {

    private final BufferedImage thumbnail;

    public BaseThumbnail(BufferedImage thumbnail, Color grayShade) {
        super(grayShade);
        this.thumbnail = thumbnail;
    }

    @Override
    public BufferedImage getImage() {
        return thumbnail;
    }
}

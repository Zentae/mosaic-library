package io.zentae.mosaicmaker.thumbnails;

import java.awt.*;
import java.awt.image.BufferedImage;

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

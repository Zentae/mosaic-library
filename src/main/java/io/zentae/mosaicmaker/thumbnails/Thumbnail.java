package io.zentae.mosaicmaker.thumbnails;

import io.zentae.mosaicmaker.exceptions.ImageReadException;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Thumbnail {

    private final Color greyShade;

    public Thumbnail(Color grayShade) {
        this.greyShade = grayShade;
    }

    public Color getGreyShade() {
        return greyShade;
    }
    public abstract BufferedImage getImage() throws ImageReadException;
}

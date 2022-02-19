package io.zentae.mosaicmaker.thumbnails;

import java.awt.Color;

public abstract class Thumbnail {

    private final Color greyShade;

    public Thumbnail(Color grayShade) {
        this.greyShade = grayShade;
    }

    public Color getGreyShade() {
        return greyShade;
    }
}

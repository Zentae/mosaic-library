package io.zentae.mosaicmaker;

import java.awt.image.BufferedImage;

public record Thumbnail(BufferedImage thumbnail, int greyShade) {

    public BufferedImage getThumbnail() {
        return thumbnail;
    }

    public int getGreyShade() {
        return greyShade;
    }
}

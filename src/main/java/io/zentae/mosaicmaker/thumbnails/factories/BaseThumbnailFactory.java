package io.zentae.mosaicmaker.thumbnails.factories;

import io.zentae.mosaicmaker.thumbnails.Thumbnail;
import io.zentae.mosaicmaker.thumbnails.BaseThumbnail;

import java.awt.Color;
import java.awt.image.BufferedImage;

public record BaseThumbnailFactory(BufferedImage thumbnail,
                                   Color greyShade) implements ThumbnailAbstractFactory {

    @Override
    public Thumbnail createThumbnail() {
        return new BaseThumbnail(thumbnail, greyShade);
    }
}

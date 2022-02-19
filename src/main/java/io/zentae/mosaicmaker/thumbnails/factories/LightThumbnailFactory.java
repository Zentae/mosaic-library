package io.zentae.mosaicmaker.thumbnails.factories;

import io.zentae.mosaicmaker.thumbnails.Thumbnail;
import io.zentae.mosaicmaker.thumbnails.LightThumbnail;

import java.awt.*;

public record LightThumbnailFactory(String thumbnailPath,
                                    Color greyShade) implements ThumbnailAbstractFactory {

    @Override
    public Thumbnail createThumbnail() {
        return new LightThumbnail(thumbnailPath, greyShade);
    }
}

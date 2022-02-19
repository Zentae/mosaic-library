package io.zentae.mosaicmaker.thumbnails.factories;

import io.zentae.mosaicmaker.thumbnails.Thumbnail;

public class ThumbnailFactory {

    public static Thumbnail getThumbnail(ThumbnailAbstractFactory factory) {
        return factory.createThumbnail();
    }
}

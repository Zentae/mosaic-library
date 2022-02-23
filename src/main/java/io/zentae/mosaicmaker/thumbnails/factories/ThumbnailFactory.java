package io.zentae.mosaicmaker.thumbnails.factories;

import io.zentae.mosaicmaker.thumbnails.Thumbnail;

public class ThumbnailFactory {

    /**
     * Create a {@link Thumbnail} using a {@link ThumbnailAbstractFactory Factory}.
     * @param factory the {@link ThumbnailAbstractFactory factory} to use.
     * @return the newly created {@link Thumbnail}.
     */
    public static Thumbnail getThumbnail(ThumbnailAbstractFactory factory) {
        return factory.createThumbnail();
    }
}

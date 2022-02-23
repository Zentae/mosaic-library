package io.zentae.mosaicmaker.thumbnails.factories;

import io.zentae.mosaicmaker.thumbnails.Thumbnail;

public interface ThumbnailAbstractFactory {

    /**
     * Create a {@link Thumbnail}.
     * @return the freshly created {@link Thumbnail}.
     */
    public Thumbnail createThumbnail();
}

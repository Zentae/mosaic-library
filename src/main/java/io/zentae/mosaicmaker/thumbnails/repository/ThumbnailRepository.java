package io.zentae.mosaicmaker.thumbnails.repository;

import io.zentae.mosaicmaker.thumbnails.Thumbnail;

import java.awt.*;
import java.util.HashSet;

public class ThumbnailRepository {

    private final HashSet<Thumbnail> thumbnails = new HashSet<>();

    /**
     * Registers a {@link Thumbnail} in the cache.
     * @param thumbnail the {@link Thumbnail} to register.
     */
    public void registerThumbnail(Thumbnail thumbnail) {
        thumbnails.add(thumbnail);
    }

    /**
     * Returns the {@link Thumbnail thumbnails} stored in cache.
     * @return the {@link Thumbnail thumbnail}'s cache.
     */
    public HashSet<Thumbnail> getThumbnails() {
        return thumbnails;
    }

    /**
     * @param greyShade the grey shade.
     * @return the closest {@link Thumbnail} to the given grey shade.
     */
    public Thumbnail getThumbnail(Color greyShade) {
        Thumbnail closestThumbnail = null;
        int diff = Math.abs(thumbnails.stream().findFirst().get().getGreyShade().getRGB() - greyShade.getRGB());

        for(Thumbnail thumbnail : thumbnails) {
            int temporaryDiff = Math.abs(thumbnail.getGreyShade().getRGB() - greyShade.getRGB());
            if(temporaryDiff <= diff) {
                diff = temporaryDiff;
                closestThumbnail = thumbnail;
            }
        }

        return closestThumbnail;
    }
}

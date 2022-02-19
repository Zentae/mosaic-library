package io.zentae.mosaicmaker.thumbnails.repository;

import io.zentae.mosaicmaker.thumbnails.Thumbnail;

import java.io.File;
import java.util.HashSet;

public class ThumbnailRepository {

    private final HashSet<Thumbnail> thumbnails = new HashSet<>();

    private final File temporaryDirectory;
    private final File thumbnailsDirectory;

    public ThumbnailRepository(String thumbnailsPath) {
        this.temporaryDirectory = new File(System.getProperty("user.dir") +  "/tmp");
        this.thumbnailsDirectory = new File(System.getProperty("user.dir") + "/" + thumbnailsPath);
    }

    public File getTemporaryDirectory() {
        return temporaryDirectory;
    }

    public File getThumbnailsDirectory() {
        return thumbnailsDirectory;
    }

    public HashSet<Thumbnail> getThumbnails() {
        return thumbnails;
    }
}

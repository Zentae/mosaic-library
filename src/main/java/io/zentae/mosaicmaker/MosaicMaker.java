package io.zentae.mosaicmaker;

import io.zentae.mosaicmaker.exceptions.ImageNotFoundException;
import io.zentae.mosaicmaker.exceptions.ImageReadException;
import io.zentae.mosaicmaker.exceptions.MosaicPrintException;
import io.zentae.mosaicmaker.services.ContentService;
import io.zentae.mosaicmaker.services.ImageService;
import io.zentae.mosaicmaker.services.manager.ServicesManager;
import io.zentae.mosaicmaker.thumbnails.factories.BaseThumbnailFactory;
import io.zentae.mosaicmaker.thumbnails.factories.LightThumbnailFactory;
import io.zentae.mosaicmaker.thumbnails.factories.ThumbnailAbstractFactory;
import io.zentae.mosaicmaker.thumbnails.factories.ThumbnailFactory;
import io.zentae.mosaicmaker.thumbnails.repository.ThumbnailRepository;
import io.zentae.mosaicmaker.utils.StorageUnit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

public class MosaicMaker {

    private final ServicesManager servicesManager;
    private final ContentService contentService;
    private final ImageService imageService;
    private final ThumbnailRepository thumbnailRepository;

    public MosaicMaker() {
        // Registers services.
        servicesManager = new ServicesManager()
                .register(Stream.of(ContentService.class, ImageService.class));
        // Get the content service.
        contentService = servicesManager.getService(ContentService.class).get();
        // Get the image service.
        imageService = servicesManager.getService(ImageService.class).get();
        // Setup thumbnails repository.
        thumbnailRepository = new ThumbnailRepository();
    }

    /**
     * Process the image & thumbnails.
     * @param thumbnailsPath the path to the thumbnails.
     * @param sourceImagePath the path to the source image.
     * @param thumbnailSize the thumbnails' size.
     * @return the {@link Mosaic}.
     */
    public Mosaic process(String thumbnailsPath, String sourceImagePath, int thumbnailSize) throws ImageNotFoundException, ImageReadException, IOException {
        // Get source image's file.
        File sourceImageFile = new File(sourceImagePath);
        // Check if the source image exists.
        if(!sourceImageFile.exists())
            // Throw an exception which will be handled.
            throw new ImageNotFoundException("Unable to find the source image.");

        // Get the source image as an Image.
        BufferedImage mosaicImage;
        try {
            mosaicImage = ImageIO.read(sourceImageFile);
        } catch (IOException e) {
            throw new ImageReadException("Unable to read the source image.");
        }

        // Process the image.
        mosaicImage = imageService.processImage(mosaicImage,
                closestMultiple(mosaicImage.getWidth(), thumbnailSize),
                closestMultiple(mosaicImage.getHeight(), thumbnailSize));
        // Creating the mosaic.
        Mosaic mosaic = new Mosaic(mosaicImage, thumbnailSize, this);
        // Get the size of all the thumbnails.
        long size = contentService.getFileSize(thumbnailsPath, StorageUnit.KB);
        // Fetches the files inside the given directory.
        contentService.getFilesInDirectory(thumbnailsPath, files ->
            files.forEach(file -> {
                try {
                    // Get the file as an image.
                    BufferedImage image = ImageIO.read(file);
                    // Process the thumbnail.
                    image = imageService.processImage(image, thumbnailSize, thumbnailSize);
                    // Gets the thumbnail's grey shade.
                    Color greyShade = imageService.getAverageColor(image, 0, 0, image.getWidth(), image.getHeight());
                    // Choosing which storage type is the best based on the size of the thumbnails.
                    ThumbnailAbstractFactory factory;
                    if (size < 100) {
                        factory = new BaseThumbnailFactory(image, greyShade);
                    } else {
                        // Updates the thumbnails.
                        ImageIO.write(image, "JPEG", file);
                        factory = new LightThumbnailFactory(file.getPath(), greyShade);
                    }
                    // Registers the thumbnail in cache.
                    thumbnailRepository.registerThumbnail(ThumbnailFactory.getThumbnail(factory));
                } catch (IOException ignored) {}
        }));

        return mosaic;
    }

    /**
     * Print the {@link Mosaic} into a {@link File}.
     * @param mosaic the {@link Mosaic} to print.
     * @param outputPath the output path.
     */
    public void printMosaic(Mosaic mosaic, String outputPath) throws MosaicPrintException, ImageReadException {
        // Print the image.
        try {
            ImageIO.write(mosaic.drawMosaic(), "JPEG", new File(outputPath + "mosaic.jpg"));
        } catch (IOException e) {
            throw new MosaicPrintException("Unable to print the mosaic.");
        }
    }

    /**
     * Find the closest multiple.
     * @param n natural integer.
     * @param x unknown.
     * @return the closest multiple.
     */
    public static int closestMultiple(int n, int x) {
        if(x > n)
            return x;

        n = n + x/2;
        n = n - (n%x);

        return n;
    }


    /**
     * @return the {@link ServicesManager}.
     */
    public ServicesManager getServicesManager() {
        return servicesManager;
    }

    /**
     * @return the {@link ThumbnailRepository}.
     */
    public ThumbnailRepository getThumbnailRepository() {
        return thumbnailRepository;
    }
}

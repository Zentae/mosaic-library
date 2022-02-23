package io.zentae.mosaicmaker.services;

import io.zentae.mosaicmaker.StorageUnit;
import io.zentae.mosaicmaker.services.manager.Service;
import io.zentae.mosaicmaker.services.manager.ServicesManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class ContentService extends Service {


    public ContentService(ServicesManager servicesManager) {
        super(servicesManager);
    }

    /**
     * Fetches {@link File}'s size and converts it in the target {@link StorageUnit Unit}.
     * @param filePath the {@link File}'s path.
     * @param storageUnit the target {@link StorageUnit Unit}.
     * @return the converted size of the given {@link File}.
     */
    public long getFileSize(String filePath, StorageUnit storageUnit) {
        try {
            // Get the file's path.
            Path path = Paths.get(filePath);
            // Return converted file's size.
            return StorageUnit.B.to(storageUnit, Files.size(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Recover all the {@link File Files} inside the given directory.
     * @param path the directory's path.
     * @param filesConsumer the {@link File Files} stored inside the directory.
     * @return {@link ContentService this}
     */
    public ContentService getFilesInDirectory(String path, Consumer<Stream<File>> filesConsumer) throws IOException {
        filesConsumer.accept(Files.walk(Paths.get(path), FileVisitOption.FOLLOW_LINKS)
                .filter(Files::isRegularFile)
                .map(Path::toFile));

        return this;
    }
}

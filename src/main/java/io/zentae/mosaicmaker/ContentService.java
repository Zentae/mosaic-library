package io.zentae.mosaicmaker;

import java.io.*;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.stream.Stream;

public final class ContentService {

    private static ContentService contentService;

    private ContentService() {
        contentService = this;
    }

    public static ContentService get() {
        return contentService != null ? contentService : new ContentService();
    }

    public ContentService copyFile(InputStream inputStream, String destination) throws IOException {
        File destinationFile = new File(destination);

        if(!destinationFile.exists()) {
            try { destinationFile.createNewFile();
            } catch (IOException e) { e.printStackTrace(); }

            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            FileWriter fileWriter = new FileWriter(destination);

            while (bufferedReader.ready())
                fileWriter.append(bufferedReader.readLine()).append("\n");

            reader.close();
            bufferedReader.close();
            fileWriter.close();
        }

        return this;
    }

    public ContentService createDirectory(String destination) {
        File file = new File(destination);
        if(!file.exists())
            file.mkdir();

        return this;
    }

    public ContentService getFilesInDirectory(String path, Consumer<Stream<File>> filesConsumer) {
        path = System.getProperty("user.dir") + path;
        try {
            filesConsumer.accept(Files.walk(Paths.get(path), FileVisitOption.FOLLOW_LINKS)
                    .filter(Files::isRegularFile)
                    .map(Path::toFile));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        return this;
    }
}

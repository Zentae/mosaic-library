# mosaic-maker
Mosaic maker module made in java with love &lt;3

```java
try {
    // Define the user directory.
    String userDir = System.getProperty("user.dir") + "\\";
    // Define the thumbnails path.
    String tPath = userDir + "thumbnails\\";
    // Define the source image's path.
    String sPath = userDir + "source.jpg";
    // Starts the process.
    new MosaicMaker(tPath, sPath, userDir, 1);
} catch (ImageNotFoundException e) {
    e.printStackTrace();
} catch (MosaicPrintException e) {
    e.printStackTrace();
} catch (ImageReadException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}
```

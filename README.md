# mosaic-maker
Mosaic maker module made in java with love &lt;3

## How to use it

```java
// Define user directory.
String userDir = System.getProperty("user.dir") + "\\";
// Define thumbnails path.
String thumbnailsPath = userDir + "thumbnails\\";
// Define thumbnails' size.
int thumbnailsSize = 1;
// Define source image path.
String sourceImagePath = userDir + "sourceImage.jpg";
// Instantiate our mosaic maker.
MosaicMaker maker = new MosaicMaker();

try {
    // Creates our mosaic.
    Mosaic mosaic = maker.process(thumbnailsPath, sourceImagePath, thumbnailsSize);
    // Print our mosaic inside a file.
    maker.printMosaic(mosaic, userDir);
} catch (ImageNotFoundException e) {
    // Cannot find the source image.
    e.printStackTrace();
} catch (ImageReadException e) {
    // Failed to read the image.
    e.printStackTrace();
} catch (IOException e) {
    // Java's IOException.
    e.printStackTrace();
} catch (MosaicPrintException e) {
    // Failed to print the mosaic into the given file.
    e.printStackTrace();
}
```
# Some examples
![alt text](https://github.com/Zentae/mosaic-maker/blob/master/results/mosaic-1.jpg?raw=true)
1,47Mo
![alt text](https://github.com/Zentae/mosaic-maker/blob/master/results/mosaic-2.jpg?raw=true)
75Ko

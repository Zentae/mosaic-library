# mosaic-maker
Mosaic maker module made in java with love &lt;3

```java
try {
    String userDir = System.getProperty("user.dir") + "\\";
    String tPath = userDir + "thumbnails\\";
    String sPath = userDir + "source.jpg";
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

# mosaic-library
Lightweight library made in java with love &lt;3

## How to use it
Draw the thumbnails.
```java
int thumbnailsLength = 20;
// Thumbnails.
Image[][] thumbnails = new Image[thumbnailsLength][thumbnailsLength];
// Thumbnail's dimension.
Dimension thumbnailDimension = new Dimension(20, 20);
// Create the mosaic's drawer.
Drawer thumbnailDrawer = new ThumbnailsDrawer(image, thumbnailDimension);
// Draw the thumbnail.
Image thumbnail = thumbnailDrawer.draw();
```
Draw the mosaic.
```java
//...
Dimension mosaicDimension = new Dimension(20, 20);
// Create the mosaic's drawer.
Drawer mosaicDrawer = new MosaicDrawer(image, thumbnails, mosaicDimension);
// Draw the picture.
Image mosaic = mosaicDrawer.draw();
```
## Some examples
![alt text](https://github.com/Zentae/mosaic-maker/blob/master/results/mosaic-1.jpg?raw=true)
373ko
![alt text](https://github.com/Zentae/mosaic-maker/blob/master/results/mosaic-2.jpg?raw=true)
477ko
![alt text](https://github.com/Zentae/mosaic-maker/blob/master/results/mosaic-3.jpg?raw=true)
75Ko

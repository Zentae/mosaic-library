package io.zentae.mosaic.drawers;

import java.awt.*;

public class ThumbnailDrawer extends Drawer {

    private final Dimension dimension;

    public ThumbnailDrawer(Image image, Dimension dimension) {
        super(image);
        this.dimension = dimension;
    }

    @Override
    public Image draw() {
        // Apply grey shade on the thumbnail.
        toGreyScale();
        // Resize the thumbnail.
        resize(dimension);
        // Returns the thumbnail.
        return getImage();
    }
}

package TWI.tileImage;

import java.awt.Point;

import TWI.tile.TWITile;

public class TWIHexagonTileImage extends TWITileImage {
    // constructor
    public TWIHexagonTileImage(TWITile tile) {
        super(tile);
    }

    @Override
    public Point getRenderPosition(int i) {
        // TODO: replace the naive tiling algorithm with spiral tiling.
        int maxPerRow = 50;
        int offset = maxPerRow / 2;

        int imageHeight = this.mImage.getHeight();
        int imageWidth = this.mImage.getWidth();

        Point pt;
        if (i % 2 == 1) {
            pt = new Point(
                ((i % maxPerRow) - offset) * imageWidth * 3 / 4,
                ((i / maxPerRow) - offset) * imageHeight
            );
        } else {
            pt = new Point(
                ((i % maxPerRow) - offset) * imageWidth * 3 / 4,
                ((i / maxPerRow) - offset) * imageHeight + imageHeight / 2
            );
        }

        return pt;
    }
}

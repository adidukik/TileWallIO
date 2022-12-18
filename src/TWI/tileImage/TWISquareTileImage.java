package TWI.tileImage;

import java.awt.Point;

import TWI.tile.TWITile;

public class TWISquareTileImage extends TWITileImage {
    // constructor
    public TWISquareTileImage(TWITile tile) {
        super(tile);
    }

    @Override
    public Point getRenderPosition(int i) {
        // TODO: replace the naive tiling algorithm with spiral tiling.
        int maxPerRow = 50;
        int offset = maxPerRow / 2;

        int imageHeight = this.mImage.getHeight();
        int imageWidth = this.mImage.getWidth();

        Point pt = new Point(
            ((i % maxPerRow) - offset) * imageWidth,
            ((i / maxPerRow) - offset) * imageHeight
        );

        return pt;
    }
}

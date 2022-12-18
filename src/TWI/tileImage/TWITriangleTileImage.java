package TWI.tileImage;

import java.awt.Point;

import TWI.tile.TWITile;

public class TWITriangleTileImage extends TWITileImage {
    // constructor
    public TWITriangleTileImage(TWITile tile) {
        super(tile);
    }

    @Override
    public Point getRenderPosition(int i) {
        // TODO: replace the naive tiling algorithm with spiral tiling.
        int maxPerRow = 50;
        int offset = maxPerRow / 2;

        int imageHeight = this.mImage.getHeight();
        int imageWidth = this.mImage.getWidth();

        int row = i / maxPerRow;

        Point pt;
        if (row % 2 == 1) {
            pt = new Point(
                ((i % maxPerRow) - offset) * imageWidth / 2,
                ((i / maxPerRow) - offset) * imageHeight
            );
        } else {
            pt = new Point(
                ((i % maxPerRow) - offset + 1) * imageWidth / 2,
                ((i / maxPerRow) - offset) * imageHeight
            );
        }

        return pt;
    }

    @Override
    public boolean getRenderFlipAt(int i) {
        if (i % 2 == 1) return false;
        else return true;
    }
}

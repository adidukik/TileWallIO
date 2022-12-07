package TWI.tile;

import java.awt.Point;
import java.awt.geom.Rectangle2D;

import TWI.geom.TWIGeom;
import TWI.geom.TWIRectangle;

public class TWISquareTile extends TWITile {
    // constants
    private static final double X_DEFAULT = 100.0;
    private static final double Y_DEFAULT = 100.0;
    private static final double WIDTH_DAFAULT = 400.0;
    private static final double HEIGHT_DEFAULT = 400.0;

    // fields
    private TWIGeom mTileGeom = null;

    @Override
    public TWIGeom getTileGeom() {
        return this.mTileGeom;
    }

    private Rectangle2D.Double mBoundingBox = null;

    @Override
    public Rectangle2D.Double getBoundingBox() {
        return this.mBoundingBox;
    }

    // constructor
    public TWISquareTile() {
        super();

        Rectangle2D rect = new Rectangle2D.Double(
            TWISquareTile.X_DEFAULT,
            TWISquareTile.Y_DEFAULT,
            TWISquareTile.HEIGHT_DEFAULT,
            TWISquareTile.WIDTH_DAFAULT
        );

        this.mTileGeom = new TWIRectangle(rect);

        this.mTileGeom.setStrokeColor(TWITile.COLOR_TILE_DEFAULT);

        this.mBoundingBox = new Rectangle2D.Double(
            TWISquareTile.X_DEFAULT,
            TWISquareTile.Y_DEFAULT,
            TWISquareTile.HEIGHT_DEFAULT,
            TWISquareTile.WIDTH_DAFAULT
        );
    }

    @Override
    protected Point getTilingPosition(int index) {
        throw new UnsupportedOperationException();
    }
}

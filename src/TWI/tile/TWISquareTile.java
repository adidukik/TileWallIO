package TWI.tile;

import java.awt.geom.Rectangle2D;

import TWI.geom.TWIGeom;
import TWI.geom.TWIRectangle;

public class TWISquareTile extends TWITile {
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
    public TWISquareTile(Double w, Double h) {
        super();

        Rectangle2D rect = new Rectangle2D.Double(
            0, 0, w, h
        );

        this.mTileGeom = new TWIRectangle(rect);

        this.mTileGeom.setStrokeColor(TWITile.COLOR_TILE_DEFAULT);

        this.mBoundingBox = new Rectangle2D.Double(
            0, 0, w, h
        );
    }
}

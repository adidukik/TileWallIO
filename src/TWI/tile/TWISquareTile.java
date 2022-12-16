package TWI.tile;

import java.awt.Point;

import TWI.geom.TWIGeom;
import TWI.geom.TWIRectangle;

public class TWISquareTile extends TWITile {
    // fields
    private TWIRectangle mRect = null;

    @Override
    public TWIGeom getTileGeom() {
        return this.mRect;
    }

    // constructor
    public TWISquareTile(Double x, Double y, Double w, Double h) {
        super();

        this.mRect = new TWIRectangle(
            x, y, w, h
        );

        this.mRect.setStrokeColor(TWITile.COLOR_TILE_DEFAULT);
        this.mRect.setFillColor(TWITile.TILE_FILL_COLOR_DEFAULT);
    }

    @Override
    public boolean isMousePointInside(Point pt) {
        return this.mRect.contains(pt);
    }
}

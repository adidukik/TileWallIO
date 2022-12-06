package TWI.tile;

import java.awt.geom.Point2D;

import TWI.geom.TWIDot;

public abstract class TWITileMgr {
    // constant
    protected static final double SNAP_RADIUS = 20.0f;
    protected static final double CALCULATION_TOLERANCE = 5.0f;

    // fields
    protected boolean mIsSnapOn = false;

    public boolean getmIsSnapOn() {
        return this.mIsSnapOn;
    }

    public void setIsSnapOn(boolean b) {
        this.mIsSnapOn = b;
    }

    protected TWITile mTile = null;

    public TWITile getTile() {
        return this.mTile;
    }

    // methods
    protected boolean isDotInside(TWIDot dot) {
        Point2D pt = dot.getPoint();
        return this.mTile.getTileGeom().getShape().contains(pt);
    }

    // abstract methods
    public abstract TWIDot addDot(TWIDot dot);
    protected abstract boolean isDotOnEdge(TWIDot dot);
    protected abstract TWIDot getOppositeAnchorDot(TWIDot dot);
}

package TWI.tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import TWI.TWI;
import TWI.TWIRenderable;
import TWI.geom.TWIDot;
import TWI.geom.TWIRectangle;

public abstract class TWITileMgr implements TWIRenderable {
    // constant
    protected static final double SNAP_RADIUS = 20.0f;
    protected static final double CALCULATION_TOLERANCE = 5.0f;
    protected static final double X_DEFAULT = 100.0;
    protected static final double Y_DEFAULT = 100.0;

    protected static final Color BG_STROKE_COLOR =
        new Color(0, 0, 0, 0);
    protected static final Color BG_FILL_COLOR =
        new Color(253, 253, 150);

    // fields
    private TWI mTWI = null;

    private Point mTileOrigin = null;

    public Point getTileOrigin() {
        return this.mTileOrigin;
    }

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

    // constructor
    protected TWITileMgr(TWI twi) {
        this.mTWI = twi;

        this.mTileOrigin = new Point(
            (int) TWITileMgr.X_DEFAULT,
            (int) TWITileMgr.Y_DEFAULT
        );
    }

    // methods
    protected boolean isDotInside(TWIDot dot) {
        Point2D pt = dot.getPoint();
        return this.mTile.getTileGeom().getShape().contains(pt);
    }

    @Override
    public void render(Graphics2D g2, Point origin) {
        double bgW = this.mTWI.getCanvas2d().getWidth() / 2;
        double bgH = this.mTWI.getCanvas2d().getHeight();

        TWIRectangle bg = new TWIRectangle(
            new Rectangle2D.Double(
                0, 0, bgW, bgH
            )
        );

        bg.setStrokeColor(TWITileMgr.BG_STROKE_COLOR);
        bg.setFillColor(TWITileMgr.BG_FILL_COLOR);

        bg.render(g2, origin);

        Point tilePos = new Point(
            origin.x + mTileOrigin.x,
            origin.y + mTileOrigin.y
        );

        this.mTile.render(g2, tilePos);
    }

    // abstract methods
    public abstract TWIDot addDot(TWIDot dot);
    protected abstract boolean isDotOnEdge(TWIDot dot);
    protected abstract TWIDot getOppositeAnchorDot(TWIDot dot);
}

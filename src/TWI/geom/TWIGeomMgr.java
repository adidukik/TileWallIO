package TWI.geom;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;

import TWI.TWI;
import TWI.tile.TWITileMgr;

public class TWIGeomMgr {
    // constant
    private static final Color COLOR_CURRENT = Color.BLUE;

    // fields
    private TWI mTwi = null;

    private TWILine mCurLine = null;

    public TWILine getCurLine() {
        return this.mCurLine;
    }

    // constructor
    public TWIGeomMgr(TWI twi) {
        this.mTwi = twi;
    }

    // methods
    public boolean createLine(Point pt) {
        TWITileMgr tileMgr = this.mTwi.getTileMgr();

        TWIDot screenDot = new TWIDot(pt.x, pt.y);

        // (1) Add Dot in respect to the tile's local coordinate.
        TWIDot tileDot = this.calcScreenDotToTileDot(screenDot);

        // (2) Try to add the new dot to TileMgr.
        // Use the new dot if tileMgr changed it.
        TWIDot newTileDot = tileMgr.addDot(tileDot);

        if (newTileDot == null) return false;

        // (3) Create the point in the screen coordinate.
        Point2D newScreenPt = this.calcTileDotToScreenDot(newTileDot);

        // (4) Create a line in the screen coordinate.
        this.mCurLine = new TWILine(newScreenPt, newScreenPt);
        this.mCurLine.setStrokeColor(TWIGeomMgr.COLOR_CURRENT);

        return true;
    }

    public void updateLine(Point pt) {
        assert (this.mCurLine != null);

        this.mCurLine.setLine(this.mCurLine.getP1(), pt);
    }

    public boolean addLine() {
        assert (this.mCurLine != null);

        TWITileMgr tileMgr = this.mTwi.getTileMgr();

        // (1) Extract (screen) points from the TWILine.
        TWIDot startScreenDot = new TWIDot(
            this.mCurLine.getX1(), this.mCurLine.getY1()
        );
        TWIDot endScreenDot = new TWIDot(
            this.mCurLine.getX2(), this.mCurLine.getY2()
        );

        // (2) Add Dot in respect to the tile's local coordinate.
        TWIDot startTileDot = this.calcScreenDotToTileDot(startScreenDot);

        TWIDot endTileDot = this.calcScreenDotToTileDot(endScreenDot);

        // (3) Try to add the new dot to TileMgr.
        // Use the new dot if tileMgr changed it.
        TWIDot newEndTileDot = tileMgr.addDot(endTileDot);

        if (newEndTileDot == null) return false;

        // (4) Add TWILine respect to the tile's local coordinate.
        TWILine geomToAdd = new TWILine(startTileDot, newEndTileDot);

        tileMgr.addPattern(geomToAdd);

        // (5) Set current line to null.
        this.mCurLine = null;

        return true;
    }

    private TWIDot calcScreenDotToTileDot(TWIDot dot) {
        TWITileMgr tileMgr = this.mTwi.getTileMgr();
        int tileX = tileMgr.getTileOrigin().x;
        int tileY = tileMgr.getTileOrigin().y;

        return new TWIDot(
            dot.getX() - tileX,
            dot.getY() - tileY
        );
    }

    private TWIDot calcTileDotToScreenDot(TWIDot  pt) {
        TWITileMgr tileMgr = this.mTwi.getTileMgr();
        int tileX = tileMgr.getTileOrigin().x;
        int tileY = tileMgr.getTileOrigin().y;

        return new TWIDot(
            pt.getX() + tileX,
            pt.getY() + tileY
        );
    }
}

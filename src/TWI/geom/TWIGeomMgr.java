package TWI.geom;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;

import TWI.TWI;
import TWI.TWIAnchorDot;
import TWI.TWIAnchorDot.ClickableFlag;
import TWI.TWIAnchorDot.SnappableFlag;
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

    private TWIBezier mCurScreenBezier = null;

    public TWIBezier getCurCurve() {
        return this.mCurScreenBezier;
    }

    // constructor
    public TWIGeomMgr(TWI twi) {
        this.mTwi = twi;
    }

    // methods
    public boolean createLine(Point pt) {
        assert(this.mCurLine == null);

        TWITileMgr tileMgr = this.mTwi.getTileMgr();

        TWIDot screenDot = new TWIDot(pt.x, pt.y);

        // (1) Add Dot in respect to the tile's local coordinate.
        TWIAnchorDot tileDot = this.calcScreenDotToTileAnchorDot(
            screenDot,
            TWIAnchorDot.SnappableFlag.SNAPPABLE,
            TWIAnchorDot.ClickableFlag.NOT_CLICKABLE
        );

        // (2) Try to add the new dot to TileMgr.
        // Use the new dot if tileMgr changed it.
        TWIDot newTileDot = tileMgr.calcValidDot(tileDot);

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
        TWIAnchorDot startTileDot =
            this.calcScreenDotToTileAnchorDot(
                startScreenDot,
                TWIAnchorDot.SnappableFlag.SNAPPABLE,
                TWIAnchorDot.ClickableFlag.CLICKABLE
            );

        TWIAnchorDot endTileDot =
            this.calcScreenDotToTileAnchorDot(
                endScreenDot,
                TWIAnchorDot.SnappableFlag.SNAPPABLE,
                TWIAnchorDot.ClickableFlag.CLICKABLE
            );

        // (3) Try to add the new dot to TileMgr.
        // Use the new dot if tileMgr changed it.
        TWIDot newEndTileDot = tileMgr.calcValidDot(endTileDot);

        if (newEndTileDot == null) return false;

        // (4) Add TWILine respect to the tile's local coordinate.
        TWILine geomToAdd = new TWILine(startTileDot, newEndTileDot);

        tileMgr.addPattern(geomToAdd);

        // (5) Set current line to null.
        this.mCurLine = null;

        return true;
    }


    public boolean createBezier(Point pt) {
        assert(this.mCurScreenBezier == null);

        TWITileMgr tileMgr = this.mTwi.getTileMgr();

        TWIDot screenDot = new TWIDot(pt.x, pt.y);

        // (1) Add Dot in respect to the tile's local coordinate.
        TWIAnchorDot tileDot =
            this.calcScreenDotToTileAnchorDot(
                screenDot,
                TWIAnchorDot.SnappableFlag.SNAPPABLE,
                TWIAnchorDot.ClickableFlag.NOT_CLICKABLE
            );

        // (2) Try to add the new dot to TileMgr.
        // Use the new dot if tileMgr changed it.
        TWIDot newTileDot = tileMgr.calcValidDot(tileDot);

        if (newTileDot == null) return false;

        // (3) Create the point in the screen coordinate.
        Point2D newScreenPt = this.calcTileDotToScreenDot(newTileDot);

        // (4) Create a bezier in the screen coordinate.
        // use some dummy points for control points
        this.mCurScreenBezier = new TWIBezier(
            newScreenPt.getX(), newScreenPt.getY(),
            newScreenPt.getX(), newScreenPt.getY() + 50,
            newScreenPt.getX(), newScreenPt.getY() - 50,
            newScreenPt.getX(), newScreenPt.getY()
        );
        this.mCurScreenBezier.setStrokeColor(TWIGeomMgr.COLOR_CURRENT);

        return true;
    }

    public void updateBezier(Point pt) {
        assert (this.mCurScreenBezier != null);

        this.mCurScreenBezier.setCurve(
            mCurScreenBezier.getX1(), mCurScreenBezier.getY1(),
            mCurScreenBezier.getCtrlX1(), mCurScreenBezier.getCtrlY1(),
            pt.getX(), pt.getY() - 50,
            pt.getX(), pt.getY()
        );
    }

    public boolean addBezier() {
        assert (this.mCurScreenBezier != null);

        TWITileMgr tileMgr = this.mTwi.getTileMgr();

        // (1) Extract (screen) points from the TWIBezier.
        TWIDot startScreenDot = new TWIDot(
            this.mCurScreenBezier.getX1(), this.mCurScreenBezier.getY1()
        );
        TWIDot startCtrlScreenDot = new TWIDot(
            this.mCurScreenBezier.getCtrlX1(), this.mCurScreenBezier.getCtrlY1()
        );
        TWIDot endCtrlScreenDot = new TWIDot(
            this.mCurScreenBezier.getCtrlX2(), this.mCurScreenBezier.getCtrlY2()
        );
        TWIDot endScreenDot = new TWIDot(
            this.mCurScreenBezier.getX2(), this.mCurScreenBezier.getY2()
        );

        // (2) Add Dot in respect to the tile's local coordinate.
        TWIAnchorDot startTileDot =
        this.calcScreenDotToTileAnchorDot(
            startScreenDot,
            TWIAnchorDot.SnappableFlag.SNAPPABLE,
            TWIAnchorDot.ClickableFlag.CLICKABLE
        );

        TWIAnchorDot startCtrlTileDot =
            this.calcScreenDotToTileAnchorDot(
                startCtrlScreenDot,
                TWIAnchorDot.SnappableFlag.SNAPPABLE,
                TWIAnchorDot.ClickableFlag.NOT_CLICKABLE
            );

        TWIAnchorDot endCtrlTileDot =
            this.calcScreenDotToTileAnchorDot(
                endCtrlScreenDot,
                TWIAnchorDot.SnappableFlag.SNAPPABLE,
                TWIAnchorDot.ClickableFlag.NOT_CLICKABLE
            );

        TWIAnchorDot endTileDot =
            this.calcScreenDotToTileAnchorDot(
                endScreenDot,
                TWIAnchorDot.SnappableFlag.SNAPPABLE,
                TWIAnchorDot.ClickableFlag.CLICKABLE
            );

        // (3) Try to add the new dot to TileMgr.
        // Use the new dot if tileMgr changed it.
        TWIDot newEndTileDot = tileMgr.calcValidDot(endTileDot);

        if (newEndTileDot == null) return false;

        // (4) Add TWIBezier respect to the tile's local coordinate.
        TWIBezier geomToAdd = new TWIBezier(
            startTileDot.getX(), startTileDot.getY(),
            startCtrlTileDot.getX(), startCtrlTileDot.getY(),
            endCtrlTileDot.getX(), endCtrlTileDot.getY(),
            newEndTileDot.getX(), newEndTileDot.getY()
        );

        tileMgr.addPattern(geomToAdd);

        // (5) Set current line to null.
        this.mCurScreenBezier = null;

        return true;
    }


    private TWIAnchorDot calcScreenDotToTileAnchorDot(
        TWIDot dot,
        SnappableFlag snappableFlag,
        ClickableFlag clickableFlag
    ) {
        TWITileMgr tileMgr = this.mTwi.getTileMgr();
        int tileX = tileMgr.getTileOrigin().x;
        int tileY = tileMgr.getTileOrigin().y;

        return new TWIAnchorDot(
            dot.getX() - tileX,
            dot.getY() - tileY,
            snappableFlag,
            clickableFlag
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

package TWI.geom;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Line2D;
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

        // (1) Add Dot in respect to the tile's local coordinate.
        Point2D tilePt = this.calcScreenPointToTilePoint((Point2D) pt);
        TWIDot tileDot = new TWIDot(tilePt);

        // (2) Try to add the new dot to TileMgr.
        // Use the new dot if tileMgr changed it.
        TWIDot newTileDot = tileMgr.addDot(tileDot);

        if (newTileDot == null) return false;

        // (3) Create the point in the screen coordinate.
        Point2D newTilePt = newTileDot.getPoint();
        Point2D newScreenPt = this.calcTilePointToScreenPoint(newTilePt);

        // (4) Create a line in the screen coordinate.
        Line2D line = new Line2D.Double(newScreenPt, newScreenPt);

        this.mCurLine = new TWILine(line);
        this.mCurLine.setStrokeColor(TWIGeomMgr.COLOR_CURRENT);

        return true;
    }

    public void updateLine(Point pt) {
        assert (this.mCurLine != null);

        Line2D line = (Line2D) this.mCurLine.getShape();

        line.setLine(
            line.getX1(), line.getY1(),
            pt.x, pt.y
        );
    }

    public boolean addLine() {
        assert (this.mCurLine != null);

        TWITileMgr tileMgr = this.mTwi.getTileMgr();

        // (1) Extract (screen) points from the TWILine.
        Line2D line = (Line2D) this.mCurLine.getShape();

        Point2D startScreenPt = line.getP1();
        Point2D endScreenPt = line.getP2();

        // (2) Add Dot in respect to the tile's local coordinate.
        Point2D startTilePt = this.calcScreenPointToTilePoint(startScreenPt);

        Point2D endTilePt = this.calcScreenPointToTilePoint(endScreenPt);
        TWIDot endTileDot = new TWIDot(endTilePt);

        // (3) Try to add the new dot to TileMgr.
        // Use the new dot if tileMgr changed it.
        endTileDot = tileMgr.addDot(endTileDot);

        if (endTileDot == null) return false;

        Point2D newEndTilePt = endTileDot.getPoint();

        // (4) Add TWILine respect to the tile's local coordinate.
        TWILine geomToAdd = new TWILine(
            new Line2D.Double(startTilePt, newEndTilePt)
        );

        tileMgr.getTile().addPattern(geomToAdd);
        // TODO: Move this to TileMgr
        this.mTwi.getPreviewMgr().updateTileImage();

        // (5) Set current line to null.
        this.mCurLine = null;

        return true;
    }

    private Point2D calcScreenPointToTilePoint(Point2D pt) {
        TWITileMgr tileMgr = this.mTwi.getTileMgr();
        int tileX = tileMgr.getTileOrigin().x;
        int tileY = tileMgr.getTileOrigin().y;

        return new Point2D.Double(
            pt.getX() - tileX,
            pt.getY() - tileY
        );
    }

    private Point2D calcTilePointToScreenPoint(Point2D pt) {
        TWITileMgr tileMgr = this.mTwi.getTileMgr();
        int tileX = tileMgr.getTileOrigin().x;
        int tileY = tileMgr.getTileOrigin().y;

        return new Point2D.Double(
            pt.getX() + tileX,
            pt.getY() + tileY
        );
    }
}

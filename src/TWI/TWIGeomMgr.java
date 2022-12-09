package TWI;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import TWI.geom.TWIDot;
import TWI.geom.TWILine;
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
        int tileX = tileMgr.getTileOrigin().x;
        int tileY = tileMgr.getTileOrigin().y;

        // (1) Add Dot in respect to the tile's local coordinate.
        Point ptToAdd = new Point(
            pt.x - tileX,
            pt.y - tileY
        );
        TWIDot dotToAdd = new TWIDot(ptToAdd);

        // (2) Try to add the new dot to TileMgr.
        // Use the new dot if tileMgr changed it.
        TWIDot newDot = tileMgr.addDot(dotToAdd);

        if (newDot == null) return false;

        // (3) Create the point in the screen coordinate.
        Point2D newPt = new Point2D.Double(
            newDot.getPoint().getX() + tileX,
            newDot.getPoint().getY() + tileY
        );

        // (4) Create a line in the screen coordinate.
        Line2D line = new Line2D.Double(
            newPt.getX(), newPt.getY(),
            newPt.getX(), newPt.getY()
        );

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
        int tileX = tileMgr.getTileOrigin().x;
        int tileY = tileMgr.getTileOrigin().y;

        // (1) Extract (screen) points from the TWILine.
        Line2D line = (Line2D) this.mCurLine.getShape();

        Point2D startPt = line.getP1();
        Point2D endPt = line.getP2();

        // (2) Add Dot in respect to the tile's local coordinate.
        Point2D startPtToAdd = new Point2D.Double(
            startPt.getX() - tileX,
            startPt.getY() - tileY
        );

        Point2D endPtToAdd = new Point2D.Double(
            endPt.getX() - tileX,
            endPt.getY() - tileY
        );
        TWIDot endDotToAdd = new TWIDot(endPtToAdd);

        // (3) Try to add the new dot to TileMgr.
        // Use the new dot if tileMgr changed it.
        endDotToAdd = tileMgr.addDot(endDotToAdd);

        if (endDotToAdd == null) return false;

        Point2D newEndPtToAdd = endDotToAdd.getPoint();

        // (4) Add TWILine respect to the tile's local coordinate.
        TWILine geomToAdd = new TWILine(
            new Line2D.Double(startPtToAdd, newEndPtToAdd)
        );

        tileMgr.getTile().addPattern(geomToAdd);
        return true;
    }
}

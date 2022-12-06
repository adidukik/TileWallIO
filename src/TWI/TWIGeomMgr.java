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
    public void createLine(Point pt) {
        TWIDot dotToAdd = new TWIDot(pt);

        TWITileMgr tileMgr = this.mTwi.getTileMgr();
        TWIDot newDot = tileMgr.addDot(dotToAdd);

        Point2D newPt = newDot.getPoint();

        Line2D line = new Line2D.Double(
            newPt.getX(), newPt.getY(),
            newPt.getX(), newPt.getY()
        );

        this.mCurLine = new TWILine(line);
        this.mCurLine.setStrokeColor(TWIGeomMgr.COLOR_CURRENT);
    }

    public void updateLine(Point pt) {
        assert (this.mCurLine != null);

        Line2D line = (Line2D) this.mCurLine.getShape();

        line.setLine(
            line.getX1(), line.getY1(),
            pt.x, pt.y
        );
    }

    public void addLine() {
        assert (this.mCurLine != null);

        Line2D line = (Line2D) this.mCurLine.getShape();

        Point2D startPt = line.getP1();
        Point2D endPt = line.getP2();

        TWIDot dotToAdd = new TWIDot(endPt);
        TWITileMgr tileMgr = this.mTwi.getTileMgr();
        dotToAdd = tileMgr.addDot(dotToAdd);
        endPt = dotToAdd.getPoint();

        TWIPattern patternToAdd = new TWIPattern(
            new TWILine(
                new Line2D.Double(
                    startPt, endPt
                )
            )
        );

        tileMgr.getTile().addPattern(patternToAdd);
    }
}

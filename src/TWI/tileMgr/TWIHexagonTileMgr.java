package TWI.tileMgr;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import TWI.TWI;
import TWI.TWIAnchorDot;
import TWI.geom.TWIDot;
import TWI.tile.TWIHexagonTile;

public class TWIHexagonTileMgr extends TWITileMgr {
    // constants
    private static final double WIDTH_DAFAULT = 350.0d;

    // constructor
    public TWIHexagonTileMgr(TWI twi) {
        super(twi);
        this.mTile = new TWIHexagonTile(WIDTH_DAFAULT);
    }

    @Override
    public TWIAnchorDot calcValidDot(TWIAnchorDot dot) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected boolean isDotInside(TWIDot dot) {
        Point pt = (Point) (Point2D) dot;
        return (
            this.isPointBelowLine(pt, this.mTile.getEdgeList().get(0)) &&

            this.isPointBelowLine(pt, this.mTile.getEdgeList().get(1)) &&
            this.isPointLeftOfLine(pt, this.mTile.getEdgeList().get(1)) &&

            this.isPointAboveLine(pt, this.mTile.getEdgeList().get(2)) &&
            this.isPointLeftOfLine(pt, this.mTile.getEdgeList().get(2)) &&

            this.isPointAboveLine(pt, this.mTile.getEdgeList().get(3)) &&

            this.isPointAboveLine(pt, this.mTile.getEdgeList().get(4)) &&
            this.isPointRightOfLine(pt, this.mTile.getEdgeList().get(4)) &&

            this.isPointBelowLine(pt, this.mTile.getEdgeList().get(5)) &&
            this.isPointRightOfLine(pt, this.mTile.getEdgeList().get(5))
        );
    }

    @Override
    protected boolean isDotOnEdge(TWIDot dot) {
        return (
            isDotOnTopEdge(dot) ||
            isDotOnTopRightEdge(dot) ||
            isDotOnBottomRightEdge(dot) ||
            isDotOnBottomEdge(dot) ||
            isDotOnBottomLeftEdge(dot) ||
            isDotOnTopLeftEdge(dot)
        );
    }

    @Override
    protected TWIAnchorDot getOppositeAnchorDot(TWIAnchorDot dot) {
        // TODO Auto-generated method stub
        return null;
    }

    private boolean isDotOnTopEdge(TWIDot dot) {
        Line2D topEdge = this.mTile.getEdgeList().get(0);

        return this.calcDistanceFromPointToLine(dot, topEdge) <=
            this.getSnapRange();
    }

    private boolean isDotOnTopRightEdge(TWIDot dot) {
        Line2D topRightEdge = this.mTile.getEdgeList().get(1);

        return this.calcDistanceFromPointToLine(dot, topRightEdge) <=
            this.getSnapRange();
    }

    private boolean isDotOnBottomRightEdge(TWIDot dot) {
        Line2D bottomRightEdge = this.mTile.getEdgeList().get(2);

        return this.calcDistanceFromPointToLine(dot, bottomRightEdge) <=
            this.getSnapRange();
    }

    private boolean isDotOnBottomEdge(TWIDot dot) {
        Line2D bottomEdge = this.mTile.getEdgeList().get(3);

        return this.calcDistanceFromPointToLine(dot, bottomEdge) <=
            this.getSnapRange();
    }

    private boolean isDotOnBottomLeftEdge(TWIDot dot) {
        Line2D bottomeLeftEdge = this.mTile.getEdgeList().get(4);

        return this.calcDistanceFromPointToLine(dot, bottomeLeftEdge) <=
            this.getSnapRange();
    }

    private boolean isDotOnTopLeftEdge(TWIDot dot) {
        Line2D topLeftEdge = this.mTile.getEdgeList().get(5);

        return this.calcDistanceFromPointToLine(dot, topLeftEdge) <=
            this.getSnapRange();
    }
}

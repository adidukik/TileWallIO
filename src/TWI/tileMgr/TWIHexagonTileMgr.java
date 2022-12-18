package TWI.tileMgr;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import TWI.TWI;
import TWI.TWIAnchorDot;
import TWI.geom.TWIDot;
import TWI.tile.TWIHexagonTile;

public class TWIHexagonTileMgr extends TWITileMgr {
    // constants
    private static final double WIDTH_DAFAULT = 200;

    // constructor
    public TWIHexagonTileMgr(TWI twi) {
        super(twi);
        this.mTile = new TWIHexagonTile(WIDTH_DAFAULT);
    }

    @Override
    public TWIAnchorDot calcValidDot(TWIAnchorDot dot) {
        if (!dot.getIsSnappable()) return dot;

        if (!this.isDotInside(dot)) return null;

        for (TWIAnchorDot anchorDot : this.mAnchorDots) {
            if (this.mIsSnapOn) {
                if (
                    anchorDot.getIsSnappable() &&
                    anchorDot.distance(dot) < TWITileMgr.SNAP_RADIUS +
                        TWITileMgr.CALCULATION_TOLERANCE
                ) {
                    return anchorDot;
                }
            } else {
                if (
                    anchorDot.getIsSnappable() &&
                    anchorDot.distance(dot) < TWITileMgr.CALCULATION_TOLERANCE
                ) {
                    return anchorDot;
                }
            }

        }

        if (isDotOnTopEdge(dot)) {
            Point2D closestPt = this.calcClosestPointOnLine(
                dot, this.mTile.getEdgeList().get(0)
            );

            dot.setLocation(closestPt);

        } else if (isDotOnTopRightEdge(dot)) {
            Point2D closestPt = this.calcClosestPointOnLine(
                dot, this.mTile.getEdgeList().get(1)
            );

            dot.setLocation(closestPt);

        } else if (isDotOnBottomRightEdge(dot)) {
            Point2D closestPt = this.calcClosestPointOnLine(
                dot, this.mTile.getEdgeList().get(2)
            );

            dot.setLocation(closestPt);

        } else if (isDotOnBottomEdge(dot)) {
            Point2D closestPt = this.calcClosestPointOnLine(
                dot, this.mTile.getEdgeList().get(3)
            );

            dot.setLocation(closestPt);

        } else if (isDotOnBottomLeftEdge(dot)) {
            Point2D closestPt = this.calcClosestPointOnLine(
                dot, this.mTile.getEdgeList().get(4)
            );

            dot.setLocation(closestPt);

        } else if (isDotOnTopLeftEdge(dot)) {
            Point2D closestPt = this.calcClosestPointOnLine(
                dot, this.mTile.getEdgeList().get(5)
            );

            dot.setLocation(closestPt);
        }

        return dot;
    }

    @Override
    protected boolean isDotInside(TWIDot dot) {
        Point pt = new Point((int) dot.getX(), (int) dot.getY());

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
    protected ArrayList<TWIAnchorDot> getOppositeAnchorDots(TWIAnchorDot dot) {
        assert (isDotOnEdge(dot));

        Point2D oppositePt;
        if (isDotOnTopEdge(dot)) {
            oppositePt = calcClosestPointOnLine(
                dot, this.mTile.getEdgeList().get(3)
            );

        } else if (isDotOnTopRightEdge(dot)) {
            oppositePt = calcClosestPointOnLine(
                dot, this.mTile.getEdgeList().get(4)
            );

        } else if (isDotOnBottomRightEdge(dot)) {
            oppositePt = calcClosestPointOnLine(
                dot, this.mTile.getEdgeList().get(5)
            );

        } else if (isDotOnBottomEdge(dot)) {
            oppositePt = calcClosestPointOnLine(
                dot, this.mTile.getEdgeList().get(0)
            );

        } else if (isDotOnBottomLeftEdge(dot)) {
            oppositePt = calcClosestPointOnLine(
                dot, this.mTile.getEdgeList().get(1)
            );

        } else {
            oppositePt = calcClosestPointOnLine(
                dot, this.mTile.getEdgeList().get(2)
            );
        }

        ArrayList<TWIAnchorDot> anchorDots = new ArrayList<>();
        anchorDots.add(new TWIAnchorDot(
            oppositePt.getX(),
            oppositePt.getY(),
            TWIAnchorDot.SnappableFlag.SNAPPABLE,
            TWIAnchorDot.ClickableFlag.NOT_CLICKABLE
        ));

        return anchorDots;
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

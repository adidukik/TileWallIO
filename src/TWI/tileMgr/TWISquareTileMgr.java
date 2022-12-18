package TWI.tileMgr;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import TWI.TWI;
import TWI.TWIAnchorDot;
import TWI.geom.TWIDot;
import TWI.geom.TWIRectangle;
import TWI.tile.TWISquareTile;

public class TWISquareTileMgr extends TWITileMgr {
    // constants
    private static final double WIDTH_DAFAULT = 400.0;

    // constructor
    public TWISquareTileMgr(TWI twi) {
        super(twi);
        this.mTile = new TWISquareTile(
            TWISquareTileMgr.WIDTH_DAFAULT
        );
    }

    // methods
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

        } else if (isDotOnRightEdge(dot)) {
            Point2D closestPt = this.calcClosestPointOnLine(
                dot, this.mTile.getEdgeList().get(1)
            );

            dot.setLocation(closestPt);

        } else if (isDotOnBottomEdge(dot)) {
            Point2D closestPt = this.calcClosestPointOnLine(
                dot, this.mTile.getEdgeList().get(2)
            );

            dot.setLocation(closestPt);

        } else if (isDotOnLeftEdge(dot)) {
            Point2D closestPt = this.calcClosestPointOnLine(
                dot, this.mTile.getEdgeList().get(3)
            );

            dot.setLocation(closestPt);
        }

        return dot;
    }

    @Override
    protected boolean isDotInside(TWIDot dot) {
        return ((TWIRectangle) this.mTile.getTileGeom()).contains(dot);
    }

    @Override
    protected boolean isDotOnEdge(TWIDot dot) {
        return (
            isDotOnTopEdge(dot) || isDotOnBottomEdge(dot) ||
            isDotOnLeftEdge(dot) || isDotOnRightEdge(dot)
        );
    }

    @Override
    protected TWIAnchorDot getOppositeAnchorDot(TWIAnchorDot dot) {
        assert (isDotOnEdge(dot));

        Point2D oppositePt;
        if (isDotOnTopEdge(dot)) {
            oppositePt = calcClosestPointOnLine(
                dot, this.mTile.getEdgeList().get(2)
            );

        } else if (isDotOnRightEdge(dot)) {
            oppositePt = calcClosestPointOnLine(
                dot, this.mTile.getEdgeList().get(3)
            );

        } else if (isDotOnBottomEdge(dot)) {
            oppositePt = calcClosestPointOnLine(
                dot, this.mTile.getEdgeList().get(0)
            );

        } else {
            oppositePt = calcClosestPointOnLine(
                dot, this.mTile.getEdgeList().get(1)
            );
        }

        return new TWIAnchorDot(
            oppositePt.getX(),
            oppositePt.getY(),
            TWIAnchorDot.SnappableFlag.SNAPPABLE,
            TWIAnchorDot.ClickableFlag.NOT_CLICKABLE
        );
    }

    private boolean isDotOnTopEdge(TWIDot dot) {
        Line2D topEdge = this.mTile.getEdgeList().get(0);

        return this.calcDistanceFromPointToLine(dot, topEdge) <=
            this.getSnapRange();
    }

    private boolean isDotOnRightEdge(TWIDot dot) {
        Line2D rightEdge = this.mTile.getEdgeList().get(1);

        return this.calcDistanceFromPointToLine(dot, rightEdge) <=
            this.getSnapRange();
    }

    private boolean isDotOnBottomEdge(TWIDot dot) {
        Line2D bottomEdge = this.mTile.getEdgeList().get(2);

        return this.calcDistanceFromPointToLine(dot, bottomEdge) <=
            this.getSnapRange();
    }

    private boolean isDotOnLeftEdge(TWIDot dot) {
        Line2D leftEdge = this.mTile.getEdgeList().get(3);

        return this.calcDistanceFromPointToLine(dot, leftEdge) <=
            this.getSnapRange();
    }
}

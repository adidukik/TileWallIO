package TWI.tileMgr;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import TWI.TWI;
import TWI.TWIAnchorDot;
import TWI.geom.TWIDot;
import TWI.tile.TWITriangleTile;

public class TWITriangleTileMgr extends TWITileMgr {
    // constants
    private static final double WIDTH_DEFAULT = 400d;

    // constructor
    public TWITriangleTileMgr(TWI twi) {
        super(twi);
        this.mTile = new TWITriangleTile(WIDTH_DEFAULT);
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

        if (isDotOnTopRightEdge(dot)) {
            Point2D closestPt = this.calcClosestPointOnLine(
                dot, this.mTile.getEdgeList().get(0)
            );

            dot.setLocation(closestPt);

        } else if (isDotOnBottomEdge(dot)) {
            Point2D closestPt = this.calcClosestPointOnLine(
                dot, this.mTile.getEdgeList().get(1)
            );

            dot.setLocation(closestPt);

        } else if (isDotOnTopLeftEdge(dot)) {
            Point2D closestPt = this.calcClosestPointOnLine(
                dot, this.mTile.getEdgeList().get(2)
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
            this.isPointLeftOfLine(pt, this.mTile.getEdgeList().get(0)) &&

            this.isPointAboveLine(pt, this.mTile.getEdgeList().get(1)) &&

            this.isPointBelowLine(pt, this.mTile.getEdgeList().get(2)) &&
            this.isPointRightOfLine(pt, this.mTile.getEdgeList().get(2))
        );
    }

    @Override
    protected boolean isDotOnEdge(TWIDot dot) {
        return (
            isDotOnTopRightEdge(dot) ||
            isDotOnBottomEdge(dot) ||
            isDotOnTopLeftEdge(dot)
        );
    }

    @Override
    protected ArrayList<TWIAnchorDot> getOppositeAnchorDots(TWIAnchorDot dot) {
        assert (isDotOnEdge(dot));

        Point2D oppositePt1;
        Point2D oppositePt2;

        if (isDotOnTopRightEdge(dot)) {
            oppositePt1 = new Point2D.Double(
                TWITriangleTileMgr.WIDTH_DEFAULT - dot.getX(),
                dot.getY()
            );
            oppositePt2 = new Point2D.Double(
                TWITriangleTileMgr.WIDTH_DEFAULT - (
                    (TWITriangleTileMgr.WIDTH_DEFAULT - dot.getX())
                    * Math.sqrt(3)
                ),
                TWITriangleTileMgr.WIDTH_DEFAULT * Math.sqrt(3) / 2
            );

        } else if (isDotOnBottomEdge(dot)) {
            oppositePt1 = new Point2D.Double(
                TWITriangleTileMgr.WIDTH_DEFAULT - (
                    (TWITriangleTileMgr.WIDTH_DEFAULT - dot.getX()) / 2
                ),
                Math.sqrt(3) / 2 * TWITriangleTileMgr.WIDTH_DEFAULT - (
                    (TWITriangleTileMgr.WIDTH_DEFAULT - dot.getX()) *
                        Math.sqrt(3) / 2
                )
            );
            oppositePt2 = new Point2D.Double(
                dot.getX() / 2,
                Math.sqrt(3) / 2 * TWITriangleTileMgr.WIDTH_DEFAULT - (
                    dot.getX() * Math.sqrt(3) / 2
                )
            );

        } else {
            oppositePt1 = new Point2D.Double(
                TWITriangleTileMgr.WIDTH_DEFAULT - dot.getX(),
                dot.getY()
            );
            oppositePt2 = new Point2D.Double(
                dot.getX() * Math.sqrt(3),
                TWITriangleTileMgr.WIDTH_DEFAULT * Math.sqrt(3) / 2
            );
        }

        ArrayList<TWIAnchorDot> anchorDots = new ArrayList<>();
        anchorDots.add(new TWIAnchorDot(
            oppositePt1.getX(),
            oppositePt1.getY(),
            TWIAnchorDot.SnappableFlag.SNAPPABLE,
            TWIAnchorDot.ClickableFlag.NOT_CLICKABLE
        ));
        anchorDots.add(new TWIAnchorDot(
            oppositePt2.getX(),
            oppositePt2.getY(),
            TWIAnchorDot.SnappableFlag.SNAPPABLE,
            TWIAnchorDot.ClickableFlag.NOT_CLICKABLE
        ));



        return anchorDots;
    }

    private boolean isDotOnTopRightEdge(TWIDot dot) {
        Line2D topRightEdge = this.mTile.getEdgeList().get(0);

        return this.calcDistanceFromPointToLine(dot, topRightEdge) <=
            this.getSnapRange();
    }

    private boolean isDotOnBottomEdge(TWIDot dot) {
        Line2D bottomEdge = this.mTile.getEdgeList().get(1);

        return this.calcDistanceFromPointToLine(dot, bottomEdge) <=
            this.getSnapRange();
    }

    private boolean isDotOnTopLeftEdge(TWIDot dot) {
        Line2D topLeftEdge = this.mTile.getEdgeList().get(2);

        return this.calcDistanceFromPointToLine(dot, topLeftEdge) <=
            this.getSnapRange();
    }
}

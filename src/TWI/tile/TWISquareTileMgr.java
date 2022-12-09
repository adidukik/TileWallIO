package TWI.tile;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import TWI.TWI;
import TWI.geom.TWIDot;

public class TWISquareTileMgr extends TWITileMgr {
    // constants
    private static final double WIDTH_DAFAULT = 400.0;
    private static final double HEIGHT_DEFAULT = 400.0;

    // constructor
    public TWISquareTileMgr(TWI twi) {
        super(twi);
        this.mTile = new TWISquareTile(
            TWISquareTileMgr.WIDTH_DAFAULT,
            TWISquareTileMgr.HEIGHT_DEFAULT
        );
    }

    // methods
    @Override
    public TWIDot addDot(TWIDot dot) {
        if (!this.isDotInside(dot)) {
            return null;
        }

        Point2D pt = dot.getPoint();

        for (TWIDot anchorDot : this.mTile.getAnchorDots()) {
            if (anchorDot.getPoint().distance(pt) < TWITileMgr.SNAP_RADIUS) {
                return anchorDot;
            }
        }

        Rectangle2D rect = (Rectangle2D) this.mTile.getTileGeom().getShape();
        Double tileHeight = rect.getHeight();
        Double tileWidth = rect.getWidth();

        Point2D pointToAdd;
        TWIDot dotToAdd;
        if (isDotOnTopEdge(dot)) {
            pointToAdd = new Point2D.Double(
                pt.getX(),
                rect.getY()
            );

            dotToAdd = new TWIDot(pointToAdd);

        } else if (isDotOnBottomEdge(dot)) {
            pointToAdd = new Point2D.Double(
                pt.getX(),
                rect.getY() + tileHeight
            );

            dotToAdd = new TWIDot(pointToAdd);

        } else if (isDotOnLeftEdge(dot)) {
            pointToAdd = new Point2D.Double(
                rect.getX(),
                pt.getY()
            );

            dotToAdd = new TWIDot(pointToAdd);

        } else if (isDotOnRightEdge(dot)) {
            pointToAdd = new Point2D.Double(
                rect.getX() + tileWidth,
                pt.getY()
            );

            dotToAdd = new TWIDot(pointToAdd);

        } else {
            dotToAdd = dot;
        }

        this.mTile.addAnchorDot(dotToAdd);

        if (this.isDotOnEdge(dot)) {
            TWIDot oppositePoint = getOppositeAnchorDot(dotToAdd);
            this.mTile.addAnchorDot(oppositePoint);
        }

        return dotToAdd;
    }

    @Override
    protected boolean isDotOnEdge(TWIDot dot) {
        return (
            isDotOnTopEdge(dot) || isDotOnBottomEdge(dot) ||
            isDotOnLeftEdge(dot) || isDotOnRightEdge(dot)
        );
    }

    @Override
    protected TWIDot getOppositeAnchorDot(TWIDot dot) {
        assert (isDotOnEdge(dot));

        Point2D pt = dot.getPoint();

        Rectangle2D rect = (Rectangle2D) this.mTile.getTileGeom().getShape();
        Double tileHeight = rect.getHeight();
        Double tileWidth = rect.getWidth();

        Point2D oppositePoint;
        if (isDotOnTopEdge(dot)) {
            oppositePoint = new Point2D.Double(
                pt.getX(),
                rect.getY() + tileHeight
            );

        } else if (isDotOnBottomEdge(dot)) {
            oppositePoint = new Point2D.Double(
                pt.getX(),
                rect.getY()
            );
        } else if (isDotOnLeftEdge(dot)) {
            oppositePoint = new Point2D.Double(
                rect.getX() + tileWidth,
                pt.getY()
            );

        } else {
            oppositePoint = new Point2D.Double(
                rect.getX(),
                pt.getY()
            );
        }

        TWIDot oppositeDot = new TWIDot(oppositePoint);
        return oppositeDot;
    }

    private boolean isDotOnTopEdge(TWIDot dot) {
        Point2D pt = dot.getPoint();
        Rectangle2D rect = (Rectangle2D) this.mTile.getTileGeom().getShape();
        Double y = rect.getY();

        Double range;
        if (this.mIsSnapOn) {
            range = TWITileMgr.CALCULATION_TOLERANCE +
                TWITileMgr.SNAP_RADIUS;
        } else {
            range = TWITileMgr.CALCULATION_TOLERANCE;
        }

        if ((y - range) <= pt.getY() && pt.getY() <= (y + range)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isDotOnBottomEdge(TWIDot dot) {
        Point2D pt = dot.getPoint();
        Rectangle2D rect = (Rectangle2D) this.mTile.getTileGeom().getShape();
        Double y = rect.getY() + rect.getHeight();

        Double range;
        if (this.mIsSnapOn) {
            range = TWITileMgr.CALCULATION_TOLERANCE +
                TWITileMgr.SNAP_RADIUS;
        } else {
            range = TWITileMgr.CALCULATION_TOLERANCE;
        }


        if ((y - range) <= pt.getY() && pt.getY() <= (y + range)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isDotOnLeftEdge(TWIDot dot) {
        Point2D pt = dot.getPoint();
        Rectangle2D rect = (Rectangle2D) this.mTile.getTileGeom().getShape();
        Double x = rect.getX();

        Double range;
        if (this.mIsSnapOn) {
            range = TWITileMgr.CALCULATION_TOLERANCE +
                TWITileMgr.SNAP_RADIUS;
        } else {
            range = TWITileMgr.CALCULATION_TOLERANCE;
        }


        if ((x - range) <= pt.getX() && pt.getX() <= (x + range)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isDotOnRightEdge(TWIDot dot) {
        Point2D pt = dot.getPoint();
        Rectangle2D rect = (Rectangle2D) this.mTile.getTileGeom().getShape();
        Double x = rect.getX() + rect.getWidth();

        Double range;
        if (this.mIsSnapOn) {
            range = TWITileMgr.CALCULATION_TOLERANCE +
                TWITileMgr.SNAP_RADIUS;
        } else {
            range = TWITileMgr.CALCULATION_TOLERANCE;
        }


        if ((x - range) <= pt.getX() && pt.getX() <= (x + range)) {
            return true;
        } else {
            return false;
        }
    }
}

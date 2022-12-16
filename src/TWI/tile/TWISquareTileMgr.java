package TWI.tile;

import TWI.TWI;
import TWI.TWIAnchorDot;
import TWI.geom.TWIDot;
import TWI.geom.TWIRectangle;

public class TWISquareTileMgr extends TWITileMgr {
    // constants
    private static final double WIDTH_DAFAULT = 400.0;
    private static final double HEIGHT_DEFAULT = 400.0;

    // constructor
    public TWISquareTileMgr(TWI twi) {
        super(twi);
        this.mTile = new TWISquareTile(
            TWITileMgr.X_DEFAULT,
            TWITileMgr.Y_DEFAULT,
            TWISquareTileMgr.WIDTH_DAFAULT,
            TWISquareTileMgr.HEIGHT_DEFAULT
        );
    }

    // methods
    @Override
    public TWIDot calcValidDot(TWIDot dot) {
        if (!this.isDotInside(dot)) {
            return null;
        }

        for (TWIDot anchorDot : this.mTile.getAnchorDots()) {
            if (anchorDot.distance(dot) < TWITileMgr.SNAP_RADIUS) {
                return anchorDot;
            }
        }

        TWIRectangle rect = (TWIRectangle) this.mTile.getTileGeom();
        Double tileHeight = rect.getHeight();
        Double tileWidth = rect.getWidth();

        if (isDotOnTopEdge(dot)) {
            dot = new TWIDot(
                dot.getX(),
                rect.getY()
            );

        } else if (isDotOnBottomEdge(dot)) {
            dot = new TWIDot(
                dot.getX(),
                rect.getY() + tileHeight
            );

        } else if (isDotOnLeftEdge(dot)) {
            dot = new TWIDot(
                rect.getX(),
                dot.getY()
            );

        } else if (isDotOnRightEdge(dot)) {
            dot = new TWIDot(
                rect.getX() + tileWidth,
                dot.getY()
            );
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
    protected void addAnchorDot(TWIAnchorDot anchorDot) {
        this.mTile.getAnchorDots().add(anchorDot);

        if (this.isDotOnEdge(anchorDot)) {
            TWIAnchorDot oppositeAnchorDot = getOppositeAnchorDot(anchorDot);
            this.mTile.getEdgeAnchorDotTable().put(
                anchorDot, oppositeAnchorDot
            );
            this.mTile.getAnchorDots().add(oppositeAnchorDot);
        }
    }

    @Override
    protected TWIAnchorDot getOppositeAnchorDot(TWIAnchorDot dot) {
        assert (isDotOnEdge(dot));

        TWIRectangle rect = (TWIRectangle) this.mTile.getTileGeom();
        Double tileHeight = rect.getHeight();
        Double tileWidth = rect.getWidth();

        if (isDotOnTopEdge(dot)) {
            return new TWIAnchorDot(
                dot.getX(),
                rect.getY() + tileHeight,
                TWIAnchorDot.SnappableFlag.SNAPPABLE,
                TWIAnchorDot.ClickableFlag.NOT_CLICKABLE
            );

        } else if (isDotOnBottomEdge(dot)) {
            return new TWIAnchorDot(
                dot.getX(),
                rect.getY(),
                TWIAnchorDot.SnappableFlag.SNAPPABLE,
                TWIAnchorDot.ClickableFlag.NOT_CLICKABLE
            );

        } else if (isDotOnLeftEdge(dot)) {
            return new TWIAnchorDot(
                rect.getX() + tileWidth,
                dot.getY(),
                TWIAnchorDot.SnappableFlag.SNAPPABLE,
                TWIAnchorDot.ClickableFlag.NOT_CLICKABLE
            );

        } else {
            return new TWIAnchorDot(
                rect.getX(),
                dot.getY(),
                TWIAnchorDot.SnappableFlag.SNAPPABLE,
                TWIAnchorDot.ClickableFlag.NOT_CLICKABLE
            );
        }
    }

    private boolean isDotOnTopEdge(TWIDot dot) {
        TWIRectangle rect = (TWIRectangle) this.mTile.getTileGeom();
        Double y = rect.getY();

        Double range;
        if (this.mIsSnapOn) {
            range = TWITileMgr.CALCULATION_TOLERANCE +
                TWITileMgr.SNAP_RADIUS;
        } else {
            range = TWITileMgr.CALCULATION_TOLERANCE;
        }

        if ((y - range) <= dot.getY() && dot.getY() <= (y + range)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isDotOnBottomEdge(TWIDot dot) {
        TWIRectangle rect = (TWIRectangle) this.mTile.getTileGeom();
        Double y = rect.getY() + rect.getHeight();

        Double range;
        if (this.mIsSnapOn) {
            range = TWITileMgr.CALCULATION_TOLERANCE +
                TWITileMgr.SNAP_RADIUS;
        } else {
            range = TWITileMgr.CALCULATION_TOLERANCE;
        }


        if ((y - range) <= dot.getY() && dot.getY() <= (y + range)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isDotOnLeftEdge(TWIDot dot) {
        TWIRectangle rect = (TWIRectangle) this.mTile.getTileGeom();
        Double x = rect.getX();

        Double range;
        if (this.mIsSnapOn) {
            range = TWITileMgr.CALCULATION_TOLERANCE +
                TWITileMgr.SNAP_RADIUS;
        } else {
            range = TWITileMgr.CALCULATION_TOLERANCE;
        }


        if ((x - range) <= dot.getX() && dot.getX() <= (x + range)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isDotOnRightEdge(TWIDot dot) {
        TWIRectangle rect = (TWIRectangle) this.mTile.getTileGeom();
        Double x = rect.getX() + rect.getWidth();

        Double range;
        if (this.mIsSnapOn) {
            range = TWITileMgr.CALCULATION_TOLERANCE +
                TWITileMgr.SNAP_RADIUS;
        } else {
            range = TWITileMgr.CALCULATION_TOLERANCE;
        }


        if ((x - range) <= dot.getX() && dot.getX() <= (x + range)) {
            return true;
        } else {
            return false;
        }
    }
}

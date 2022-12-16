package TWI.tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import TWI.TWI;
import TWI.TWIAnchorDot;
import TWI.TWIRenderable;
import TWI.geom.TWIDot;
import TWI.geom.TWIGeom;
import TWI.geom.TWIRectangle;
import TWI.pattern.TWIPattern;
import TWI.pattern.TWIPatternFactory;

public abstract class TWITileMgr implements TWIRenderable {
    // constant
    protected static final double SNAP_RADIUS = 20.0f;
    protected static final double CALCULATION_TOLERANCE = 5.0f;
    protected static final double X_DEFAULT = 100.0;
    protected static final double Y_DEFAULT = 100.0;

    protected static final Color BG_STROKE_COLOR =
        new Color(0, 0, 0, 0);
    protected static final Color BG_FILL_COLOR =
        new Color(253, 253, 150);

    // fields
    private TWI mTWI = null;

    private Point mTileOrigin = null;

    public Point getTileOrigin() {
        return this.mTileOrigin;
    }

    protected boolean mIsSnapOn = false;

    public boolean getmIsSnapOn() {
        return this.mIsSnapOn;
    }

    public void setIsSnapOn(boolean b) {
        this.mIsSnapOn = b;
    }

    protected TWITile mTile = null;

    public TWITile getTile() {
        return this.mTile;
    }

    // constructor
    protected TWITileMgr(TWI twi) {
        this.mTWI = twi;

        this.mTileOrigin = new Point(
            (int) TWITileMgr.X_DEFAULT,
            (int) TWITileMgr.Y_DEFAULT
        );
    }

    // abstract methods
    public abstract TWIDot calcValidDot(TWIDot dot);

    protected abstract boolean isDotInside(TWIDot dot);
    protected abstract boolean isDotOnEdge(TWIDot dot);
    protected abstract void addAnchorDot(TWIAnchorDot dot);
    protected abstract TWIAnchorDot getOppositeAnchorDot(TWIAnchorDot dot);

    // methods
    public void addPattern(TWIGeom geom) {
        TWIPattern pattern = TWIPatternFactory.getPattern(geom);

        this.mTile.getPatterns().add(pattern);

        for (TWIAnchorDot dot : pattern.getAnchorDots()) {
            this.addAnchorDot(dot);
        }

        this.mTWI.getPreviewMgr().updateTileImage();
    }

    public void selectPattern(TWIPattern pattern) {
        this.mTile.getPatterns().remove(pattern);
        this.mTile.getSelectedPatterns().add(pattern);
    }

    public void deselectAllPatterns() {
        ArrayList<TWIPattern> deselectPatterns = new ArrayList<>();

        for (TWIPattern pattern : this.mTile.getSelectedPatterns()) {
            deselectPatterns.add(pattern);
        }

        this.mTile.getSelectedPatterns().removeAll(deselectPatterns);
        this.mTile.getPatterns().addAll(deselectPatterns);
    }

    public void removeSelectedPatterns() {
        for (TWIPattern pattern : this.mTile.getSelectedPatterns()) {
            this.removeAnchorDots(pattern);
        }

        this.mTile.getSelectedPatterns().clear();

        this.mTWI.getPreviewMgr().updateTileImage();
    }

    public void removeAllPattern() {
        for (TWIPattern pattern : this.mTile.getPatterns()) {
            this.removeAnchorDots(pattern);
        }

        for (TWIPattern pattern : this.mTile.getSelectedPatterns()) {
            this.removeAnchorDots(pattern);
        }

        this.mTile.getPatterns().clear();
        this.mTile.getSelectedPatterns().clear();

        this.mTWI.getPreviewMgr().updateTileImage();
    }

    private void removeAnchorDots(TWIPattern pattern) {
        for (TWIAnchorDot anchorDot : pattern.getAnchorDots()) {
            if (this.mTile.getEdgeAnchorDotTable().containsKey(anchorDot)) {
                TWIAnchorDot oppositeAnchorDot =
                    this.mTile.getEdgeAnchorDotTable().get(anchorDot);
                this.mTile.getAnchorDots().remove(oppositeAnchorDot);
                this.mTile.getEdgeAnchorDotTable().remove(anchorDot);
            }

            this.mTile.getAnchorDots().remove(anchorDot);
        }
    }

    // interface methods
    @Override
    public void render(Graphics2D g2, Point origin) {
        double bgW = this.mTWI.getCanvas2d().getWidth() / 2;
        double bgH = this.mTWI.getCanvas2d().getHeight();

        TWIRectangle bg = new TWIRectangle(0, 0, bgW, bgH);

        bg.setStrokeColor(TWITileMgr.BG_STROKE_COLOR);
        bg.setFillColor(TWITileMgr.BG_FILL_COLOR);

        bg.render(g2, origin);

        Point tilePos = new Point(
            origin.x + mTileOrigin.x,
            origin.y + mTileOrigin.y
        );

        this.mTile.render(g2, tilePos);
    }
}

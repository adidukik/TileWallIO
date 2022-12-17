package TWI.tile;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Hashtable;

import TWI.TWI;
import TWI.TWIAnchorDot;
import TWI.geom.TWIDot;
import TWI.geom.TWIGeom;
import TWI.geom.TWIRectangle;
import TWI.pattern.TWIPattern;
import TWI.pattern.TWIPatternFactory;

public abstract class TWITileMgr {
    // constant
    protected static final double SNAP_RADIUS = 20.0f;
    protected static final double CALCULATION_TOLERANCE = 5.0f;
    protected static final double X_DEFAULT = 100.0;
    protected static final double Y_DEFAULT = 100.0;

    protected static final Color BG_STROKE_COLOR =
        new Color(0, 0, 0, 0);
    protected static final Color BG_FILL_COLOR =
        new Color(253, 253, 150);

    private static final float STROKE_WIDTH_INCREMENT = 1.0f;

    // fields
    private TWI mTWI = null;


    private Point mTileOrigin = null;

    public Point getTileOrigin() {
        return this.mTileOrigin;
    }


    protected boolean mIsSnapOn = false;

    public boolean getIsSnapOn() {
        return this.mIsSnapOn;
    }

    public void setIsSnapOn(boolean b) {
        this.mIsSnapOn = b;
    }


    protected TWITile mTile = null;

    public TWITile getTile() {
        return this.mTile;
    }


    protected ArrayList<TWIAnchorDot> mAnchorDots = null;

    public ArrayList<TWIAnchorDot> getAnchorDots() {
        return this.mAnchorDots;
    }


    protected Hashtable<TWIAnchorDot, TWIAnchorDot> mEdgeAnchorDotTable = null;

    public Hashtable<TWIAnchorDot, TWIAnchorDot> getEdgeAnchorDotTable() {
        return this.mEdgeAnchorDotTable;
    }


    private TWIAnchorDot mSelectAnchorDot = null;

    // constructor
    protected TWITileMgr(TWI twi) {
        this.mTWI = twi;

        this.mTileOrigin = new Point(
            (int) TWITileMgr.X_DEFAULT,
            (int) TWITileMgr.Y_DEFAULT
        );

        this.mAnchorDots = new ArrayList<>();
        this.mEdgeAnchorDotTable = new Hashtable<>();
    }

    // abstract methods
    public abstract TWIAnchorDot calcValidDot(TWIAnchorDot dot);

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
        pattern.setHighlightColor();
        this.mTile.getPatterns().remove(pattern);
        this.mTile.getSelectedPatterns().add(pattern);
    }

    public void deselectAllPatterns() {
        ArrayList<TWIPattern> deselectPatterns = new ArrayList<>();

        for (TWIPattern pattern : this.mTile.getSelectedPatterns()) {
            pattern.unsetHighlightColor();
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

    public void setSelectedPatternsColor(Color color) {
        for (TWIPattern pattern : this.mTile.getSelectedPatterns()) {
            pattern.unsetHighlightColor();
            pattern.setFillColor(color);
            pattern.setStrokeColor(color);
        }

        this.deselectAllPatterns();
    }

    public void increaseSelectedPatternsStrokeWidth() {
        for (TWIPattern pattern : this.mTile.getSelectedPatterns()) {
            BasicStroke stroke = (BasicStroke) pattern.getStroke();
            pattern.setStroke(
                new BasicStroke(
                    stroke.getLineWidth() + TWITileMgr.STROKE_WIDTH_INCREMENT,
                    stroke.getEndCap(),
                    stroke.getLineJoin()
                )
            );
        }
    }

    public void decreaseSelectedPatternsStrokeWidth() {
        for (TWIPattern pattern : this.mTile.getSelectedPatterns()) {
            BasicStroke stroke = (BasicStroke) pattern.getStroke();

            if (stroke.getLineWidth() > TWITileMgr.STROKE_WIDTH_INCREMENT) {
                pattern.setStroke(
                    new BasicStroke(
                        stroke.getLineWidth() -
                             TWITileMgr.STROKE_WIDTH_INCREMENT,
                        stroke.getEndCap(),
                        stroke.getLineJoin()
                    )
                );
            }
        }
    }


    private void removeAnchorDots(TWIPattern pattern) {
        for (TWIAnchorDot anchorDot : pattern.getAnchorDots()) {
            this.removeOppositeAnchorDotIfAny(anchorDot);

            this.mAnchorDots.remove(anchorDot);
        }
    }

    public boolean selectAnchorDot(Point pt) {
        assert(this.mSelectAnchorDot == null);

        Point tilePt = new Point(
            pt.x - this.getTileOrigin().x,
            pt.y - this.getTileOrigin().y
        );

        for (TWIAnchorDot anchorDot : this.mAnchorDots) {
            if (
                anchorDot.getIsClickable() &&
                anchorDot.distance(tilePt) < TWITileMgr.CALCULATION_TOLERANCE
            ) {
                this.mSelectAnchorDot = anchorDot;
                return true;
            }
        }

        return false;
    }

    public boolean moveSelectedAnchorDotTo(Point pt) {
        assert(this.mSelectAnchorDot != null);

        if (this.mSelectAnchorDot.getIsSnappable()) {
            TWIAnchorDot newAnchorDot = new TWIAnchorDot(
                pt.x - this.getTileOrigin().x,
                pt.y - this.getTileOrigin().y,
                this.mSelectAnchorDot.getSnappableFlag(),
                this.mSelectAnchorDot.getClickableFlag()
            );

            newAnchorDot = this.calcValidDot(newAnchorDot);

            if (newAnchorDot == null) return false;

            this.removeOppositeAnchorDotIfAny(this.mSelectAnchorDot);

            this.mSelectAnchorDot.setLocation(
                newAnchorDot.getX(),
                newAnchorDot.getY()
            );

        } else {
            this.removeOppositeAnchorDotIfAny(this.mSelectAnchorDot);

            this.mSelectAnchorDot.setLocation(
                pt.x - this.getTileOrigin().x,
                pt.y - this.getTileOrigin().y
            );
        }

        for (TWIPattern pattern : this.mTile.getPatterns()) {
            pattern.update();
        }

        this.mTWI.getPreviewMgr().updateTileImage();

        return true;
    }


    private boolean removeOppositeAnchorDotIfAny(TWIAnchorDot anchorDot) {
        if (this.mEdgeAnchorDotTable.containsKey(anchorDot)) {
            TWIAnchorDot oppositeAnchorDot =
                this.mEdgeAnchorDotTable.get(anchorDot);
            this.mEdgeAnchorDotTable.remove(oppositeAnchorDot);
            this.mEdgeAnchorDotTable.remove(anchorDot);

            return true;

        } else {
            return false;
        }
    }


    public void renderTileEditor(Graphics2D g2, Point origin) {
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
        this.mTile.renderAnchorDots(g2, tilePos);

        for (
            TWIAnchorDot oppositeAnchorDot : this.mEdgeAnchorDotTable.values()
        ) {
            oppositeAnchorDot.render(g2, tilePos);
        }
    }

    public void renderController(Graphics2D g2, Point origin) {
        Point tilePos = new Point(
            origin.x + mTileOrigin.x,
            origin.y + mTileOrigin.y
        );

        this.mTile.renderController(g2, tilePos);
    }
}

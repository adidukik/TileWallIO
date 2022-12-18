package TWI.tileMgr;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Hashtable;

import TWI.TWI;
import TWI.TWIAnchorDot;
import TWI.TWIPatternFactory;
import TWI.geom.TWIDot;
import TWI.geom.TWIGeom;
import TWI.geom.TWIRectangle;
import TWI.pattern.TWIPattern;
import TWI.tile.TWITile;

public abstract class TWITileMgr {
    // constant
    protected static final double SNAP_RADIUS = 20.0f;
    public static final double CALCULATION_TOLERANCE = 5.0f;
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


    private TWIAnchorDot mSelectedAnchorDot = null;

    public TWIAnchorDot getSelectedAnchorDot() {
        return this.mSelectedAnchorDot;
    }

    public void setSelectedAnchorDot(TWIAnchorDot anchorDot) {
        this.mSelectedAnchorDot = anchorDot;
    }

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

    public void deselectAllPatterns() {
        ArrayList<TWIPattern> deselectPatterns = new ArrayList<>();

        for (TWIPattern pattern : this.mTile.getSelectedPatterns()) {
            pattern.unsetHighlightColor();
            deselectPatterns.add(pattern);
        }

        this.mTile.getSelectedPatterns().removeAll(deselectPatterns);
        this.mTile.getPatterns().addAll(deselectPatterns);
    }

    public void removeAnchorDots(TWIPattern pattern) {
        for (TWIAnchorDot anchorDot : pattern.getAnchorDots()) {
            this.removeOppositeAnchorDotIfAny(anchorDot);

            this.mAnchorDots.remove(anchorDot);
        }
    }

    public boolean removeOppositeAnchorDotIfAny(TWIAnchorDot anchorDot) {
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

    protected double calcDistanceFromPointToLine(Point2D pt, Line2D line) {
        // Calculate the distance from point to line using area method.
        double paralellogramArea = Math.abs(
            (line.getX2() - line.getX1()) * (line.getY1() - pt.getY()) -
            (line.getX1() - pt.getX()) * (line.getY2() - line.getY1())
        );

        double lineLength = calcLineLength(line);

        return paralellogramArea / lineLength;
    }

    // protected Point2D calcClosestPointOnLine(Point2D pt, Line2D line) {
    //     double lineLength = calcLineLength(line);

    // }

    // protected boolean isPointAboveLine(Point pt, Line2D line) {

    // }

    private double calcLineLength(Line2D line) {
        return Math.hypot(
            line.getX2() - line.getX1(),
            line.getY2() - line.getY1()
        );
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

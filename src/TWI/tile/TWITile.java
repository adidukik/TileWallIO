package TWI.tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import TWI.TWIClickable;
import TWI.TWIPattern;
import TWI.TWIRenderable;
import TWI.geom.TWIDot;
import TWI.geom.TWIGeom;

public abstract class TWITile implements TWIRenderable, TWIClickable {
    // constants
    protected static final Color COLOR_TILE_DEFAULT = Color.BLACK;

    // fields
    private ArrayList<TWIPattern> mPatterns = null;

    public ArrayList<TWIPattern> getPatterns() {
        return this.mPatterns;
    }

    private ArrayList<TWIDot> mAnchorDots = null;

    public ArrayList<TWIDot> getAnchorDots() {
        return this.mAnchorDots;
    }

    private boolean mIsEdgeVisible = false;

    public boolean getEdgeVisible() {
        return this.mIsEdgeVisible;
    }

    public void setmIsEdgeVisible(boolean b) {
        this.mIsEdgeVisible = true;
    }


    private boolean mIsDotVisible = true;

    public boolean getIsDotVisible() {
        return this.mIsDotVisible;
    }

    public void setIsDotVisible(boolean b) {
        this.mIsDotVisible = b;
    }

    // constructor
    public TWITile() {
        this.mPatterns = new ArrayList<>();
        this.mAnchorDots = new ArrayList<>();
        this.mIsEdgeVisible = true;
    }

    // methods
    public void addPattern(TWIPattern p) {
        this.mPatterns.add(p);
    }

    public void addAnchorDot(TWIDot dot) {
        this.mAnchorDots.add(dot);
    }

    // abstract methods
    public abstract TWIGeom getTileGeom();
    protected abstract Point getTilingPosition(int index);

    // interface methods
    @Override
    public void render(Graphics2D g2) {
        if (this.mIsEdgeVisible) {
            this.getTileGeom().render(g2);
        }

        if (this.mIsDotVisible) {
            for (TWIDot anchorDot : this.mAnchorDots) {
                anchorDot.render(g2);
            }
        }

        for (TWIPattern pattern : this.mPatterns) {
            pattern.render(g2);
        }
    }

    @Override
    public boolean isMousePointInside(Point pt) {
        return this.getTileGeom().getShape().contains(pt);
    }
}

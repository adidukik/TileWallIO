package TWI.tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import TWI.TWIClickable;
import TWI.TWIRenderable;
import TWI.geom.TWIGeom;
import TWI.pattern.TWIPattern;

public abstract class TWITile implements TWIRenderable, TWIClickable {
    // constants
    protected static final Color COLOR_TILE_DEFAULT = Color.BLACK;
    protected static final Color TILE_FILL_COLOR_DEFAULT =
        new Color(0, 0, 0, 0);

    // fields
    private ArrayList<TWIPattern> mPatterns = null;

    public ArrayList<TWIPattern> getPatterns() {
        return this.mPatterns;
    }


    private ArrayList<TWIPattern> mSelectedPatterns = null;

    public ArrayList<TWIPattern> getSelectedPatterns() {
        return this.mSelectedPatterns;
    }

    private boolean mIsEdgeVisible = false;

    public boolean getEdgeVisible() {
        return this.mIsEdgeVisible;
    }

    public void setmIsEdgeVisible(boolean b) {
        this.mIsEdgeVisible = true;
    }

    // constructor
    public TWITile() {
        this.mPatterns = new ArrayList<>();
        this.mSelectedPatterns = new ArrayList<>();
        this.mIsEdgeVisible = true;
    }

    // abstract methods
    public abstract TWIGeom getTileGeom();

    // render-related methods
    @Override
    public void render(Graphics2D g2, Point origin) {
        if (this.mIsEdgeVisible) {
            this.getTileGeom().render(g2, origin);
        }

        for (TWIPattern pattern : this.mPatterns) {
            // TODO: make the selected color revertible.
            pattern.setStrokeColor(Color.RED);
            pattern.render(g2, origin);
        }

        for (TWIPattern pattern : this.mSelectedPatterns) {
            // TODO: make the selected color revertible.
            pattern.setStrokeColor(Color.GREEN);
            pattern.render(g2, origin);
        }
    }
}

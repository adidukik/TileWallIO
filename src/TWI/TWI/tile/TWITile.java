package TWI.tile;

import java.util.ArrayList;

import TWI.TWIClickable;
import TWI.TWIPattern;
import TWI.TWIRenderable;
import TWI.geom.TWIGeom;
import TWI.geom.TWIPoint;

public abstract class TWITile implements TWIRenderable, TWIClickable {
    // fields
    private TWIGeom mTileShape = null;

    public TWIGeom getTileShape() {
        return this.mTileShape;
    }

    private ArrayList<TWIPattern> mPatterns = null;

    public ArrayList<TWIPattern> getPatterns() {
        return this.mPatterns;
    }

    private ArrayList<TWIPoint> mAnchorPoints = null;

    public ArrayList<TWIPoint> getAnchorPoints() {
        return this.mAnchorPoints;
    }

    private boolean isEdgeVisible = false;

    public boolean getEdgeVisible() {
        return this.isEdgeVisible;
    }

    public void setEdgeVisible(boolean b) {
        this.isEdgeVisible = true;
    }

    // constructor
    public TWITile(TWIGeom tileShape) {
        this.mTileShape = tileShape;
        this.mPatterns = new ArrayList<>();
        this.mAnchorPoints = new ArrayList<>();
    }

    // abstract methods
    protected abstract boolean isPointInside(TWIPoint p);
    protected abstract boolean isPointOnEdge(TWIPoint p);
    protected abstract void calcAnchorPoints(TWIPoint p);

    // renderable interface
    @Override
    public void render() {
        this.mTileShape.render();

        for (TWIPattern pattern : this.mPatterns) {
            pattern.render();
        }
    }

    @Override
    public void refresh() {
        // TODO Auto-generated method stub
    }
}

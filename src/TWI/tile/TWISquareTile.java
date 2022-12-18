package TWI.tile;

import java.awt.geom.Line2D;
import java.util.ArrayList;

import TWI.geom.TWIGeom;
import TWI.geom.TWIRectangle;
import TWI.tileImage.TWISquareTileImage;
import TWI.tileImage.TWITileImage;

public class TWISquareTile extends TWITile {
    // fields
    private TWIRectangle mRect = null;

    @Override
    public TWIGeom getTileGeom() {
        return this.mRect;
    }

    private TWISquareTileImage mTileImage = null;

    @Override
    public TWITileImage getTileImage() {
        return this.mTileImage;
    }

    private ArrayList<Line2D> mEdgeList = null;

    @Override
    public ArrayList<Line2D> getEdgeList() {
        return this.mEdgeList;
    }

    // constructor
    public TWISquareTile(Double w) {
        this.mRect = new TWIRectangle(0, 0, w, w);
        this.mRect.setStrokeColor(TWITile.COLOR_TILE_DEFAULT);
        this.mRect.setFillColor(TWITile.TILE_FILL_COLOR_DEFAULT);

        this.mTileImage = new TWISquareTileImage(this);

        this.mEdgeList = new ArrayList<>();

        this.mEdgeList.add(new Line2D.Double(0, 0, w, 0));
        this.mEdgeList.add(new Line2D.Double(w, 0, w, w));
        this.mEdgeList.add(new Line2D.Double(w, w, 0, w));
        this.mEdgeList.add(new Line2D.Double(0, w, 0, 0));
    }

}

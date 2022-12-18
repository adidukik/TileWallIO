package TWI.tile;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import TWI.geom.TWIGeom;
import TWI.geom.TWITriangle;
import TWI.tileImage.TWITileImage;
import TWI.tileImage.TWITriangleTileImage;

public class TWITriangleTile extends TWITile {
    // fields
    private TWITriangle mTriangle = null;

    @Override
    public TWIGeom getTileGeom() {
        return this.mTriangle;
    }


    private TWITriangleTileImage mTileImage = null;

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
    public TWITriangleTile(Double w) {
        // Regular hexagon vertices
        Point2D v1 = new Point2D.Double(0.5 * w,    0);
        Point2D v2 = new Point2D.Double(w,          Math.sqrt(3) * w / 2);
        Point2D v3 = new Point2D.Double(0,          Math.sqrt(3) * w / 2);

        this.mTriangle = new TWITriangle(
            v1.getX(), v1.getY(),
            v2.getX(), v2.getY(),
            v3.getX(), v3.getY()
        );
        this.mTriangle.setStrokeColor(TWITile.COLOR_TILE_DEFAULT);
        this.mTriangle.setFillColor(TWITile.TILE_FILL_COLOR_DEFAULT);

        this.mTileImage = new TWITriangleTileImage(this);

        this.mEdgeList = new ArrayList<>();
        this.mEdgeList.add(new Line2D.Double(v1, v2));
        this.mEdgeList.add(new Line2D.Double(v2, v3));
        this.mEdgeList.add(new Line2D.Double(v3, v1));
    }
}

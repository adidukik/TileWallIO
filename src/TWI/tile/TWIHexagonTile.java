package TWI.tile;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import TWI.geom.TWIGeom;
import TWI.geom.TWIHexagon;
import TWI.tileImage.TWIHexagonTileImage;
import TWI.tileImage.TWITileImage;

public class TWIHexagonTile extends TWITile {
    // fields
    private TWIHexagon mHexagon = null;

    @Override
    public TWIGeom getTileGeom() {
        return this.mHexagon;
    }


    private TWIHexagonTileImage mTileImage = null;

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
    public TWIHexagonTile(Double w) {
        // Regular hexagon vertices
        Point2D v1 = new Point2D.Double(0.5 * w,    0);
        Point2D v2 = new Point2D.Double(1.5 * w,    0);
        Point2D v3 = new Point2D.Double(2 * w,      Math.sqrt(3) * w / 2);
        Point2D v4 = new Point2D.Double(1.5 * w,    Math.sqrt(3) * w);
        Point2D v5 = new Point2D.Double(0.5 * w,    Math.sqrt(3) * w);
        Point2D v6 = new Point2D.Double(0,          Math.sqrt(3) * w / 2);

        this.mHexagon = new TWIHexagon(
            v1.getX(), v1.getY(),
            v2.getX(), v2.getY(),
            v3.getX(), v3.getY(),
            v4.getX(), v4.getY(),
            v5.getX(), v5.getY(),
            v6.getX(), v6.getY()
        );
        this.mHexagon.setStrokeColor(TWITile.COLOR_TILE_DEFAULT);
        this.mHexagon.setFillColor(TWITile.TILE_FILL_COLOR_DEFAULT);

        this.mTileImage = new TWIHexagonTileImage(this);

        this.mEdgeList = new ArrayList<>();
        this.mEdgeList.add(new Line2D.Double(v1, v2));
        this.mEdgeList.add(new Line2D.Double(v2, v3));
        this.mEdgeList.add(new Line2D.Double(v3, v4));
        this.mEdgeList.add(new Line2D.Double(v4, v5));
        this.mEdgeList.add(new Line2D.Double(v5, v6));
        this.mEdgeList.add(new Line2D.Double(v6, v1));
    }
}

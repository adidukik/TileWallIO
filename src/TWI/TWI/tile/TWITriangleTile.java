package TWI.tile;

import java.awt.geom.Rectangle2D;
import java.awt.Graphics;

import TWI.geom.TWIGeom;
import TWI.geom.TWIPoint;

public class TWITriangleTile extends TWITile {
    // constants
    private static final Double WIDTH_DAFAULT = 100.0;
    private static final Double HEIGHT_DEFAULT = 100.0;

    // fields
    private Rectangle2D.Double mBoundingBox = null;

    @Override
    public Rectangle2D.Double getBoundingBox() {
        return this.mBoundingBox;
    }

    // constructor
    public TWITriangleTile() {
        //DAMIR: drawPolygon()
        /* public abstract void drawPolygon(int[] xPoints,
               int[] yPoints,
               int nPoints)
        */

        // TWIGeom tileShape = new Rectangle2D.Double(
        //     WIDTH_DAFAULT,
        //     HEIGHT_DEFAULT,
        //     0,
        //     0
        // );
        super(tileShape);

        th
        this.mBoundingBox
    }


    @Override
    public boolean isClickOn(java.awt.geom.Point2D.Double pt) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected boolean isPointInside(TWIPoint p) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected boolean isPointOnEdge(TWIPoint p) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected void calcAnchorPoints(TWIPoint p) {
        // TODO Auto-generated method stub

    }

}

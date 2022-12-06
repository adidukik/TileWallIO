package TWI.geom;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class TWIDot extends TWIGeom {
    // constants
    private static final double RENDER_RADIUS = 2.0f;

    // fields
    private Point2D mPoint = null;

    public Point2D getPoint() {
        return this.mPoint;
    }

    public void setPoint(Point2D pt) {
    }

    private Rectangle2D.Double mBoundingBox = null;

    @Override
    public Rectangle2D.Double getBoundingBox() {
        return this.mBoundingBox;
    }

    // constructor
    public TWIDot(Point2D pt) {
        this.mPoint = pt;
    }

    public Shape getShape() {
        Ellipse2D circle = new Ellipse2D.Double(
            this.mPoint.getX() - TWIDot.RENDER_RADIUS,
            this.mPoint.getY() - TWIDot.RENDER_RADIUS,
            TWIDot.RENDER_RADIUS * 2,
            TWIDot.RENDER_RADIUS * 2
        );

        return circle;
    }
}

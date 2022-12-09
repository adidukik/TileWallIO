package TWI.geom;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

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

    private Ellipse2D mShape = null;

    // constructor
    public TWIDot(Point2D pt) {
        this.mPoint = pt;

        this.mShape = new Ellipse2D.Double(
            this.mPoint.getX() - TWIDot.RENDER_RADIUS,
            this.mPoint.getY() - TWIDot.RENDER_RADIUS,
            TWIDot.RENDER_RADIUS * 2,
            TWIDot.RENDER_RADIUS * 2
        );
    }

    @Override
    public Shape getShape() {
        return this.mShape;
    }

    @Override
    public void applyTransform(AffineTransform t) {
        // * only translation can be applied to Point.
        double tx = t.getTranslateX();
        double ty = t.getTranslateY();

        this.mPoint = new Point2D.Double(
            this.mPoint.getX() + tx,
            this.mPoint.getY() + ty
        );
    }
}

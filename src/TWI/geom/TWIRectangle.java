package TWI.geom;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class TWIRectangle extends TWIGeom {
    // fields
    private Rectangle2D mRectangle = null;

    // constructor
    public TWIRectangle(Rectangle2D rect) {
        this.mRectangle = rect;
    }

    // methods
    @Override
    public Shape getShape() {
        return this.mRectangle;
    }

    @Override
    public boolean isMousePointInside(Point pt) {
        return this.mRectangle.contains(pt);
    }

    @Override
    public void applyTransform(AffineTransform t) {
        this.mRectangle = (Rectangle2D) t.createTransformedShape(mRectangle);
    }
}

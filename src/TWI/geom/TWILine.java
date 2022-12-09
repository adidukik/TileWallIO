package TWI.geom;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class TWILine extends TWIGeom {
    // fields
    private Line2D mLine = null;

    // constructor
    public TWILine(Line2D line) {
        this.mLine = line;
    }

    // methods
    @Override
    public Shape getShape() {
        return this.mLine;
    }

    @Override
    public void applyTransform(AffineTransform t) {
        this.mLine = (Line2D) t.createTransformedShape(mLine);
    }
}

package TWI;

import java.awt.Point;
import java.awt.Shape;

public interface TWIClickable {
    // interface methods
    public Shape getBoundingBox();
    public boolean isMousePointInside(Point pt);
}

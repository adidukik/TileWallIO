package TWI;

import java.awt.Point;
import java.awt.geom.Rectangle2D;

public interface TWIClickable {
    // interface methods
    public Rectangle2D.Double getBoundingBox();
    public boolean isMouseOn(Point pt);
    public void updateOnHover(Point pt);
    public void updateOnHoverLost(Point pt);
    public void updateOnClick(Point pt);
    public void updateOnRelease(Point pt);
}

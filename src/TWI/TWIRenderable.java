package TWI;

import java.awt.Graphics2D;
import java.awt.Point;

public interface TWIRenderable {
    public abstract void render(Graphics2D g2, Point origin);
    // public abstract void refresh(Graphics2D g2);
}

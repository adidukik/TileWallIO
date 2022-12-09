package TWI;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import TWI.geom.TWIGeom;

public class TWIPattern implements TWIRenderable, TWIClickable {
    // fields
    private TWIGeom mGeom = null;

    public TWIGeom getGeom() {
        return this.mGeom;
    }

    // constructor
    public TWIPattern(TWIGeom geom) {
        this.mGeom = geom;
    }

    // interface methods
    @Override
    public Rectangle2D.Double getBoundingBox() {
        // TODO: Make clickable, or revise clicking mechanisms
        // return this.mGeom.getBoundingBox();
        return null;
    }

    @Override
    public boolean isMousePointInside(Point pt) {
        return false;
    }

    @Override
    public void render(Graphics2D g2, Point origin) {
        this.mGeom.render(g2, origin);
    }
}

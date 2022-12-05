package TWI;

import java.awt.Point;
import java.awt.geom.Rectangle2D;

import TWI.geom.TWIGeom;

public class TWIPattern implements TWIRenderable, TWIClickable {
    // fields
    private TWIGeom mGeom = null;

    public TWIGeom getGeom() {
        return this.mGeom;
    }

    // interface methods
    @Override
    public Rectangle2D.Double getBoundingBox() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isMouseOn(Point pt) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub

    }

    @Override
    public void refresh() {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateOnHover(Point pt) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateOnHoverLost(Point pt) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateOnClick(Point pt) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateOnRelease(Point pt) {
        // TODO Auto-generated method stub

    }

}

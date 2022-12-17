package TWI.pattern;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import TWI.TWIAnchorDot;
import TWI.geom.TWIGeom;

public interface TWIPattern extends TWIGeom {
    // constant
    public static final Color CONTROLLER_COLOR = new Color(0, 0, 0, 128);
    public static final Color HIGHLIGHT_COLOR = Color.RED;

    // interface methods
    public ArrayList<TWIAnchorDot> getAnchorDots();
    public void setHighlightColor();
    public void unsetHighlightColor();
    public void renderAnchorDots(Graphics2D g2, Point origin);
    public void renderController(Graphics2D g2, Point origin);
    public void update();
}

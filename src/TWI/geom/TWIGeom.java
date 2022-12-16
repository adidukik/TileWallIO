package TWI.geom;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Stroke;

import TWI.TWIRenderable;

public interface TWIGeom extends TWIRenderable {
    // constant
    public static final Color COLOR_DEFAULT = Color.RED;
    public static final Color COLOR_FILL_DEFAULT = new Color(0, 0, 0, 0);
    public static final Float STROKE_WIDTH_DEFAULT = 3.0f;
    public static final int STROKE_CAP_DEFAULT = BasicStroke.CAP_ROUND;
    public static final int STROKE_JOIN_DEFAULT = BasicStroke.JOIN_ROUND;
    public static final BasicStroke STROKE_DEFAULT = new BasicStroke(
        TWIGeom.STROKE_WIDTH_DEFAULT,
        TWIGeom.STROKE_CAP_DEFAULT,
        TWIGeom.STROKE_JOIN_DEFAULT
    );

    // abstract methods
    public Color getStrokeColor();
    public void setStrokeColor(Color color);

    public Color getFillColor();
    public void setFillColor(Color color);

    public Stroke getStroke();
    public void setStroke(Stroke stroke);

    public Rectangle getBounds();
}

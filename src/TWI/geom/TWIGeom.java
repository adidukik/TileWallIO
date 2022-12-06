package TWI.geom;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;

import TWI.TWIClickable;
import TWI.TWIRenderable;

public abstract class TWIGeom implements TWIRenderable, TWIClickable {
    // constant
    private static final Color COLOR_DEFAULT = Color.RED;
    private static final Color COLOR_FILL_DEFUALT =
        new Color(1.0f, 0.0f, 0.0f, 0.0f);
    private static final Float STROKE_WIDTH_DEFAULT = 3.0f;
    private static final int STROKE_CAP_DEFAULT = BasicStroke.CAP_ROUND;
    private static final int STROKE_JOIN_DEFAULT = BasicStroke.JOIN_ROUND;

    // abstract methods
    public abstract Shape getShape();

    // fields
    private boolean mIsVisible = false;

    public boolean getIsVisible() {
        return this.mIsVisible;
    }

    public void setIsVisible(boolean b) {
        this.mIsVisible = b;
    }

    private Color mStrokeColor = null;

    public Color getStrokeColor() {
        return this.mStrokeColor;
    }

    public void setStrokeColor(Color color) {
        this.mStrokeColor = color;
    }

    public Color mFillColor = null;

    public Color getFillColor() {
        return this.mStrokeColor;
    }

    public void setFillColor(Color color) {
        this.mStrokeColor = color;
    }


    private Stroke mStroke = null;

    public Stroke getStroke() {
        return this.mStroke;
    }

    public void setStroke(Stroke stroke) {
        this.mStroke = stroke;
    }

    // protected constructor
    protected TWIGeom() {
        this.mStrokeColor = TWIGeom.COLOR_DEFAULT;
        this.mFillColor = TWIGeom.COLOR_FILL_DEFUALT;

        this.mStroke = new BasicStroke(
            TWIGeom.STROKE_WIDTH_DEFAULT,
            TWIGeom.STROKE_CAP_DEFAULT,
            TWIGeom.STROKE_JOIN_DEFAULT
        );
    }

    // methods
    public boolean isMousePointInside(Point pt) {
        return this.getShape().contains(pt);
    }

    // interface methods
    @Override
    public void render(Graphics2D g2) {
        g2.setColor(this.mStrokeColor);
        g2.setStroke(this.mStroke);
        g2.draw(this.getShape());
        g2.setColor(this.mFillColor);
        g2.fill(this.getShape());
    }
}

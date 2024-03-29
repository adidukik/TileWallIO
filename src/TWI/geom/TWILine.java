package TWI.geom;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class TWILine extends Line2D.Double implements TWIGeom {
    // fields
    private Color mStrokeColor = null;

    @Override
    public Color getStrokeColor() {
        return this.mStrokeColor;
    }

    @Override
    public void setStrokeColor(Color color) {
        this.mStrokeColor = color;
    }


    private Color mFillColor = null;

    @Override
    public Color getFillColor() {
        return this.mFillColor;
    }

    @Override
    public void setFillColor(Color color) {
        this.mFillColor = color;
    }


    private Stroke mStroke = null;

    @Override
    public Stroke getStroke() {
        return this.mStroke;
    }

    @Override
    public void setStroke(Stroke stroke) {
        this.mStroke = stroke;
    }

    // constructor
    public TWILine(double x1, double y1, double x2, double y2) {
        super(x1, y1, x2, y2);

        this.mStrokeColor = TWIGeom.COLOR_DEFAULT;
        this.mFillColor = TWIGeom.COLOR_FILL_DEFAULT;
        this.mStroke = TWIGeom.STROKE_DEFAULT;
    }

    public TWILine(Point2D p1, Point2D p2) {
        super(p1, p2);

        this.mStrokeColor = TWIGeom.COLOR_DEFAULT;
        this.mFillColor = TWIGeom.COLOR_FILL_DEFAULT;
        this.mStroke = TWIGeom.STROKE_DEFAULT;
    }

    // interface methods
    @Override
    public void render(Graphics2D g2, Point origin) {
        g2.translate(origin.x, origin.y);

        g2.setColor(this.mStrokeColor);
        g2.setStroke(this.mStroke);
        g2.draw(this);

        g2.translate(-origin.x, -origin.y);
    }
}

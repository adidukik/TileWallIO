package TWI.geom;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;

public class TWITriangle implements TWIGeom {
    // fields
    private GeneralPath mPath = null;

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
    public TWITriangle(
        double x1, double y1,
        double x2, double y2,
        double x3, double y3
    ) {
        this.mPath = new GeneralPath();
        this.mPath.moveTo(x1, y1);
        this.mPath.lineTo(x2, y2);
        this.mPath.lineTo(x3, y3);
        this.mPath.lineTo(x1, y1);

        this.mStrokeColor = TWIGeom.COLOR_DEFAULT;
        this.mFillColor = TWIGeom.COLOR_FILL_DEFAULT;
        this.mStroke = TWIGeom.STROKE_DEFAULT;
    }

    // interface methods
    @Override
    public Rectangle getBounds() {
        return this.mPath.getBounds();
    }

    @Override
    public void render(Graphics2D g2, Point origin) {
        g2.translate(origin.x, origin.y);

        g2.setColor(this.mFillColor);
        g2.fill(this.mPath);
        g2.setColor(this.mStrokeColor);
        g2.setStroke(this.mStroke);
        g2.draw(this.mPath);

        g2.translate(-origin.x, -origin.y);
    }
}

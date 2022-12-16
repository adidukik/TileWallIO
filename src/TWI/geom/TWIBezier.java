package TWI.geom;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.CubicCurve2D;


public class TWIBezier extends CubicCurve2D.Double implements TWIGeom {
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
    public TWIBezier(
        double x1, double y1,
        double ctrlx1, double ctrly1,
        double ctrlx2, double ctrly2,
        double x2, double y2
    ) {
        super(
            x1, y1,
            ctrlx1, ctrly1,
            ctrlx2, ctrly2,
            x2, y2
        );

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
        g2.setColor(this.mFillColor);
        g2.fill(this);

        g2.translate(-origin.x, -origin.y);
    }
}

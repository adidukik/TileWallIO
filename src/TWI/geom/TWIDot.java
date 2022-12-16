package TWI.geom;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class TWIDot extends Point2D.Double implements TWIGeom {
    // constants
    private static final double RENDER_RADIUS = 2.0f;

    // fields
    private Ellipse2D mCircle = null;


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
    public TWIDot(double x, double y) {
        super(x, y);

        this.mStrokeColor = TWIGeom.COLOR_DEFAULT;
        this.mFillColor = TWIGeom.COLOR_FILL_DEFAULT;
        this.mStroke = TWIGeom.STROKE_DEFAULT;

        this.mCircle = new Ellipse2D.Double(
            x - TWIDot.RENDER_RADIUS,
            y - TWIDot.RENDER_RADIUS,
            TWIDot.RENDER_RADIUS * 2,
            TWIDot.RENDER_RADIUS * 2
        );
    }

    // methods
    @Override
    public void setLocation(double x, double y) {
        super.setLocation(x, y);
        this.updateRenderCircle();
    }

    @Override
    public void setLocation(Point2D p) {
        super.setLocation(p);
        this.updateRenderCircle();
    }

    private void updateRenderCircle() {
        // * This must be called at every location update.
        this.mCircle.setFrame(
            x - TWIDot.RENDER_RADIUS,
            y - TWIDot.RENDER_RADIUS,
            TWIDot.RENDER_RADIUS * 2,
            TWIDot.RENDER_RADIUS * 2
        );
    }

    // interface methods
    @Override
    public Rectangle getBounds() {
        return this.mCircle.getBounds();
    }

    @Override
    public void render(Graphics2D g2, Point origin) {
        g2.translate(origin.x, origin.y);

        g2.setColor(this.mStrokeColor);
        g2.setStroke(this.mStroke);
        g2.draw(this.mCircle);
        g2.setColor(this.mFillColor);
        g2.fill(this.mCircle);

        g2.translate(-origin.x, -origin.y);
    }
}

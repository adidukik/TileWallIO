package TWI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import TWI.geom.TWIRectangle;

public class TWISelectionBox implements TWIRenderable {
    // Constants
    public static final Color SELECTION_BOX_FILL_COLOR =
        new Color(0, 255, 255, 65);
    public static final Color SELECTION_BOX_STROKE_COLOR = Color.YELLOW;

    // fields
    private Point mAnchorPt = null;

    private TWIRectangle mRectangle = null;

    public TWIRectangle getRectangle() {
        return this.mRectangle;
    }

    // constructor
    public TWISelectionBox(Point pt) {
        this.mAnchorPt = pt;

        this.mRectangle = new TWIRectangle(
            new Rectangle2D.Double(pt.getX(), pt.getY(), 0f, 0f)
        );

        this.mRectangle.setFillColor(SELECTION_BOX_FILL_COLOR);
        this.mRectangle.setStrokeColor(SELECTION_BOX_STROKE_COLOR);
    }

    // methods
    public void update(Point pt) {
        Rectangle2D rect = (Rectangle2D) this.mRectangle.getShape();

        rect.setRect(this.mAnchorPt.x, this.mAnchorPt.y, 0, 0);
        rect.add(pt);
    }

    @Override
    public void render(Graphics2D g2, Point origin) {
        this.mRectangle.render(g2, origin);
    }
}
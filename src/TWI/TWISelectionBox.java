package TWI;

import java.awt.Color;
import java.awt.Point;

import TWI.geom.TWIRectangle;

public class TWISelectionBox extends TWIRectangle {
    // Constants
    public static final Color SELECTION_BOX_FILL_COLOR =
        new Color(0, 255, 255, 65);
    public static final Color SELECTION_BOX_STROKE_COLOR = Color.YELLOW;

    // fields
    private Point mAnchorPt = null;

    // constructor
    public TWISelectionBox(Point pt) {
        super(pt.x, pt.y, 0, 0);
        this.mAnchorPt = pt;
        this.setFillColor(SELECTION_BOX_FILL_COLOR);
        this.setStrokeColor(SELECTION_BOX_STROKE_COLOR);
    }

    // methods
    public void update(Point pt) {
        this.setRect(this.mAnchorPt.x, this.mAnchorPt.y, 0, 0);
        this.add(pt);
    }
}
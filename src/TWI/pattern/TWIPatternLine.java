package TWI.pattern;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import TWI.TWIAnchorDot;
import TWI.geom.TWILine;

public class TWIPatternLine extends TWILine implements TWIPattern {
    // fields
    private TWIAnchorDot mDot1 = null;

    private TWIAnchorDot mDot2 = null;

    // constructor
    public TWIPatternLine(double x1, double y1, double x2, double y2) {
        super(x1, y1, x2, y2);

        this.mDot1 = new TWIAnchorDot(
            x1, y1,
            TWIAnchorDot.SnappableFlag.SNAPPABLE,
            TWIAnchorDot.ClickableFlag.CLICKABLE
        );
        this.mDot2 = new TWIAnchorDot(
            x2, y2,
            TWIAnchorDot.SnappableFlag.SNAPPABLE,
            TWIAnchorDot.ClickableFlag.CLICKABLE
        );
    }

    public TWIPatternLine(Point2D p1, Point2D p2) {
        super(p1, p2);

        this.mDot1 = new TWIAnchorDot(
            p1.getX(), p1.getY(),
            TWIAnchorDot.SnappableFlag.SNAPPABLE,
            TWIAnchorDot.ClickableFlag.CLICKABLE
        );
        this.mDot2 = new TWIAnchorDot(
            p2.getX(), p2.getY(),
            TWIAnchorDot.SnappableFlag.SNAPPABLE,
            TWIAnchorDot.ClickableFlag.CLICKABLE
        );
    }

    // interface methods
    @Override
    public ArrayList<TWIAnchorDot> getAnchorDots() {
        ArrayList<TWIAnchorDot> anchorDots = new ArrayList<>();

        anchorDots.add(this.mDot1);
        anchorDots.add(this.mDot2);

        return anchorDots;
    }

    @Override
    public void update() {
        this.setLine(this.mDot1, this.mDot2);
    }

    @Override
    public void renderAnchorDots(Graphics2D g2, Point origin) {
        this.mDot1.render(g2, origin);
        this.mDot2.render(g2, origin);
    }

    @Override
    public void renderController(Graphics2D g2, Point origin) {}
}

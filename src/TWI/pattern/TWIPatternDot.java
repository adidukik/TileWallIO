package TWI.pattern;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import TWI.TWIAnchorDot;
import TWI.geom.TWIDot;

public class TWIPatternDot extends TWIDot implements TWIPattern {
    // fields
    private TWIAnchorDot mDot = null;

    public TWIPatternDot(double x, double y) {
        super(x, y);
        this.mDot = new TWIAnchorDot(
            x, y,
            TWIAnchorDot.SnappableFlag.SNAPPABLE,
            TWIAnchorDot.ClickableFlag.CLICKABLE
        );
    }

    @Override
    public ArrayList<TWIAnchorDot> getAnchorDots() {
        ArrayList<TWIAnchorDot> anchorDots = new ArrayList<>();

        anchorDots.add(this.mDot);

        return null;
    }

    @Override
    public void update() {
        this.setLocation(this.mDot);
    }

    @Override
    public void renderAnchorDots(Graphics2D g2, Point origin) {}

    @Override
    public void renderController(Graphics2D g2, Point origin) {}

}

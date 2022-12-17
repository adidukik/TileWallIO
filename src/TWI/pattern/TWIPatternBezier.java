package TWI.pattern;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import TWI.TWIAnchorDot;
import TWI.geom.TWIBezier;
import TWI.geom.TWILine;

public class TWIPatternBezier extends TWIBezier implements TWIPattern {
    // fields
    private TWIAnchorDot mDot1 = null;

    private TWIAnchorDot mCtrlDot1 = null;

    private TWIAnchorDot mCtrlDot2 = null;

    private TWIAnchorDot mDot2 = null;

    private TWILine mCtrlLine1 = null;

    private TWILine mCtrlLine2 = null;

    // constructor
    public TWIPatternBezier(
        double x1, double y1,
        double ctrlx1, double ctrly1,
        double ctrlx2, double ctrly2,
        double x2, double y2
    ) {
        super(x1, y1, ctrlx1, ctrly1, ctrlx2, ctrly2, x2, y2);

        this.mDot1 = new TWIAnchorDot(
            x1, y1,
            TWIAnchorDot.SnappableFlag.SNAPPABLE,
            TWIAnchorDot.ClickableFlag.CLICKABLE
        );
        this.mCtrlDot1 = new TWIAnchorDot(
            ctrlx1, ctrly1,
            TWIAnchorDot.SnappableFlag.NOT_SNAPPABLE,
            TWIAnchorDot.ClickableFlag.CLICKABLE
        );
        this.mCtrlDot2 = new TWIAnchorDot(
            ctrlx2, ctrly2,
            TWIAnchorDot.SnappableFlag.NOT_SNAPPABLE,
            TWIAnchorDot.ClickableFlag.CLICKABLE
        );
        this.mDot2 = new TWIAnchorDot(
            x2, y2,
            TWIAnchorDot.SnappableFlag.SNAPPABLE,
            TWIAnchorDot.ClickableFlag.CLICKABLE
        );

        this.mCtrlLine1 = new TWILine(this.mDot1, this.mCtrlDot1);
        this.mCtrlLine1.setStrokeColor(TWIPattern.CONTROLLER_COLOR);

        this.mCtrlLine2 = new TWILine(this.mDot2, this.mCtrlDot2);
        this.mCtrlLine2.setStrokeColor(TWIPattern.CONTROLLER_COLOR);
    }

    // interface methods
    @Override
    public ArrayList<TWIAnchorDot> getAnchorDots() {
        ArrayList<TWIAnchorDot> anchorDots = new ArrayList<>();

        anchorDots.add(this.mDot1);
        anchorDots.add(this.mCtrlDot1);
        anchorDots.add(this.mCtrlDot2);
        anchorDots.add(this.mDot2);

        return anchorDots;
    }

    @Override
    public void update() {
        this.setCurve(mDot1, mCtrlDot1, mCtrlDot2, mDot2);

        this.mCtrlLine1.setLine(this.mDot1, this.mCtrlDot1);
        this.mCtrlLine2.setLine(this.mDot2, this.mCtrlDot2);
    }

    @Override
    public void renderAnchorDots(Graphics2D g2, Point origin) {
        this.mDot1.render(g2, origin);
        this.mDot2.render(g2, origin);
    }

    @Override
    public void renderController(Graphics2D g2, Point origin) {
        this.mCtrlLine1.render(g2, origin);
        this.mCtrlDot1.render(g2, origin);

        this.mCtrlLine2.render(g2, origin);
        this.mCtrlDot2.render(g2, origin);
    }

}

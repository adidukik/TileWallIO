package TWI.pattern;

import java.util.ArrayList;

import TWI.TWIAnchorDot;
import TWI.geom.TWIBezier;

public class TWIPatternBezier extends TWIBezier implements TWIPattern {
    // fields
    private TWIAnchorDot mDot1 = null;

    private TWIAnchorDot mCtrlDot1 = null;

    private TWIAnchorDot mCtrlDot2 = null;

    private TWIAnchorDot mDot2 = null;

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
    }

    // interface methods
    @Override
    public ArrayList<TWIAnchorDot> getAnchorDots() {
        ArrayList<TWIAnchorDot> anchorDots = new ArrayList<>();

        anchorDots.add(this.mDot1);
        anchorDots.add(this.mCtrlDot1);
        anchorDots.add(this.mCtrlDot2);
        anchorDots.add(this.mDot2);

        return null;
    }

    @Override
    public void update() {
        this.setCurve(mDot1, mCtrlDot1, mCtrlDot2, mDot2);
    }

}

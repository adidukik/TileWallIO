package TWI;

import java.awt.Point;

import TWI.geom.TWIDot;

public class TWIAnchorDot extends TWIDot implements TWIClickable {
    // constants
    public enum SnappableFlag { SNAPPABLE, NOT_SNAPPABLE };
    public enum ClickableFlag { CLICKABLE, NOT_CLICKABLE}

    public static final boolean NOT_SNAPPABLE = false;
    public static final boolean CLICKABLE = true;
    public static final boolean NOT_CLICKABLE = false;

    private static final int CLICK_RADIUS = 5;

    // fields
    private boolean mIsSnappable = false;

    public boolean getIsSnappable() {
        return this.mIsSnappable;
    }

    private boolean mIsClickable = false;

    public boolean getIsClickable() {
        return this.mIsClickable;
    }

    // constructor
    public TWIAnchorDot(
        double x, double y,
        SnappableFlag snappableFlag, ClickableFlag clickableFlag
    ) {
        super(x, y);

        switch (snappableFlag) {
            case SNAPPABLE -> this.mIsSnappable = true;
            case NOT_SNAPPABLE -> this.mIsSnappable = false;
        }

        switch (clickableFlag) {
            case CLICKABLE -> this.mIsClickable = true;
            case NOT_CLICKABLE -> this.mIsClickable = false;
        }
    }

    // interface methods
    @Override
    public boolean isMousePointInside(Point pt) {
        return this.distance(pt) <= CLICK_RADIUS;
    }
}

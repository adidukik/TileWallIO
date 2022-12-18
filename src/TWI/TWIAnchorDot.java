package TWI;

import java.awt.Color;

import TWI.geom.TWIDot;

public class TWIAnchorDot extends TWIDot {
    // constants
    public enum SnappableFlag { SNAPPABLE, NOT_SNAPPABLE };
    public enum ClickableFlag { CLICKABLE, NOT_CLICKABLE}

    public static final Color DOT_NORMAL_COLOR = Color.RED;
    public static final Color DOT_NOT_SNAPPABLE_COLOR = Color.BLUE;
    public static final Color DOT_NOT_CLICKABLE_COLOR = Color.DARK_GRAY;
    public static final Color DOT_UNINTERACTABLE_COLOR = Color.BLACK;

    // fields
    private boolean mIsSnappable = false;

    public boolean getIsSnappable() {
        return this.mIsSnappable;
    }

    public SnappableFlag getSnappableFlag() {
        if (this.mIsSnappable) {
            return TWIAnchorDot.SnappableFlag.SNAPPABLE;
        } else {
            return TWIAnchorDot.SnappableFlag.NOT_SNAPPABLE;
        }
    }


    private boolean mIsClickable = false;

    public boolean getIsClickable() {
        return this.mIsClickable;
    }

    public ClickableFlag getClickableFlag() {
        if (this.mIsSnappable) {
            return TWIAnchorDot.ClickableFlag.CLICKABLE;
        } else {
            return TWIAnchorDot.ClickableFlag.NOT_CLICKABLE;
        }
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

        if (this.mIsSnappable && this.mIsClickable) {
            this.setStrokeColor(TWIAnchorDot.DOT_NORMAL_COLOR);
            this.setFillColor(TWIAnchorDot.DOT_NORMAL_COLOR);
        } else if (!this.mIsSnappable && this.mIsClickable) {
            this.setStrokeColor(TWIAnchorDot.DOT_NOT_SNAPPABLE_COLOR);
            this.setFillColor(TWIAnchorDot.DOT_NOT_SNAPPABLE_COLOR);
        } else if (this.mIsSnappable && !this.mIsClickable) {
            this.setStrokeColor(TWIAnchorDot.DOT_NOT_CLICKABLE_COLOR);
            this.setFillColor(TWIAnchorDot.DOT_NOT_CLICKABLE_COLOR);
        } else {
            System.out.println(
                "Warning: an uninteractiable anchor dot was created"
            );

            this.setStrokeColor(TWIAnchorDot.DOT_UNINTERACTABLE_COLOR);
            this.setFillColor(TWIAnchorDot.DOT_UNINTERACTABLE_COLOR);
        }
    }
}

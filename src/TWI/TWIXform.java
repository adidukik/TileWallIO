package TWI;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

public class TWIXform {
    // constants
    public static final Point PIVOT_PT = new Point(100, 100);
    public static final double MIN_START_ARM_LENGTH_FOR_SCALING = 100.0;

    // fields
    private AffineTransform mCurXformFromWorldToScreen = null;

    public AffineTransform getCurXformFromWorldToScreen() {
        return this.mCurXformFromWorldToScreen;
    }

    // inverse of the original matrix
    private AffineTransform mCurXformFromScreenToWorld = null;

    public AffineTransform getCurXformFromScreenToWorld() {
        return this.mCurXformFromScreenToWorld;
    }

    private Point mStartScreenPt = null;

    private AffineTransform mStartXformFromWorldToScreen = null;

    // constructor
    public TWIXform() {
        this.mCurXformFromWorldToScreen = new AffineTransform();
        this.mCurXformFromScreenToWorld = new AffineTransform();
        this.mStartXformFromWorldToScreen = new AffineTransform();
    }

    // methods
    public void setStartScreenPt(Point pt) {
        this.mStartScreenPt = pt;
        this.mStartXformFromWorldToScreen.setTransform(
            this.mCurXformFromWorldToScreen);
    }

    public Point calcPtFromWorldToScreen(Point2D.Double worldPt) {
        Point screenPt = new Point();
        this.mCurXformFromWorldToScreen.transform(worldPt, screenPt);
        return screenPt;
    }

    public Point2D.Double calcPtFromScreenToWorld(Point screenPt) {
        Point2D.Double worldPt = new Point2D.Double();
        this.mCurXformFromScreenToWorld.transform(screenPt, worldPt);
        return worldPt;
    }

    public void home() {
        this.mCurXformFromWorldToScreen.setToIdentity();
        this.updateCurXformFromScreenToWorld();
    }

    public boolean translateTo(Point pt) {
        if (this.mStartScreenPt == null) return false;

        this.mCurXformFromWorldToScreen.setTransform(
            mStartXformFromWorldToScreen
        );
        this.updateCurXformFromScreenToWorld();

        Point2D.Double worldPt0 =
            this.calcPtFromScreenToWorld(this.mStartScreenPt);
        Point2D.Double worldPt1 =
            this.calcPtFromScreenToWorld(pt);

        double dx = worldPt1.x - worldPt0.x;
        double dy = worldPt1.y - worldPt0.y;

        this.mCurXformFromWorldToScreen.translate(dx, dy);

        this.updateCurXformFromScreenToWorld();

        return true;
    }

    public boolean zoomRotateTo(Point pt) {
        if (this.mStartScreenPt == null) return false;

        this.mCurXformFromWorldToScreen.setTransform(
            mStartXformFromWorldToScreen
        );
        this.updateCurXformFromScreenToWorld();

        // get scaling ratio
        double d0 = TWIXform.PIVOT_PT.distance(this.mStartScreenPt);
        double d1 = TWIXform.PIVOT_PT.distance(pt);

        if (d0 < TWIXform.MIN_START_ARM_LENGTH_FOR_SCALING) return false;
        double scaling = d1 / d0;

        // get rotation angle
        double ang0 = StrictMath.atan2(
            this.mStartScreenPt.y - TWIXform.PIVOT_PT.y,
            this.mStartScreenPt.x - TWIXform.PIVOT_PT.x
        );
        double ang1 = StrictMath.atan2(
            pt.y - TWIXform.PIVOT_PT.y,
            pt.x - TWIXform.PIVOT_PT.x
        );
        double ang = ang1 - ang0;

        Point2D.Double worldPivotPt =
            this.calcPtFromScreenToWorld(TWIXform.PIVOT_PT);

        // 1) translate the canvas by worldPivotPt
        this.mCurXformFromWorldToScreen.translate(
            worldPivotPt.x,
            worldPivotPt.y
        );

        // 2) rotate the canvas by ang
        this.mCurXformFromWorldToScreen.rotate(ang);

        // 3) scale the canvas by s.
        this.mCurXformFromWorldToScreen.scale(scaling, scaling);

        // 4) translate the canvas by -worldPivotPt
        this.mCurXformFromWorldToScreen.translate(
            -worldPivotPt.x,
            -worldPivotPt.y
        );

        this.updateCurXformFromScreenToWorld();

        return true;
    }

    // call whenever mCurXformFromWorldToScreen changes to have its
    // corresponding mCurXformFromScreenToWorld
    public void updateCurXformFromScreenToWorld() {
        try {
            this.mCurXformFromScreenToWorld =
                this.mCurXformFromWorldToScreen.createInverse();
        } catch (NoninvertibleTransformException ex) {
            System.out.println("NoninvertibleTransformException");
        }

    }
}
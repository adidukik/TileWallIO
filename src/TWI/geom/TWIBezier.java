package TWI.geom;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.CubicCurve2D;

public class TWIBezier extends TWIGeom {
    // fields
    private CubicCurve2D mCurve;

    @Override
    public Shape getShape() {
        return this.mCurve;
    }


    private TWIDot mStartDot = null;

    public TWIDot getStartDot() {
        return this.mStartDot;
    }

    public void setStartDot(TWIDot dot) {
        this.mStartDot = dot;
        this.updateCurve();
    }


    private TWIDot mEndDot = null;

    public TWIDot getEndDot() {
        return this.mEndDot;
    }

    public void setEndDot(TWIDot dot) {
        this.mEndDot = dot;
        this.updateCurve();
    }


    private TWIDot mStartControlDot = null;

    public TWIDot getStartControlDot() {
        return this.mStartControlDot;
    }

    public void setStartControlDot(TWIDot dot) {
        this.mStartControlDot = dot;
        this.updateCurve();
    }


    private TWIDot mEndControlDot = null;

    public TWIDot getEndControlDot() {
        return this.mEndControlDot;
    }

    public void setEndControlDot(TWIDot dot) {
        this.mEndControlDot = dot;
        this.updateCurve();
    }


    // constructor
    public TWIBezier(CubicCurve2D curve) {
        this.mStartDot = new TWIDot(curve.getP1());
        this.mEndDot = new TWIDot(curve.getP2());
        this.mStartControlDot = new TWIDot(curve.getCtrlP1());
        this.mEndControlDot = new TWIDot(curve.getCtrlP2());

        this.mCurve = curve;
    }


    // methods
    private void updateCurve() {
        this.mCurve.setCurve(
            this.mStartDot.getPoint(),
            this.mEndDot.getPoint(),
            this.mStartControlDot.getPoint(),
            this.mEndControlDot.getPoint()
        );
    }

    @Override
    public void applyTransform(AffineTransform t) {
        this.mCurve = (CubicCurve2D) t.createTransformedShape(mCurve);
    }
}

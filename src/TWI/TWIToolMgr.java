package TWI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import TWI.geom.TWIEllipse;
import TWI.geom.TWIGeom;

public class TWIToolMgr {
    // constants
    public enum Tool {
        LINE,
        BEZIER
    }

    private static final Color COLOR_DEFAULT = Color.BLACK;
    private static final float STROKE_WIDTH_DEFAULT = 3.0f;
    private static final float STROKE_WIDTH_INCREMENT = 1.0f;

    private static final int STROKE_PREVIEW_OFFSET_X = 20;
    private static final int STROKE_PREVIEW_OFFSET_Y = 20;

    // fields
    private TWI mTWI = null;


    private Tool mCurTool = null;

    public Tool getCurTool() {
        return this.mCurTool;
    }

    public void setCurTool(Tool tool) {
        this.mCurTool = tool;
    }


    private Color mDrawColor = null;

    public Color getDrawColor() {
        return this.mDrawColor;
    }

    public void setDrawColor(Color color) {
        this.mDrawColor = color;

        this.updateStrokePreview();
    }


    private float mDrawStrokeWidth = Float.NaN;

    public float getDrawStrokeWidth() {
        return this.mDrawStrokeWidth;
    }


    private TWIEllipse mStrokePreview = null;


    // constructor
    public TWIToolMgr(TWI twi) {
        this.mTWI = twi;
        this.mCurTool = Tool.BEZIER;

        this.mDrawColor = TWIToolMgr.COLOR_DEFAULT;
        this.mDrawStrokeWidth = TWIToolMgr.STROKE_WIDTH_DEFAULT;

        this.mStrokePreview = new TWIEllipse(
            -this.mDrawStrokeWidth / 2,
            -this.mDrawStrokeWidth / 2,
            this.mDrawStrokeWidth,
            this.mDrawStrokeWidth
        );
        this.mStrokePreview.setStroke(
            new BasicStroke(
                0,
                TWIGeom.STROKE_CAP_DEFAULT,
                TWIGeom.STROKE_JOIN_DEFAULT
            )
        );

        this.mStrokePreview.setFillColor(this.mDrawColor);
    }


    // methods
    public boolean createGeom(Point pt) {
        TWIGeomMgr geomMgr = this.mTWI.getGeomMgr();

        switch (this.mCurTool) {
            case LINE -> {
                return geomMgr.createLine(pt);
            }
            case BEZIER -> {
                return geomMgr.createBezier(pt);
            }
            default -> {
                return false;
            }
        }
    }


    public void updateGeom(Point pt) {
        TWIGeomMgr geomMgr = this.mTWI.getGeomMgr();

        switch (this.mCurTool) {
            case LINE -> {
                geomMgr.updateLine(pt);
            }
            case BEZIER -> {
                geomMgr.updateBezier(pt);
            }
        }
    }


    public void renderGeom(Graphics2D g2, Point origin) {
        TWIGeomMgr geomMgr = this.mTWI.getGeomMgr();

        switch (this.mCurTool) {
            case LINE -> {
                geomMgr.renderLine(g2, origin);
            }
            case BEZIER -> {
                geomMgr.renderBezier(g2, origin);;
            }
        }
    }


    public boolean addGeom() {
        TWIGeomMgr geomMgr = this.mTWI.getGeomMgr();

        switch (this.mCurTool) {
            case LINE -> {
                return geomMgr.addLine();
            }
            case BEZIER -> {
                return geomMgr.addBezier();
            }
            default -> {
                return false;
            }
        }
    }


    public void switchToPrevTool() {
        int i = this.getToolIndex(this.mCurTool);

        Tool[] tools = TWIToolMgr.Tool.values();

        if (i == 0) {
            this.mCurTool = tools[tools.length - 1];
        } else {
            this.mCurTool = tools[i - 1];
        }
    }


    public void switchToNextTool() {
        int i = this.getToolIndex(this.mCurTool);

        Tool[] tools = TWIToolMgr.Tool.values();

        if (i == tools.length - 1) {
            this.mCurTool = tools[0];
        } else {
            this.mCurTool = tools[i + 1];
        }
    }

    private int getToolIndex(Tool tool) {
        return tool.ordinal();
    }


    public void increaseDrawStrokeWidth() {
        this.mDrawStrokeWidth += TWIToolMgr.STROKE_WIDTH_INCREMENT;

        this.updateStrokePreview();
    }


    public void decreaseDrawStrokeWidth() {
        if (this.mDrawStrokeWidth > TWIToolMgr.STROKE_WIDTH_INCREMENT) {
            this.mDrawStrokeWidth -= TWIToolMgr.STROKE_WIDTH_INCREMENT;
            this.updateStrokePreview();
        }
    }


    public void renderStrokePreview(Graphics2D g2) {
        int screenH = this.mTWI.getCanvas2d().getHeight();

        this.mStrokePreview.render(
            g2,
            new Point(
                TWIToolMgr.STROKE_PREVIEW_OFFSET_X,
                screenH - TWIToolMgr.STROKE_PREVIEW_OFFSET_Y
            )
        );
    }


    private void updateStrokePreview() {
        this.mStrokePreview.setFrame(
            -this.mDrawStrokeWidth / 2,
            -this.mDrawStrokeWidth / 2,
            this.mDrawStrokeWidth,
            this.mDrawStrokeWidth
        );

        this.mStrokePreview.setFillColor(this.mDrawColor);
    }
}

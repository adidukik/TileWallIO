package TWI;

import java.awt.Graphics2D;
import java.awt.Point;

public class TWIToolMgr {
    // constants
    public enum Tool {
        LINE,
        BEZIER
    }

    // fields
    private TWI mTwi = null;


    private Tool mCurTool = null;

    public Tool getCurTool() {
        return this.mCurTool;
    }

    public void setCurTool(Tool tool) {
        this.mCurTool = tool;
    }

    // constructor
    public TWIToolMgr(TWI twi) {
        this.mTwi = twi;
        this.mCurTool = Tool.BEZIER;
    }

    // methods
    public boolean createGeom(Point pt) {
        TWIGeomMgr geomMgr = this.mTwi.getGeomMgr();

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
        TWIGeomMgr geomMgr = this.mTwi.getGeomMgr();

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
        TWIGeomMgr geomMgr = this.mTwi.getGeomMgr();

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
        TWIGeomMgr geomMgr = this.mTwi.getGeomMgr();

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
}

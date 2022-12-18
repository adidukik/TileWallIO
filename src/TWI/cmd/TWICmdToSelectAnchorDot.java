package TWI.cmd;

import java.awt.Point;

import TWI.TWI;
import TWI.TWIAnchorDot;
import TWI.tileMgr.TWITileMgr;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToSelectAnchorDot extends XLoggableCmd {
    // fields
    private Point mPt = null;

    // private constructor
    private TWICmdToSelectAnchorDot(XApp app, Point pt) {
        super(app);

        this.mPt = pt;
    }

    public static boolean execute(XApp app, Point pt) {
        TWICmdToSelectAnchorDot cmd = new TWICmdToSelectAnchorDot(app, pt);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;
        TWITileMgr tileMgr = twi.getTileMgr();

        assert(tileMgr.getSelectedAnchorDot() == null);

        Point tilePt = new Point(
            this.mPt.x - tileMgr.getTileOrigin().x,
            this.mPt.y - tileMgr.getTileOrigin().y
        );

        for (TWIAnchorDot anchorDot : tileMgr.getAnchorDots()) {
            if (
                anchorDot.getIsClickable() &&
                anchorDot.distance(tilePt) < TWITileMgr.CALCULATION_TOLERANCE
            ) {
                tileMgr.setSelectedAnchorDot(anchorDot);
                return true;
            }
        }

        return false;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();

        sb.append(this.getClass().getSimpleName());
        sb.append("\t");
        sb.append(this.mPt);

        return sb.toString();
    }
}
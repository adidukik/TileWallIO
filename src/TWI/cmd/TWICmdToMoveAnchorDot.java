package TWI.cmd;

import java.awt.Point;

import TWI.TWI;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToMoveAnchorDot extends XLoggableCmd {
    // fields
    private Point mPt = null;
    private boolean mWasMoved = false;

    // private constructor
    private TWICmdToMoveAnchorDot(XApp app, Point pt) {
        super(app);

        this.mPt = pt;
    }

    public static boolean execute(XApp app, Point pt) {
        TWICmdToMoveAnchorDot cmd = new TWICmdToMoveAnchorDot(app, pt);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;

        this.mWasMoved = twi.getTileMgr().moveSelectedAnchorDotTo(this.mPt);

        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();

        sb.append(this.getClass().getSimpleName());
        sb.append("\t");
        sb.append(this.mPt);
        sb.append("\t");
        sb.append(this.mWasMoved);

        return sb.toString();
    }
}
package TWI.cmd;

import java.awt.Point;

import TWI.TWI;
import TWI.geom.TWIDot;
import TWI.tile.TWITileMgr;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToAddDot extends XLoggableCmd {
    // fields
    private Point mPt = null;
    private boolean mIsAdded = false;

    // private constructor
    private TWICmdToAddDot(XApp app, Point pt) {
        super(app);

        this.mPt = pt;
    }

    public static boolean execute(XApp app, Point pt) {
        TWICmdToAddDot cmd = new TWICmdToAddDot(app, pt);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;

        TWITileMgr tileMgr = twi.getTileMgr();

        TWIDot dot = new TWIDot(this.mPt.x, this.mPt.y);
        TWIDot dotAdded = tileMgr.addDot(dot);

        this.mIsAdded = (dotAdded != null);

        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();

        sb.append(this.getClass().getSimpleName());
        sb.append("\t");
        sb.append(this.mPt);
        sb.append("\t");
        sb.append(this.mIsAdded);

        return sb.toString();
    }
}
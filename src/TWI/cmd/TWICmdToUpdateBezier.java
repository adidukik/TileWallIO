package TWI.cmd;

// import TWI.TWI;
import java.awt.Point;

import TWI.TWI;
import TWI.geom.TWIGeomMgr;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToUpdateBezier extends XLoggableCmd {
    // fields
    private Point mPt = null;

    // private constructor
    private TWICmdToUpdateBezier(XApp app, Point pt) {
        super(app);

        this.mPt = pt;
    }

    public static boolean execute(XApp app, Point pt) {
        TWICmdToUpdateBezier cmd = new TWICmdToUpdateBezier(app, pt);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;

        TWIGeomMgr geomMgr = twi.getGeomMgr();

        geomMgr.updateBezier(mPt);

        return true;
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
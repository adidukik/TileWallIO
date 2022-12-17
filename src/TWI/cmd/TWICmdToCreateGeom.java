package TWI.cmd;

// import TWI.TWI;
import java.awt.Point;

import TWI.TWI;
import TWI.TWIToolMgr;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToCreateGeom extends XLoggableCmd {
    // fields
    private Point mPt = null;

    // private constructor
    private TWICmdToCreateGeom(XApp app, Point pt) {
        super(app);

        this.mPt = pt;
    }

    public static boolean execute(XApp app, Point pt) {
        TWICmdToCreateGeom cmd = new TWICmdToCreateGeom(app, pt);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;

        TWIToolMgr toolMgr = twi.getToolMgr();

        return toolMgr.createGeom(mPt);
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
package TWI.cmd;

import TWI.TWI;
import TWI.TWIToolMgr;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToRegisterCurve extends XLoggableCmd {
    // private constructor
    private TWICmdToRegisterCurve(XApp app) {
        super(app);
    }

    public static boolean execute(XApp app) {
        TWICmdToRegisterCurve cmd = new TWICmdToRegisterCurve(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;

        TWIToolMgr toolMgr = twi.getToolMgr();

        toolMgr.addGeom();

        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();

        sb.append(this.getClass().getSimpleName());

        return sb.toString();
    }
}
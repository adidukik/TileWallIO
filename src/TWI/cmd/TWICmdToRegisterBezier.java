package TWI.cmd;

import TWI.TWI;
import TWI.geom.TWIGeomMgr;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToRegisterBezier extends XLoggableCmd {
    // private constructor
    private TWICmdToRegisterBezier(XApp app) {
        super(app);
    }

    public static boolean execute(XApp app) {
        TWICmdToRegisterBezier cmd = new TWICmdToRegisterBezier(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;

        TWIGeomMgr geomMgr = twi.getGeomMgr();

        geomMgr.addBezier();

        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();

        sb.append(this.getClass().getSimpleName());

        return sb.toString();
    }
}
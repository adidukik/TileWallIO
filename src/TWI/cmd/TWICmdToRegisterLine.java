package TWI.cmd;

import TWI.TWI;
import TWI.geom.TWIGeomMgr;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToRegisterLine extends XLoggableCmd {
    // private constructor
    private TWICmdToRegisterLine(XApp app) {
        super(app);
    }

    public static boolean execute(XApp app) {
        TWICmdToRegisterLine cmd = new TWICmdToRegisterLine(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;

        TWIGeomMgr geomMgr = twi.getGeomMgr();

        geomMgr.addLine();

        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();

        sb.append(this.getClass().getSimpleName());
        // sb.append("\t");

        return sb.toString();
    }
}
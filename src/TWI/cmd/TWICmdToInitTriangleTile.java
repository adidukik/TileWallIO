package TWI.cmd;

import TWI.TWI;
import TWI.tileMgr.TWITriangleTileMgr;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToInitTriangleTile extends XLoggableCmd {
    // private constructor
    private TWICmdToInitTriangleTile(XApp app) {
        super(app);
    }

    public static boolean execute(XApp app) {
        TWICmdToInitTriangleTile cmd = new TWICmdToInitTriangleTile(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;

        TWITriangleTileMgr tileMgr = new TWITriangleTileMgr(twi);

        twi.setTileMgr(tileMgr);
        twi.initComponents();

        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();

        sb.append(this.getClass().getSimpleName());

        return sb.toString();
    }
}
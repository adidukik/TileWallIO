package TWI.cmd;

import TWI.TWI;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToRemoveSelectedPattern extends XLoggableCmd {
    // private constructor
    private TWICmdToRemoveSelectedPattern(XApp app) {
        super(app);
    }

    public static boolean execute(XApp app) {
        TWICmdToRemoveSelectedPattern cmd = new TWICmdToRemoveSelectedPattern(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;

        twi.getTileMgr().removeSelectedPatterns();

        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();

        sb.append(this.getClass().getSimpleName());

        return sb.toString();
    }
}
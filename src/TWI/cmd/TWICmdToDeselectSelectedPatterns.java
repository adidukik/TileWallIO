package TWI.cmd;

import TWI.TWI;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToDeselectSelectedPatterns extends XLoggableCmd {
    // fields

    // private constructor
    private TWICmdToDeselectSelectedPatterns(XApp app) {
        super(app);
    }

    public static boolean execute(XApp app) {
        TWICmdToDeselectSelectedPatterns cmd = new TWICmdToDeselectSelectedPatterns(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;

        twi.getTileMgr().deselectAllPatterns();

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
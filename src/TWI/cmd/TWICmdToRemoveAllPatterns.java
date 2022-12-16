package TWI.cmd;

import TWI.TWI;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToRemoveAllPatterns extends XLoggableCmd {
    // fields

    // private constructor
    private TWICmdToRemoveAllPatterns(XApp app) {
        super(app);
    }

    public static boolean execute(XApp app) {
        TWICmdToRemoveAllPatterns cmd = new TWICmdToRemoveAllPatterns(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;

        twi.getTileMgr().removeAllPattern();

        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();

        sb.append(this.getClass().getSimpleName());

        return sb.toString();
    }
}
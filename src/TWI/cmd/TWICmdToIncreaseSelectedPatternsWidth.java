package TWI.cmd;

import TWI.TWI;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToIncreaseSelectedPatternsWidth extends XLoggableCmd {
    // private constructor
    private TWICmdToIncreaseSelectedPatternsWidth(XApp app) {
        super(app);
    }

    public static boolean execute(XApp app) {
        TWICmdToIncreaseSelectedPatternsWidth cmd
            = new TWICmdToIncreaseSelectedPatternsWidth(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;

        twi.getTileMgr().increaseSelectedPatternsStrokeWidth();

        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();

        sb.append(this.getClass().getSimpleName());

        return sb.toString();
    }
}
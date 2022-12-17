package TWI.cmd;

import TWI.TWI;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToDecreaseSelectedPatternsWidth extends XLoggableCmd {
    // private constructor
    private TWICmdToDecreaseSelectedPatternsWidth(XApp app) {
        super(app);
    }

    public static boolean execute(XApp app) {
        TWICmdToDecreaseSelectedPatternsWidth cmd
            = new TWICmdToDecreaseSelectedPatternsWidth(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;

        twi.getTileMgr().decreaseSelectedPatternsStrokeWidth();

        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();

        sb.append(this.getClass().getSimpleName());

        return sb.toString();
    }
}
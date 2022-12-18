package TWI.cmd;

import TWI.TWI;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToIncreaseDrawStrokeWidth extends XLoggableCmd {
    // fields

    // private constructor
    private TWICmdToIncreaseDrawStrokeWidth(XApp app) {
        super(app);
    }

    public static boolean execute(XApp app) {
        TWICmdToIncreaseDrawStrokeWidth cmd =
            new TWICmdToIncreaseDrawStrokeWidth(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;

        twi.getToolMgr().increaseDrawStrokeWidth();

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
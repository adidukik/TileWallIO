package TWI.cmd;

import TWI.TWI;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToDecreaseDrawStrokeWidth extends XLoggableCmd {
    // fields

    // private constructor
    private TWICmdToDecreaseDrawStrokeWidth(XApp app) {
        super(app);
    }

    public static boolean execute(XApp app) {
        TWICmdToDecreaseDrawStrokeWidth cmd =
            new TWICmdToDecreaseDrawStrokeWidth(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;

        twi.getGeomMgr().decreaseDrawStrokeWidth();

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
package TWI.cmd;

import TWI.TWI;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToTurnSnapOnOff extends XLoggableCmd {
    // fields
    boolean mIsSnapOn = false;

    // private constructor
    private TWICmdToTurnSnapOnOff(XApp app, boolean isSnapOn) {
        super(app);

        this.mIsSnapOn = isSnapOn;
    }

    public static boolean execute(XApp app, boolean isSnapOn) {
        TWICmdToTurnSnapOnOff cmd = new TWICmdToTurnSnapOnOff(app, isSnapOn);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;

        twi.getTileMgr().setIsSnapOn(mIsSnapOn);

        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();

        sb.append(this.getClass().getSimpleName());
        sb.append("\t");
        sb.append(this.mIsSnapOn);

        return sb.toString();
    }
}
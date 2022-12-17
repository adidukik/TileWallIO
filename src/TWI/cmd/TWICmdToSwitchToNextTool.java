package TWI.cmd;

import TWI.TWI;
import TWI.TWIToolMgr;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToSwitchToNextTool extends XLoggableCmd {
    // fields
    private TWIToolMgr.Tool mOldTool = null;
    private TWIToolMgr.Tool mNewTool = null;

    // private constructor
    private TWICmdToSwitchToNextTool(XApp app) {
        super(app);
    }

    public static boolean execute(XApp app) {
        TWICmdToSwitchToNextTool cmd = new TWICmdToSwitchToNextTool(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;

        this.mOldTool = twi.getToolMgr().getCurTool();
        twi.getToolMgr().switchToNextTool();
        this.mNewTool = twi.getToolMgr().getCurTool();

        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();

        sb.append(this.getClass().getSimpleName());
        sb.append("\t");
        sb.append(this.mOldTool);
        sb.append("\t");
        sb.append(this.mNewTool);

        return sb.toString();
    }
}
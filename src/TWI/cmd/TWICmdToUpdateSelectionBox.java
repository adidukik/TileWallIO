package TWI.cmd;

import java.awt.Point;

import TWI.scenario.TWISelectScenario;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToUpdateSelectionBox extends XLoggableCmd {
    // fields
    private Point mPt = null;

    // private constructor
    private TWICmdToUpdateSelectionBox(XApp app, Point pt) {
        super(app);

        this.mPt = pt;
    }

    public static boolean execute(XApp app, Point pt) {
        TWICmdToUpdateSelectionBox cmd = new TWICmdToUpdateSelectionBox(app, pt);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWISelectScenario.getSingleton().getSelectionBox().update(this.mPt);

        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();

        sb.append(this.getClass().getSimpleName());
        sb.append("\t");
        sb.append(this.mPt);

        return sb.toString();
    }
}
package TWI.cmd;

import java.awt.Point;

import TWI.TWISelectionBox;
import TWI.scenario.TWISelectScenario;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToCreateSelectionBox extends XLoggableCmd {
    // fields
    private Point mPt = null;

    // private constructor
    private TWICmdToCreateSelectionBox(XApp app, Point pt) {
        super(app);

        this.mPt = pt;
    }

    public static boolean execute(XApp app, Point pt) {
        TWICmdToCreateSelectionBox cmd = new TWICmdToCreateSelectionBox(app, pt);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWISelectionBox selectionBox = new TWISelectionBox(this.mPt);
        TWISelectScenario.getSingleton().setSelectionBox(selectionBox);

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
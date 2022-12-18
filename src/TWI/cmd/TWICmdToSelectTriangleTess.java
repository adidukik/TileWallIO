package TWI.cmd;

import TWI.TWI;
import java.awt.Point;

import TWI.start.TWIButtons;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToSelectTriangleTess extends XLoggableCmd {
    // fields
    private Point mPt = null;

    // private constructor
    private TWICmdToSelectTriangleTess(XApp app, Point pt) {
        super(app);
        
        this.mPt = pt;
    }

    public static boolean execute(XApp app, Point pt) {
        
        TWICmdToSelectTriangleTess cmd = new TWICmdToSelectTriangleTess(app, pt);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;

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
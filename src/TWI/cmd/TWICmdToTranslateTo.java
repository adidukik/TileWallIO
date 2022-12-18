package TWI.cmd;

import java.awt.Point;

import TWI.TWI;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToTranslateTo extends XLoggableCmd {
    // fields
    private Point mPt = null;

    // private constructor
    private TWICmdToTranslateTo(XApp app, Point pt) {
        super(app);
        this.mPt = pt;
    }

    public static boolean execute(XApp app, Point pt) {
        TWICmdToTranslateTo cmd = new TWICmdToTranslateTo(app, pt);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;

        twi.getXform().translateTo(this.mPt);

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
package TWI.cmd;

import java.awt.Point;

import TWI.TWI;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToZoomNRotateTo extends XLoggableCmd {
    // fields
    private Point mPt = null;

    // private constructor
    private TWICmdToZoomNRotateTo(XApp app, Point pt) {
        super(app);
        this.mPt = pt;
    }

    public static boolean execute(XApp app, Point pt) {
        TWICmdToZoomNRotateTo cmd = new TWICmdToZoomNRotateTo(app, pt);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;

        twi.getXform().zoomRotateTo(this.mPt);

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
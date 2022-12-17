package TWI.cmd;

import java.awt.Color;

import TWI.TWI;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToChangeDrawColor extends XLoggableCmd {
    // fields
    private Color mColor = null;

    // private constructor
    private TWICmdToChangeDrawColor(XApp app, Color color) {
        super(app);

        this.mColor = color;
    }

    public static boolean execute(XApp app, Color color) {
        TWICmdToChangeDrawColor cmd = new TWICmdToChangeDrawColor(app, color);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;

        twi.getGeomMgr().setDrawColor(this.mColor);

        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();

        sb.append(this.getClass().getSimpleName());
        sb.append("\t");
        sb.append(this.mColor);

        return sb.toString();
    }
}
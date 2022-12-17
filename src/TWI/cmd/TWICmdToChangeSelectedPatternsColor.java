package TWI.cmd;

import java.awt.Color;

import TWI.TWI;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToChangeSelectedPatternsColor extends XLoggableCmd {
    // fields
    private Color mColor = null;

    // private constructor
    private TWICmdToChangeSelectedPatternsColor(XApp app, Color color) {
        super(app);

        this.mColor = color;
    }

    public static boolean execute(XApp app, Color color) {
        TWICmdToChangeSelectedPatternsColor cmd
            = new TWICmdToChangeSelectedPatternsColor(app, color);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;

        twi.getTileMgr().setSelectedPatternsColor(this.mColor);

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
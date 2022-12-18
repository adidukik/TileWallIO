package TWI.cmd;

import TWI.TWI;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToExportImage extends XLoggableCmd {
    // fields

    // private constructor
    private TWICmdToExportImage(XApp app) {
        super(app);
    }

    public static boolean execute(XApp app) {
        TWICmdToExportImage cmd = new TWICmdToExportImage(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;
        twi.getCanvas2d().exportImage("Paint");
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
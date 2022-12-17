package TWI.cmd;

// import TWI.TWI;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToSelectTess extends XLoggableCmd {
    // fields

    // private constructor
    private TWICmdToSelectTess(XApp app) {
        super(app);
    }

    public static boolean execute(XApp app) {
        TWICmdToSelectTess cmd = new TWICmdToSelectTess(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        // TWI twi = (TWI) this.mApp;
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
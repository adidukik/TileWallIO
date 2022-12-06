package TWI.cmd;

// import TWI.TWI;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToDoSomething extends XLoggableCmd {
    // fields

    // private constructor
    private TWICmdToDoSomething(XApp app) {
        super(app);
    }

    public static boolean execute(XApp app) {
        TWICmdToDoSomething cmd = new TWICmdToDoSomething(app);
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
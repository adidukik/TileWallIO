package TWI.cmd;

import TWI.TWI;
import TWI.tileMgr.TWISquareTileMgr;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToInitSquareTile extends XLoggableCmd {
    // private constructor
    private TWICmdToInitSquareTile(XApp app) {
        super(app);
    }

    public static boolean execute(XApp app) {
        TWICmdToInitSquareTile cmd = new TWICmdToInitSquareTile(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;

        TWISquareTileMgr tileMgr = new TWISquareTileMgr(twi);

        twi.setTileMgr(tileMgr);

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
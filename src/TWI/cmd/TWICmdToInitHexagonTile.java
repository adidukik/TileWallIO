package TWI.cmd;

import TWI.TWI;
import TWI.tileMgr.TWIHexagonTileMgr;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToInitHexagonTile extends XLoggableCmd {
    // private constructor
    private TWICmdToInitHexagonTile(XApp app) {
        super(app);
    }

    public static boolean execute(XApp app) {
        TWICmdToInitHexagonTile cmd = new TWICmdToInitHexagonTile(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;

        TWIHexagonTileMgr tileMgr = new TWIHexagonTileMgr(twi);

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
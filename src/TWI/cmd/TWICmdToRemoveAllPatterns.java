package TWI.cmd;

import TWI.TWI;
import TWI.pattern.TWIPattern;
import TWI.tile.TWITile;
import TWI.tileMgr.TWITileMgr;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToRemoveAllPatterns extends XLoggableCmd {
    // private constructor
    private TWICmdToRemoveAllPatterns(XApp app) {
        super(app);
    }

    public static boolean execute(XApp app) {
        TWICmdToRemoveAllPatterns cmd = new TWICmdToRemoveAllPatterns(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;
        TWITileMgr tileMgr = twi.getTileMgr();
        TWITile tile = tileMgr.getTile();

        for (TWIPattern pattern : tile.getPatterns()) {
            tileMgr.removeAnchorDots(pattern);
        }

        for (
            TWIPattern pattern :
                twi.getTileMgr().getTile().getSelectedPatterns()
        ) {
            tileMgr.removeAnchorDots(pattern);
        }

        tile.getPatterns().clear();
        tile.getSelectedPatterns().clear();

        twi.getPreviewMgr().updateTileImage();

        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();

        sb.append(this.getClass().getSimpleName());

        return sb.toString();
    }
}
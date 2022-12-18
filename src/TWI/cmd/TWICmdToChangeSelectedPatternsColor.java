package TWI.cmd;

import java.awt.Color;

import TWI.TWI;
import TWI.pattern.TWIPattern;
import TWI.tile.TWITile;
import TWI.tileMgr.TWITileMgr;
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
        TWITileMgr tileMgr = twi.getTileMgr();
        TWITile tile = tileMgr.getTile();

        for (TWIPattern pattern : tile.getSelectedPatterns()) {
            pattern.unsetHighlightColor();
            pattern.setFillColor(this.mColor);
            pattern.setStrokeColor(this.mColor);
        }

        tileMgr.deselectAllPatterns();

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
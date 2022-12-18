package TWI.cmd;

import java.awt.BasicStroke;

import TWI.TWI;
import TWI.TWIToolMgr;
import TWI.pattern.TWIPattern;
import TWI.tile.TWITile;
import TWI.tileMgr.TWITileMgr;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToDecreaseSelectedPatternsWidth extends XLoggableCmd {
    // private constructor
    private TWICmdToDecreaseSelectedPatternsWidth(XApp app) {
        super(app);
    }

    public static boolean execute(XApp app) {
        TWICmdToDecreaseSelectedPatternsWidth cmd
            = new TWICmdToDecreaseSelectedPatternsWidth(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;
        TWITileMgr tileMgr = twi.getTileMgr();
        TWITile tile = tileMgr.getTile();

        for (TWIPattern pattern : tile.getSelectedPatterns()) {
            BasicStroke stroke = (BasicStroke) pattern.getStroke();

            if (stroke.getLineWidth() > TWIToolMgr.STROKE_WIDTH_INCREMENT) {
                pattern.setStroke(
                    new BasicStroke(
                        stroke.getLineWidth() -
                            TWIToolMgr.STROKE_WIDTH_INCREMENT,
                        stroke.getEndCap(),
                        stroke.getLineJoin()
                    )
                );
            }
        }

        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();

        sb.append(this.getClass().getSimpleName());

        return sb.toString();
    }
}
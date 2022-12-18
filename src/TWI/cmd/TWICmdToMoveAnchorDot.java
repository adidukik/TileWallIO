package TWI.cmd;

import java.awt.Point;

import TWI.TWI;
import TWI.TWIAnchorDot;
import TWI.pattern.TWIPattern;
import TWI.tile.TWITile;
import TWI.tileMgr.TWITileMgr;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToMoveAnchorDot extends XLoggableCmd {
    // fields
    private Point mPt = null;

    // private constructor
    private TWICmdToMoveAnchorDot(XApp app, Point pt) {
        super(app);

        this.mPt = pt;
    }

    public static boolean execute(XApp app, Point pt) {
        TWICmdToMoveAnchorDot cmd = new TWICmdToMoveAnchorDot(app, pt);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;
        TWITileMgr tileMgr = twi.getTileMgr();
        TWITile tile = tileMgr.getTile();

        TWIAnchorDot anchorDot = tileMgr.getSelectedAnchorDot();

        if (anchorDot.getIsSnappable()) {
            TWIAnchorDot newAnchorDot = new TWIAnchorDot(
                this.mPt.x - tileMgr.getTileOrigin().x,
                this.mPt.y - tileMgr.getTileOrigin().y,
                anchorDot.getSnappableFlag(),
                anchorDot.getClickableFlag()
            );

            newAnchorDot = tileMgr.calcValidDot(newAnchorDot);

            if (newAnchorDot == null) return false;

            tileMgr.removeOppositeAnchorDotIfAny(anchorDot);

            anchorDot.setLocation(
                newAnchorDot.getX(),
                newAnchorDot.getY()
            );

        } else {
            tileMgr.removeOppositeAnchorDotIfAny(anchorDot);

            anchorDot.setLocation(
                this.mPt.x - tileMgr.getTileOrigin().x,
                this.mPt.y - tileMgr.getTileOrigin().y
            );
        }

        for (TWIPattern pattern : tile.getPatterns()) {
            pattern.update();
        }

        twi.getPreviewMgr().updateTileImage();

        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();

        sb.append(this.getClass().getSimpleName());
        sb.append("\t");
        sb.append(this.mPt);

        return sb.toString();
    }
}
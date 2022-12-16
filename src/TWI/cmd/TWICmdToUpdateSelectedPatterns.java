package TWI.cmd;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import TWI.TWI;
import TWI.TWISelectionBox;
import TWI.pattern.TWIPattern;
import TWI.scenario.TWISelectScenario;
import TWI.tile.TWITileMgr;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToUpdateSelectedPatterns extends XLoggableCmd {
    // private constructor
    private TWICmdToUpdateSelectedPatterns(XApp app) {
        super(app);
    }

    public static boolean execute(XApp app) {
        TWICmdToUpdateSelectedPatterns cmd = new TWICmdToUpdateSelectedPatterns(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWI twi = (TWI) this.mApp;
        TWITileMgr tileMgr = twi.getTileMgr();
        ArrayList<TWIPattern> patterns = tileMgr.getTile().getPatterns();
        ArrayList<TWIPattern> selectedPatterns = tileMgr.getTile()
            .getSelectedPatterns();
        ArrayList<TWIPattern> newlySelectedPatterns = new ArrayList<>();

        for (TWIPattern pattern : patterns) {
            if (this.isPatternInsideSelectionBox(pattern)) {
                newlySelectedPatterns.add(pattern);
            } else {
                System.out.println("(1): Wrong");
            }
        }

        patterns.removeAll(newlySelectedPatterns);
        selectedPatterns.addAll(newlySelectedPatterns);

        return true;
    }

    private boolean isPatternInsideSelectionBox(TWIPattern pattern) {
        TWI twi = (TWI) this.mApp;
        TWISelectionBox selectionBox =
            TWISelectScenario.getSingleton().getSelectionBox();

        assert(selectionBox != null);

        Rectangle2D worldBox = new Rectangle2D.Double(
            selectionBox.getX() - twi.getTileMgr().getTileOrigin().getX(),
            selectionBox.getY() - twi.getTileMgr().getTileOrigin().getY(),
            selectionBox.getWidth(),
            selectionBox.getHeight()
        );

        return worldBox.intersects(pattern.getBounds());
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();

        sb.append(this.getClass().getSimpleName());

        return sb.toString();
    }
}
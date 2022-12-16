package TWI.cmd;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import TWI.TWI;
import TWI.TWIPattern;
import TWI.TWISelectionBox;
import TWI.scenario.TWISelectScenario;
import TWI.tile.TWITileMgr;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToUpdateSelectedPatterns extends XLoggableCmd {
    // fields

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
        // JSI jsi = (JSI) this.mApp;
        // JSIPtCurveMgr ptCurveManager = jsi.getPtCurveMgr();
        // ArrayList<JSIPtCurve> ptCurves = ptCurveManager.getPtCurves();
        // ArrayList<JSIPtCurve> selectedPtCurves =
        //     ptCurveManager.getSelectedPtCurves();
        // ArrayList<JSIPtCurve> newlySelectedPtCurves = new ArrayList<>();

        // for (JSIPtCurve ptCurve : ptCurves) {
        //     if (isPtCurveInsideSelectionBox(ptCurve)) {
        //         newlySelectedPtCurves.add(ptCurve);
        //     }
        // }

        // ptCurves.removeAll(newlySelectedPtCurves);
        // selectedPtCurves.addAll(newlySelectedPtCurves);

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
        TWISelectionBox selectionBox =
            TWISelectScenario.getSingleton().getSelectionBox();

        assert(selectionBox != null);

        Rectangle2D rect = (Rectangle2D) selectionBox.getRectangle().getShape();

        return rect.intersects(pattern.getGeom().getShape().getBounds2D());
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();

        sb.append(this.getClass().getSimpleName());
        // sb.append("\t");

        return sb.toString();
    }
}
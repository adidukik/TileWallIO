package TWI;

import javax.swing.JFrame;

import TWI.geom.TWIGeomMgr;
import TWI.preview.TWIPreviewMgr;
import TWI.scenario.TWIScenarioMgr;
import TWI.tile.TWISquareTileMgr;
import TWI.tile.TWITileMgr;

import TWI.start.TWIButtons;
import x.XApp;
import x.XLogMgr;

public class TWI extends XApp {
    // fields
    private boolean mIsDebug = false;

    public boolean getIsDebug() {
        return this.mIsDebug;
    }

    private JFrame mFrame = null;

    private TWICanvas2D mCanvas2D = null;

    public TWICanvas2D getCanvas2d() {
        return this.mCanvas2D;
    }

    private TWIXform mXform = null;

    public TWIXform getXform() {
        return this.mXform;
    }

    private TWIEventListener mEventListener = null;

    public TWIEventListener getEventListener() {
        return this.mEventListener;
    }

    private TWIGeomMgr mGeomMgr = null;

    public TWIGeomMgr getGeomMgr() {
        return this.mGeomMgr;
    }

    private TWITileMgr mTileMgr = null;

    public TWITileMgr getTileMgr() {
        return this.mTileMgr;
    }

    //private TWIStartMgr mStartMgr = null;

    // public TWIStartMgr getStartMgr() {
    //     return this.mStartMgr;
    // }

    private TWIButtons mButtons = null;
    
    public TWIButtons getButtons() {
        return this.mButtons;
    }

    private TWIPreviewMgr mPreviewMgr = null;

    public TWIPreviewMgr getPreviewMgr() {
        return this.mPreviewMgr;
    }

    private TWIScenarioMgr mScenarioMgr = null;

    @Override
    public TWIScenarioMgr getScenarioMgr() {
        return this.mScenarioMgr;
    }

    private XLogMgr mLogMgr = null;

    @Override
    public XLogMgr getLogMgr() {
        return this.mLogMgr;
    }

    // constructor
    public TWI(boolean isDebug) {
        this.mIsDebug = isDebug;

        // create components
        // 1) frame
        this.mFrame = new JFrame("TileWallIO");
        // 2) canvas
        this.mCanvas2D = new TWICanvas2D(this);
        // 3) other components
        this.mXform = new TWIXform();
        // 4) event listenters
        this.mEventListener = new TWIEventListener(this);
        // 5) managers
        // TODO: Add scene for selecting tile type
        this.mGeomMgr = new TWIGeomMgr(this);
        this.mTileMgr = new TWISquareTileMgr(this);
        this.mPreviewMgr = new TWIPreviewMgr(this);
        //this.mStartMgr = new TWIStartMgr(this);

        this.mScenarioMgr = new TWIScenarioMgr(this);
        this.mLogMgr = new XLogMgr();
        this.getLogMgr().setPrintOn(this.mIsDebug);

        this.mButtons = new TWIButtons();

        // connect event listeners
        this.mCanvas2D.addMouseListener(this.mEventListener);
        this.mCanvas2D.addMouseMotionListener(this.mEventListener);
        this.mCanvas2D.setFocusable(true);
        this.mCanvas2D.addKeyListener(mEventListener);

        // build and show visual compoenents
        this.mFrame.add(this.mCanvas2D);
        this.mFrame.setSize(800, 600);
        this.mFrame.setVisible(true);
        this.mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // methods
    public static void main(String[] args) {
        new TWI(true);
    }
}

package TWI;

import java.io.IOException;

import javax.swing.JFrame;

import TWI.start.TWIStartMenu;
import TWI.tileMgr.TWITileMgr;
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

    private TWIStartMenu mStartMenu = null;

    public TWIStartMenu getStartMenu() {
        return this.mStartMenu;
    }

    private TWIToolMgr mToolMgr = null;

    public TWIToolMgr getToolMgr() {
        return this.mToolMgr;
    }

    private TWIGeomMgr mGeomMgr = null;

    public TWIGeomMgr getGeomMgr() {
        return this.mGeomMgr;
    }

    private TWITileMgr mTileMgr = null;

    public TWITileMgr getTileMgr() {
        return this.mTileMgr;
    }

    public void setTileMgr(TWITileMgr tileMgr) {
        assert(this.mTileMgr == null);

        this.mTileMgr = tileMgr;
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
    public TWI(boolean isDebug) throws IOException {
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
        this.mStartMenu = new TWIStartMenu();

        this.mScenarioMgr = new TWIScenarioMgr(this);
        this.mLogMgr = new XLogMgr();
        this.getLogMgr().setPrintOn(this.mIsDebug);

        // connect event listeners
        this.mCanvas2D.addMouseListener(this.mEventListener);
        this.mCanvas2D.addMouseMotionListener(this.mEventListener);
        this.mCanvas2D.setFocusable(true);
        this.mCanvas2D.addKeyListener(mEventListener);

        // build and show visual compoenents
        this.mFrame.add(this.mCanvas2D);
        this.mFrame.setSize(1080, 720);
        this.mFrame.setVisible(true);
        this.mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initComponents() {
        this.mToolMgr = new TWIToolMgr(this);
        this.mGeomMgr = new TWIGeomMgr(this);
        this.mPreviewMgr = new TWIPreviewMgr(this);
    }

    // methods
    public static void main(String[] args) {
        try {
            new TWI(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

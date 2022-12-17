package TWI.scenario;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import TWI.TWI;
import TWI.cmd.TWICmdToMoveAnchorDot;
import TWI.cmd.TWICmdToSelectAnchorDot;
import x.XApp;
import x.XCmdToChangeScene;
import x.XScenario;

public class TWIEditScenario extends XScenario {
    // singleton pattern
    private static TWIEditScenario mSingleton = null;

    public static TWIEditScenario createSingleton(XApp app) {
        assert (TWIEditScenario.mSingleton == null);

        TWIEditScenario.mSingleton = new TWIEditScenario(app);
        return TWIEditScenario.mSingleton;
    }

    public static TWIEditScenario getSingleton() {
        assert (TWIEditScenario.mSingleton != null);

        return TWIEditScenario.mSingleton;
    }

    private TWIEditScenario(XApp app) {
        super(app);
    }

    @Override
    protected void addScenes() {
        this.addScene(TWIEditScenario.EditReadyScene.createSingleton(this));
        this.addScene(TWIEditScenario.AnchorDotDragScene.createSingleton(this));
    }

    // scenes
    public static class EditReadyScene extends TWIScene {
        // singleton pattern
        private static EditReadyScene mSingleton = null;

        public static EditReadyScene createSingleton(XScenario scenario) {
            assert(EditReadyScene.mSingleton == null);

            EditReadyScene.mSingleton = new EditReadyScene(scenario);
            return EditReadyScene.mSingleton;
        }

        public static EditReadyScene getSingleton() {
            assert(EditReadyScene.mSingleton != null);

            return EditReadyScene.mSingleton;
        }

        private EditReadyScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            TWI twi = (TWI) this.mScenario.getApp();

            if (TWICmdToSelectAnchorDot.execute(twi, e.getPoint())) {
                XCmdToChangeScene.execute(
                    twi,
                    TWIEditScenario.AnchorDotDragScene.getSingleton(),
                    this.mReturnScene
                );
            }
        }

        @Override
        public void handleMouseDrag(MouseEvent e) {}

        @Override
        public void handleMouseRelease(MouseEvent e) {}

        @Override
        public void handleKeyDown(KeyEvent e) {}

        @Override
        public void handleKeyUp(KeyEvent e) {
            TWI twi = (TWI) this.getScenario().getApp();

            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_E -> {
                    XCmdToChangeScene.execute(
                        twi,
                        this.mReturnScene,
                        null
                    );
                }
            }
        }

        @Override
        public void updateSupportObjects(Graphics2D g2) {}

        @Override
        public void renderWorldObjects(Graphics2D g2) {
            TWI twi = (TWI) this.mScenario.getApp();
            twi.getPreviewMgr().render(g2, new Point(0, 0));
        }

        @Override
        public void renderScreenObjects(Graphics2D g2) {
            TWI twi = (TWI) this.mScenario.getApp();
            twi.getTileMgr().renderTileEditor(g2, new Point(0, 0));
            twi.getTileMgr().renderAdditionalAnchorDots(g2, new Point(0, 0));
        }

        @Override
        public void getReady() {
            TWI twi = (TWI) this.mScenario.getApp();
            twi.getTileMgr().setIsSnapOn(true);
        }

        @Override
        public void wrapUp() {
            TWI twi = (TWI) this.mScenario.getApp();
            twi.getTileMgr().setIsSnapOn(false);
        }
    }

    public static class AnchorDotDragScene extends TWIScene {
        // singleton pattern
        private static AnchorDotDragScene mSingleton = null;

        public static AnchorDotDragScene createSingleton(XScenario scenario) {
            assert(AnchorDotDragScene.mSingleton == null);

            AnchorDotDragScene.mSingleton = new AnchorDotDragScene(scenario);
            return AnchorDotDragScene.mSingleton;
        }

        public static AnchorDotDragScene getSingleton() {
            assert(AnchorDotDragScene.mSingleton != null);

            return AnchorDotDragScene.mSingleton;
        }

        private AnchorDotDragScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {}

        @Override
        public void handleMouseDrag(MouseEvent e) {
            TWI twi = (TWI) this.mScenario.getApp();

            TWICmdToMoveAnchorDot.execute(twi, e.getPoint());
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            TWI twi = (TWI) this.mScenario.getApp();

            XCmdToChangeScene.execute(
                twi,
                TWIEditScenario.EditReadyScene.getSingleton(),
                this.mReturnScene
            );
        }

        @Override
        public void handleKeyDown(KeyEvent e) {}

        @Override
        public void handleKeyUp(KeyEvent e) {
            TWI twi = (TWI) this.getScenario().getApp();

            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_E -> {
                    XCmdToChangeScene.execute(
                        twi,
                        this.mReturnScene,
                        null
                    );
                }
            }
        }

        @Override
        public void updateSupportObjects(Graphics2D g2) {}

        @Override
        public void renderWorldObjects(Graphics2D g2) {
            TWI twi = (TWI) this.mScenario.getApp();
            twi.getPreviewMgr().render(g2, new Point(0, 0));
        }

        @Override
        public void renderScreenObjects(Graphics2D g2) {
            TWI twi = (TWI) this.mScenario.getApp();
            twi.getTileMgr().renderTileEditor(g2, new Point(0, 0));
            twi.getTileMgr().renderAdditionalAnchorDots(g2, new Point(0, 0));
        }

        @Override
        public void getReady() {
            TWI twi = (TWI) this.mScenario.getApp();
            twi.getTileMgr().setIsSnapOn(true);
        }

        @Override
        public void wrapUp() {
            TWI twi = (TWI) this.mScenario.getApp();
            twi.getTileMgr().setIsSnapOn(false);
        }
    }
}
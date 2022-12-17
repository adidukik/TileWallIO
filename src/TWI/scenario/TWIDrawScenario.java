package TWI.scenario;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import TWI.TWI;
import TWI.cmd.TWICmdToRegisterCurve;
import TWI.cmd.TWICmdToUpdateGeom;
import x.XApp;
import x.XCmdToChangeScene;
import x.XScenario;

public class TWIDrawScenario extends XScenario {
    // singleton pattern
    private static TWIDrawScenario mSingleton = null;

    public static TWIDrawScenario createSingleton(XApp app) {
        assert (TWIDrawScenario.mSingleton == null);

        TWIDrawScenario.mSingleton = new TWIDrawScenario(app);
        return TWIDrawScenario.mSingleton;
    }

    public static TWIDrawScenario getSingleton() {
        assert (TWIDrawScenario.mSingleton != null);

        return TWIDrawScenario.mSingleton;
    }

    private TWIDrawScenario(XApp app) {
        super(app);
    }

    @Override
    protected void addScenes() {
        this.addScene(TWIDrawScenario.DrawLineReadyScene.createSingleton(this));
        this.addScene(TWIDrawScenario.DrawLineScene.createSingleton(this));
    }

    // scenes
    public static class DrawLineReadyScene extends TWIScene {
        // singleton pattern
        private static DrawLineReadyScene mSingleton = null;

        public static DrawLineReadyScene createSingleton(XScenario scenario) {
            assert(DrawLineReadyScene.mSingleton == null);

            DrawLineReadyScene.mSingleton = new DrawLineReadyScene(scenario);
            return DrawLineReadyScene.mSingleton;
        }

        public static DrawLineReadyScene getSingleton() {
            assert(DrawLineReadyScene.mSingleton != null);

            return DrawLineReadyScene.mSingleton;
        }

        private DrawLineReadyScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {}

        @Override
        public void handleMouseDrag(MouseEvent e) {
            TWI twi = (TWI) this.getScenario().getApp();

            // TWICmdToUpdateLine.execute(twi, e.getPoint());
            TWICmdToUpdateGeom.execute(twi, e.getPoint());

            XCmdToChangeScene.execute(
                twi,
                TWIDrawScenario.DrawLineScene.getSingleton(),
                this.mReturnScene
            );
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            TWI twi = (TWI) this.getScenario().getApp();

            XCmdToChangeScene.execute(
                twi,
                this.mReturnScene,
                null
            );
        }

        @Override
        public void handleKeyDown(KeyEvent e) {}

        @Override
        public void handleKeyUp(KeyEvent e) {}

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

            twi.getToolMgr().renderGeom(g2, new Point(0, 0));
        }

        @Override
        public void getReady() {}

        @Override
        public void wrapUp() {}
    }

    public static class DrawLineScene extends TWIScene {
        // singleton pattern
        private static DrawLineScene mSingleton = null;

        public static DrawLineScene createSingleton(XScenario scenario) {
            assert(DrawLineScene.mSingleton == null);

            DrawLineScene.mSingleton = new DrawLineScene(scenario);
            return DrawLineScene.mSingleton;
        }

        public static DrawLineScene getSingleton() {
            assert(DrawLineScene.mSingleton != null);

            return DrawLineScene.mSingleton;
        }

        private DrawLineScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {}

        @Override
        public void handleMouseDrag(MouseEvent e) {
            TWI twi = (TWI) this.getScenario().getApp();

            // TWICmdToUpdateLine.execute(twi, e.getPoint());
            TWICmdToUpdateGeom.execute(twi, e.getPoint());
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            TWI twi = (TWI) this.getScenario().getApp();

            // TWICmdToRegisterLine.execute(twi);
            TWICmdToRegisterCurve.execute(twi);

            XCmdToChangeScene.execute(
                twi,
                this.mReturnScene,
                null
            );
        }

        @Override
        public void handleKeyDown(KeyEvent e) {}

        @Override
        public void handleKeyUp(KeyEvent e) {}

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

            twi.getToolMgr().renderGeom(g2, new Point(0, 0));
        }

        @Override
        public void getReady() {}

        @Override
        public void wrapUp() {}
    }
}
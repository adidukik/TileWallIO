package TWI.scenario;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import TWI.TWI;
import TWI.TWIScene;
import x.XApp;
import x.XCmdToChangeScene;
import x.XScenario;

public class TWIDefaultScenario extends XScenario {
    // singleton pattern
    private static TWIDefaultScenario mSingleton = null;

    public static TWIDefaultScenario createSingleton(XApp app) {
        assert (TWIDefaultScenario.mSingleton == null);

        TWIDefaultScenario.mSingleton = new TWIDefaultScenario(app);
        return TWIDefaultScenario.mSingleton;
    }

    public static TWIDefaultScenario getSingleton() {
        assert (TWIDefaultScenario.mSingleton != null);

        return TWIDefaultScenario.mSingleton;
    }

    private TWIDefaultScenario(XApp app) {
        super(app);
    }

    @Override
    protected void addScenes() {
        this.addScene(TWIDefaultScenario.ReadyScene.createSingleton(this));
    }

    // scenes
    public static class ReadyScene extends TWIScene {
        // singleton pattern
        private static ReadyScene mSingleton = null;

        public static ReadyScene createSingleton(XScenario scenario) {
            assert(ReadyScene.mSingleton == null);

            ReadyScene.mSingleton = new ReadyScene(scenario);
            return ReadyScene.mSingleton;
        }

        public static ReadyScene getSingleton() {
            assert(ReadyScene.mSingleton != null);

            return ReadyScene.mSingleton;
        }

        private ReadyScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            TWI twi = (TWI) this.getScenario().getApp();
            Point pt = e.getPoint();

            // TODO: CmdToCreateCurve

            XCmdToChangeScene.execute(
                twi,
                TWIDrawScenario.DrawScene.getSingleton(),
                this
            );
        }

        @Override
        public void handleMouseDrag(MouseEvent e) {}

        @Override
        public void handleMouseRelease(MouseEvent e) {}

        @Override
        public void handleKeyDown(KeyEvent e) {
            // TWI twi = (TWI) this.mScenario.getApp();

            // int code = e.getKeyCode();
            // switch (code) {
            //     case KeyEvent.XXX -> {}
            // }
        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            // TWI twi = (TWI) this.mScenario.getApp();

            // int code = e.getKeyCode();
            // switch (code) {
            //     case KeyEvent.XXX -> {}
            // }
        }

        @Override
        public void updateSupportObjects(Graphics2D g2) {}

        @Override
        public void renderWorldObjects(Graphics2D g2) {}

        @Override
        public void renderScreenObjects(Graphics2D g2) {}

        @Override
        public void getReady() {}

        @Override
        public void wrapUp() {}
    }
}
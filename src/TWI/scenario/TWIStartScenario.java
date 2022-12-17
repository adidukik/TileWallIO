package TWI.scenario;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import TWI.TWI;
import x.XApp;
import x.XScenario;

public class TWIStartScenario extends XScenario {
    // singleton pattern
    private static TWIStartScenario mSingleton = null;

    public static TWIStartScenario createSingleton(XApp app) {
        assert (TWIStartScenario.mSingleton == null);

        TWIStartScenario.mSingleton = new TWIStartScenario(app);
        return TWIStartScenario.mSingleton;
    }

    public static TWIStartScenario getSingleton() {
        assert (TWIStartScenario.mSingleton != null);

        return TWIStartScenario.mSingleton;
    }

    private TWIStartScenario(XApp app) {
        super(app);
    }

    @Override
    protected void addScenes() {
        this.addScene(TWIStartScenario.StartScene.createSingleton(this));
    }

    // scenes
    public static class StartScene extends TWIScene {
        // singleton pattern
        private static StartScene mSingleton = null;

        public static StartScene createSingleton(XScenario scenario) {
            assert(StartScene.mSingleton == null);

            StartScene.mSingleton = new StartScene(scenario);
            return StartScene.mSingleton;
        }

        public static StartScene getSingleton() {
            assert(StartScene.mSingleton != null);

            return StartScene.mSingleton;
        }

        private StartScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {}

        @Override
        public void handleMouseDrag(MouseEvent e) {}

        @Override
        public void handleMouseRelease(MouseEvent e) {}

        @Override
        public void handleKeyDown(KeyEvent e) {}

        @Override
        public void handleKeyUp(KeyEvent e) {}

        @Override
        public void updateSupportObjects(Graphics2D g2) {}

        @Override
        public void renderWorldObjects(Graphics2D g2) {}

        @Override
        public void renderScreenObjects(Graphics2D g2) {
            TWI twi = (TWI) this.mScenario.getApp();
            
            twi.getButtons().render(g2, new Point(400, 400));


        }

        @Override
        public void getReady() {}

        @Override
        public void wrapUp() {}
    }
}
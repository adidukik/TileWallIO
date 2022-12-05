package scenario;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import TWI.TWIScene;
import x.XApp;
import x.XScenario;

public class TWIEmptyScenario extends XScenario {
    // singleton pattern
    private static TWIEmptyScenario mSingleton = null;

    public static TWIEmptyScenario createSingleton(XApp app) {
        assert (TWIEmptyScenario.mSingleton == null);

        TWIEmptyScenario.mSingleton = new TWIEmptyScenario(app);
        return TWIEmptyScenario.mSingleton;
    }

    public static TWIEmptyScenario getSingleton() {
        assert (TWIEmptyScenario.mSingleton != null);

        return TWIEmptyScenario.mSingleton;
    }

    private TWIEmptyScenario(XApp app) {
        super(app);
    }

    @Override
    protected void addScenes() {
        this.addScene(TWIEmptyScenario.EmptyScene.createSingleton(this));
    }

    // scenes
    public static class EmptyScene extends TWIScene {
        // singleton pattern
        private static EmptyScene mSingleton = null;

        public static EmptyScene createSingleton(XScenario scenario) {
            assert(EmptyScene.mSingleton == null);

            EmptyScene.mSingleton = new EmptyScene(scenario);
            return EmptyScene.mSingleton;
        }

        public static EmptyScene getSingleton() {
            assert(EmptyScene.mSingleton != null);

            return EmptyScene.mSingleton;
        }

        private EmptyScene(XScenario scenario) {
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
        public void renderScreenObjects(Graphics2D g2) {}

        @Override
        public void getReady() {}

        @Override
        public void wrapUp() {}
    }
}
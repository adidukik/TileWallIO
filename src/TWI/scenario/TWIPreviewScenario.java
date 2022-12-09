package TWI.scenario;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import TWI.TWI;
import x.XApp;
import x.XCmdToChangeScene;
import x.XScenario;

public class TWIPreviewScenario extends XScenario {
    // singleton pattern
    private static TWIPreviewScenario mSingleton = null;

    public static TWIPreviewScenario createSingleton(XApp app) {
        assert (TWIPreviewScenario.mSingleton == null);

        TWIPreviewScenario.mSingleton = new TWIPreviewScenario(app);
        return TWIPreviewScenario.mSingleton;
    }

    public static TWIPreviewScenario getSingleton() {
        assert (TWIPreviewScenario.mSingleton != null);

        return TWIPreviewScenario.mSingleton;
    }

    private TWIPreviewScenario(XApp app) {
        super(app);
    }

    @Override
    protected void addScenes() {
        this.addScene(TWIPreviewScenario.PreviewCtrlScene.createSingleton(this));
    }

    // scenes
    public static class PreviewScene extends TWIScene {
        // singleton pattern
        private static PreviewScene mSingleton = null;

        public static PreviewScene createSingleton(XScenario scenario) {
            assert(PreviewScene.mSingleton == null);

            PreviewScene.mSingleton = new PreviewScene(scenario);
            return PreviewScene.mSingleton;
        }

        public static PreviewScene getSingleton() {
            assert(PreviewScene.mSingleton != null);

            return PreviewScene.mSingleton;
        }

        private PreviewScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {}

        @Override
        public void handleMouseDrag(MouseEvent e) {}

        @Override
        public void handleMouseRelease(MouseEvent e) {}

        @Override
        public void handleKeyDown(KeyEvent e) {
            TWI twi = (TWI) this.mScenario.getApp();

            int code = e.getKeyCode();
            switch(code) {
                case KeyEvent.VK_CONTROL -> {
                    XCmdToChangeScene.execute(
                        twi,
                        TWIPreviewScenario.PreviewCtrlScene.getSingleton(),
                        this
                    );
                }
            }
        }

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

    public static class PreviewCtrlScene extends TWIScene {
        // singleton pattern
        private static PreviewCtrlScene mSingleton = null;

        public static PreviewCtrlScene createSingleton(XScenario scenario) {
            assert(PreviewCtrlScene.mSingleton == null);

            PreviewCtrlScene.mSingleton = new PreviewCtrlScene(scenario);
            return PreviewCtrlScene.mSingleton;
        }

        public static PreviewCtrlScene getSingleton() {
            assert(PreviewCtrlScene.mSingleton != null);

            return PreviewCtrlScene.mSingleton;
        }

        private PreviewCtrlScene(XScenario scenario) {
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
        public void handleKeyUp(KeyEvent e) {
            TWI twi = (TWI) this.mScenario.getApp();

            int code = e.getKeyCode();
            switch(code) {
                case KeyEvent.VK_S -> {
                    // TODO: Implement TWICmdToSavePreview
                }
                case KeyEvent.VK_CONTROL -> {
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
        public void renderWorldObjects(Graphics2D g2) {}

        @Override
        public void renderScreenObjects(Graphics2D g2) {}

        @Override
        public void getReady() {}

        @Override
        public void wrapUp() {}
    }
}
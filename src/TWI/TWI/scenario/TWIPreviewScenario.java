package TWI.scenario;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import TWI.TWI;
import TWI.TWIScene;
import x.XApp;
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
        this.addScene(TWIPreviewScenario.PreviewScene.createSingleton(this));
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
        public void handleKeyDown(KeyEvent e) {}

        @Override
        public void handleKeyUp(KeyEvent e) {
            // TODO: Move this to TWICmdToExport
            //Export (Save) the image
            TWI twi = (TWI) this.mScenario.getApp();

            int code = e.getKeyCode();
            switch(code) {
                case KeyEvent.VK_CONTROL, KeyEvent.VK_S -> {
                //     //
                //     //HD Resolution
                //     int width = 1280;
                //     int height = 720;
                //     BufferedImage image =
                //         new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
                //     Graphics g = image.getGraphics();

                //     g2.paintComponent(g);

                //     try {
                //         ImageIO.write(image, "png", new File(filename));
                //     } catch (IOException exception) {
                //         // TODO: Log
                //     }



                //     //TODO: Implement the solution
                //     //TWICmdToHome.execute(TWI);
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
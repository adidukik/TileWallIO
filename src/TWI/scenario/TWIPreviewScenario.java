package TWI.scenario;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import TWI.TWI;
import TWI.cmd.TWICmdToExportImage;
import TWI.cmd.TWICmdToSetStartingScreenPt;
import TWI.cmd.TWICmdToTranslateTo;
import TWI.cmd.TWICmdToZoomNRotateTo;
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
        this.addScene(TWIPreviewScenario.PreviewScene.createSingleton(this));
        this.addScene(TWIPreviewScenario.ZoomNRotateReadyScene.createSingleton(this));
        this.addScene(TWIPreviewScenario.ZoomNRotateScene.createSingleton(this));
        this.addScene(TWIPreviewScenario.PanReadyScene.createSingleton(this));
        this.addScene(TWIPreviewScenario.PanScene.createSingleton(this));
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
                        TWIPreviewScenario.PanReadyScene.getSingleton(),
                        this
                    );
                }
                case KeyEvent.VK_P -> {
                    XCmdToChangeScene.execute(
                        twi,
                        TWIDefaultScenario.ReadyScene.getSingleton(),
                        null
                    );
                }
                case KeyEvent.VK_ALT -> {
                    XCmdToChangeScene.execute(
                        twi,
                        TWIPreviewScenario.ZoomNRotateReadyScene.getSingleton(),
                        this
                    );
                }
                case KeyEvent.VK_C -> {
                    XCmdToChangeScene.execute(
                        twi,
                        TWIColorScenario.ColorPreviewScene.getSingleton(),
                        this
                    );
                }
            }
        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            TWI twi = (TWI) this.mScenario.getApp();
            int code = e.getKeyCode();
            switch(code) {
                case KeyEvent.VK_S -> {
                    TWICmdToExportImage.execute(twi);
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
        public void renderScreenObjects(Graphics2D g2) {}

        @Override
        public void getReady() {}

        @Override
        public void wrapUp() {}
    }


    public static class ZoomNRotateReadyScene extends TWIScene {
        // singleton pattern
        private static ZoomNRotateReadyScene mSingleton = null;

        public static ZoomNRotateReadyScene createSingleton(XScenario scenario) {
            assert(ZoomNRotateReadyScene.mSingleton == null);

            ZoomNRotateReadyScene.mSingleton = new ZoomNRotateReadyScene(scenario);
            return ZoomNRotateReadyScene.mSingleton;
        }

        public static ZoomNRotateReadyScene getSingleton() {
            assert(ZoomNRotateReadyScene.mSingleton != null);

            return ZoomNRotateReadyScene.mSingleton;
        }

        private ZoomNRotateReadyScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            TWI twi = (TWI) this.mScenario.getApp();

            Point pt = e.getPoint();
            TWICmdToSetStartingScreenPt.execute(twi, pt);

            XCmdToChangeScene.execute(
                twi,
                TWIPreviewScenario.ZoomNRotateScene.getSingleton(),
                this.mReturnScene
            );
        }

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

            switch (code) {
                case KeyEvent.VK_ALT -> {
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
        public void renderScreenObjects(Graphics2D g2) {}

        @Override
        public void getReady() {}

        @Override
        public void wrapUp() {}
    }



    public static class ZoomNRotateScene extends TWIScene {
        // singleton pattern
        private static ZoomNRotateScene mSingleton = null;

        public static ZoomNRotateScene createSingleton(XScenario scenario) {
            assert(ZoomNRotateScene.mSingleton == null);

            ZoomNRotateScene.mSingleton = new ZoomNRotateScene(scenario);
            return ZoomNRotateScene.mSingleton;
        }

        public static ZoomNRotateScene getSingleton() {
            assert(ZoomNRotateScene.mSingleton != null);

            return ZoomNRotateScene.mSingleton;
        }

        private ZoomNRotateScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {}

        @Override
        public void handleMouseDrag(MouseEvent e) {
            TWI twi = (TWI) this.mScenario.getApp();

            Point pt = e.getPoint();
            TWICmdToZoomNRotateTo.execute(twi, pt);
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            TWI twi = (TWI) this.mScenario.getApp();

            TWICmdToSetStartingScreenPt.execute(twi, null);

            XCmdToChangeScene.execute(
                twi,
                TWIPreviewScenario.ZoomNRotateReadyScene.getSingleton(),
                this.mReturnScene
            );

        }

        @Override
        public void handleKeyDown(KeyEvent e) {}

        @Override
        public void handleKeyUp(KeyEvent e) {
            TWI twi = (TWI) this.mScenario.getApp();

            int code = e.getKeyCode();
            TWICmdToSetStartingScreenPt.execute(twi, null);

            switch (code) {
                case KeyEvent.VK_ALT -> {
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
        public void renderScreenObjects(Graphics2D g2) {}

        @Override
        public void getReady() {}

        @Override
        public void wrapUp() {}
    }

    public static class PanReadyScene extends TWIScene {
        // singleton pattern
        private static PanReadyScene mSingleton = null;

        public static PanReadyScene createSingleton(XScenario scenario) {
            assert(PanReadyScene.mSingleton == null);

            PanReadyScene.mSingleton = new PanReadyScene(scenario);
            return PanReadyScene.mSingleton;
        }

        public static PanReadyScene getSingleton() {
            assert(PanReadyScene.mSingleton != null);

            return PanReadyScene.mSingleton;
        }

        private PanReadyScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            TWI twi = (TWI) this.mScenario.getApp();

            Point pt = e.getPoint();
            TWICmdToSetStartingScreenPt.execute(twi, pt);

            XCmdToChangeScene.execute(
                twi,
                TWIPreviewScenario.PanScene.getSingleton(),
                this.mReturnScene
            );
        }

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

            switch (code) {
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
        public void renderWorldObjects(Graphics2D g2) {
            TWI twi = (TWI) this.mScenario.getApp();
            twi.getPreviewMgr().render(g2, new Point(0, 0));
        }

        @Override
        public void renderScreenObjects(Graphics2D g2) {}

        @Override
        public void getReady() {}

        @Override
        public void wrapUp() {}
    }


    public static class PanScene extends TWIScene {
        // singleton pattern
        private static PanScene mSingleton = null;

        public static PanScene createSingleton(XScenario scenario) {
            assert(PanScene.mSingleton == null);

            PanScene.mSingleton = new PanScene(scenario);
            return PanScene.mSingleton;
        }

        public static PanScene getSingleton() {
            assert(PanScene.mSingleton != null);

            return PanScene.mSingleton;
        }

        private PanScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {}

        @Override
        public void handleMouseDrag(MouseEvent e) {
            TWI twi = (TWI) this.mScenario.getApp();

            Point pt = e.getPoint();
            TWICmdToTranslateTo.execute(twi, pt);
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            TWI twi = (TWI) this.mScenario.getApp();

            TWICmdToSetStartingScreenPt.execute(twi, null);

            XCmdToChangeScene.execute(
                twi,
                TWIPreviewScenario.PanReadyScene.getSingleton(),
                this.mReturnScene
            );
        }

        @Override
        public void handleKeyDown(KeyEvent e) {}

        @Override
        public void handleKeyUp(KeyEvent e) {
            TWI twi = (TWI) this.mScenario.getApp();

            int code = e.getKeyCode();
            TWICmdToSetStartingScreenPt.execute(twi, null);

            switch (code) {
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
        public void renderWorldObjects(Graphics2D g2) {
            TWI twi = (TWI) this.mScenario.getApp();
            twi.getPreviewMgr().render(g2, new Point(0, 0));
        }

        @Override
        public void renderScreenObjects(Graphics2D g2) {}

        @Override
        public void getReady() {}

        @Override
        public void wrapUp() {}
    }



}
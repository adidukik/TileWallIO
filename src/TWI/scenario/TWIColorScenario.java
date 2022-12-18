package TWI.scenario;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import TWI.TWI;
import TWI.TWIColorChooser;
import TWI.cmd.TWICmdToChangeDrawColor;
import TWI.cmd.TWICmdToChangeSelectedPatternsColor;
import x.XApp;
import x.XCmdToChangeScene;
import x.XScenario;

public class TWIColorScenario extends XScenario {
    // singleton pattern
    private static TWIColorScenario mSingleton = null;

    public static TWIColorScenario createSingleton(XApp app) {
        assert (TWIColorScenario.mSingleton == null);

        TWIColorScenario.mSingleton = new TWIColorScenario(app);
        TWIColorScenario.mColorChooser = new TWIColorChooser((TWI) app);
        return TWIColorScenario.mSingleton;
    }

    public static TWIColorScenario getSingleton() {
        assert (TWIColorScenario.mSingleton != null);

        return TWIColorScenario.mSingleton;
    }

    private TWIColorScenario(XApp app) {
        super(app);
    }

    @Override
    protected void addScenes() {
        this.addScene(TWIColorScenario.ColorScene.createSingleton(this));
        this.addScene(
            TWIColorScenario.ColorSelectedScene.createSingleton(this)
        );
    }

    // scenes
    public static class ColorScene extends TWIScene {
        // singleton pattern
        private static ColorScene mSingleton = null;

        public static ColorScene createSingleton(XScenario scenario) {
            assert(ColorScene.mSingleton == null);

            ColorScene.mSingleton = new ColorScene(scenario);
            return ColorScene.mSingleton;
        }

        public static ColorScene getSingleton() {
            assert(ColorScene.mSingleton != null);

            return ColorScene.mSingleton;
        }

        private ColorScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            TWI twi = (TWI) this.getScenario().getApp();


            Color color = TWIColorScenario.getColorChooser()
                .calcColor(e.getPoint());

            TWICmdToChangeDrawColor.execute(twi,color);
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
                case KeyEvent.VK_C -> {
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
            twi.getToolMgr().renderStrokePreview(g2);
            TWIColorScenario.drawColorChooser(g2);
        }

        @Override
        public void getReady() {}

        @Override
        public void wrapUp() {}
    }

    public static class ColorSelectedScene extends TWIScene {
        // singleton pattern
        private static ColorSelectedScene mSingleton = null;

        public static ColorSelectedScene createSingleton(XScenario scenario) {
            assert(ColorSelectedScene.mSingleton == null);

            ColorSelectedScene.mSingleton = new ColorSelectedScene(scenario);
            return ColorSelectedScene.mSingleton;
        }

        public static ColorSelectedScene getSingleton() {
            assert(ColorSelectedScene.mSingleton != null);

            return ColorSelectedScene.mSingleton;
        }

        private ColorSelectedScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            TWI twi = (TWI) this.getScenario().getApp();


            Color color = TWIColorScenario.getColorChooser()
                .calcColor(e.getPoint());

            TWICmdToChangeSelectedPatternsColor.execute(twi, color);

            XCmdToChangeScene.execute(
                twi,
                this.mReturnScene,
                null
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
            TWI twi = (TWI) this.getScenario().getApp();

            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_C -> {
                    XCmdToChangeScene.execute(
                        twi,
                        TWISelectScenario.SelectedReadyScene.getSingleton(),
                        this.mReturnScene
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
            twi.getToolMgr().renderStrokePreview(g2);
            TWIColorScenario.drawColorChooser(g2);
        }

        @Override
        public void getReady() {}

        @Override
        public void wrapUp() {}
    }

    // class static fields
    private static TWIColorChooser mColorChooser = null;

    public static TWIColorChooser getColorChooser() {
        return TWIColorScenario.mColorChooser;
    }

    public static void drawColorChooser(Graphics2D g2) {
        TWIColorScenario.mColorChooser.render(g2, new Point(0, 0));
    }
}
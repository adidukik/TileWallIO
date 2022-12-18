package TWI.scenario;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import TWI.TWI;
import TWI.cmd.TWICmdToCreateGeom;
import TWI.cmd.TWICmdToDecreaseDrawStrokeWidth;
import TWI.cmd.TWICmdToIncreaseDrawStrokeWidth;
import TWI.cmd.TWICmdToRemoveAllPatterns;
import TWI.cmd.TWICmdToSwitchToNextTool;
import TWI.cmd.TWICmdToSwitchToPrevTool;
import TWI.cmd.TWICmdToTurnSnapOnOff;
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
        this.addScene(
            TWIDefaultScenario.ReadyScene.createSingleton(this)
        );
        this.addScene(
            TWIDefaultScenario.ReadyNoSnapScene.createSingleton(this)
        );
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
                if (TWICmdToCreateGeom.execute(twi, e.getPoint())) {
                    XCmdToChangeScene.execute(
                    twi,
                    TWIDrawScenario.DrawLineReadyScene.getSingleton(),
                    this
                );
            }
        }

        @Override
        public void handleMouseDrag(MouseEvent e) {}

        @Override
        public void handleMouseRelease(MouseEvent e) {}

        @Override
        public void handleKeyDown(KeyEvent e) {
            TWI twi = (TWI) this.getScenario().getApp();

            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_S -> {
                    TWICmdToTurnSnapOnOff.execute(twi, false);

                    XCmdToChangeScene.execute(
                        this.mScenario.getApp(),
                        TWIDefaultScenario.ReadyNoSnapScene.getSingleton(),
                        TWIDefaultScenario.ReadyScene.getSingleton()
                    );
                }
                case KeyEvent.VK_SHIFT -> {
                    XCmdToChangeScene.execute(
                        twi,
                        TWISelectScenario.SelectReadyScene.getSingleton(),
                        this
                    );
                }
                case KeyEvent.VK_E -> {
                    XCmdToChangeScene.execute(
                        twi,
                        TWIEditScenario.EditReadyScene.getSingleton(),
                        this
                    );
                }
                case KeyEvent.VK_P -> {
                    XCmdToChangeScene.execute(
                        twi,
                        TWIPreviewScenario.PreviewScene.getSingleton(),
                        this
                    );
                }
                case KeyEvent.VK_C -> {
                    XCmdToChangeScene.execute(
                        twi,
                        TWIColorScenario.ColorScene.getSingleton(),
                        this
                    );
                }
            }
        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            TWI twi = (TWI) this.getScenario().getApp();

            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_0 -> {
                    TWICmdToRemoveAllPatterns.execute(twi);
                }
                case KeyEvent.VK_LEFT -> {
                    TWICmdToSwitchToPrevTool.execute(twi);
                }
                case KeyEvent.VK_RIGHT -> {
                    TWICmdToSwitchToNextTool.execute(twi);
                }
                case KeyEvent.VK_UP -> {
                    TWICmdToIncreaseDrawStrokeWidth.execute(twi);
                }
                case KeyEvent.VK_DOWN -> {
                    TWICmdToDecreaseDrawStrokeWidth.execute(twi);
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
        }

        @Override
        public void getReady() {}

        @Override
        public void wrapUp() {}
    }

    public static class ReadyNoSnapScene extends TWIScene {
        // singleton pattern
        private static ReadyNoSnapScene mSingleton = null;

        public static ReadyNoSnapScene createSingleton(XScenario scenario) {
            assert(ReadyNoSnapScene.mSingleton == null);

            ReadyNoSnapScene.mSingleton = new ReadyNoSnapScene(scenario);
            return ReadyNoSnapScene.mSingleton;
        }

        public static ReadyNoSnapScene getSingleton() {
            assert(ReadyNoSnapScene.mSingleton != null);

            return ReadyNoSnapScene.mSingleton;
        }

        private ReadyNoSnapScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            TWI twi = (TWI) this.getScenario().getApp();

            // TWICmdToCreateLine.execute(twi, e.getPoint());
            TWICmdToCreateGeom.execute(twi, e.getPoint());

            XCmdToChangeScene.execute(
                twi,
                TWIDrawScenario.DrawLineReadyScene.getSingleton(),
                this
            );
        }

        @Override
        public void handleMouseDrag(MouseEvent e) {}

        @Override
        public void handleMouseRelease(MouseEvent e) {}

        @Override
        public void handleKeyDown(KeyEvent e) {
            TWI twi = (TWI) this.getScenario().getApp();

            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_SHIFT -> {
                    XCmdToChangeScene.execute(
                        twi,
                        TWISelectScenario.SelectReadyScene.getSingleton(),
                        this
                    );
                }
                case KeyEvent.VK_E -> {
                    XCmdToChangeScene.execute(
                        twi,
                        TWIEditScenario.EditReadyScene.getSingleton(),
                        this
                    );
                }
            }
        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            TWI twi = (TWI) this.getScenario().getApp();

            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_S -> {
                    TWICmdToTurnSnapOnOff.execute(twi, true);

                    XCmdToChangeScene.execute(
                        this.mScenario.getApp(),
                        TWIDefaultScenario.ReadyScene.getSingleton(),
                        null
                    );
                }
                case KeyEvent.VK_0 -> {
                    TWICmdToRemoveAllPatterns.execute(twi);
                }
                case KeyEvent.VK_LEFT -> {
                    TWICmdToSwitchToPrevTool.execute(twi);
                }
                case KeyEvent.VK_RIGHT -> {
                    TWICmdToSwitchToNextTool.execute(twi);
                }
                case KeyEvent.VK_UP -> {
                    TWICmdToIncreaseDrawStrokeWidth.execute(twi);
                }
                case KeyEvent.VK_DOWN -> {
                    TWICmdToDecreaseDrawStrokeWidth.execute(twi);
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
        }

        @Override
        public void getReady() {}

        @Override
        public void wrapUp() {}
    }
}
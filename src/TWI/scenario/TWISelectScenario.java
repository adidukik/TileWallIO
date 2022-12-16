package TWI.scenario;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import TWI.TWI;
import TWI.TWISelectionBox;
import TWI.cmd.TWICmdToCreateSelectionBox;
import TWI.cmd.TWICmdToDeselectSelectedPatterns;
import TWI.cmd.TWICmdToDestroySelectionBox;
import TWI.cmd.TWICmdToRemoveSelectedPattern;
import TWI.cmd.TWICmdToUpdateSelectedPatterns;
import TWI.cmd.TWICmdToUpdateSelectionBox;
import x.XApp;
import x.XCmdToChangeScene;
import x.XScenario;

public class TWISelectScenario extends XScenario {
    // singleton pattern
    private static TWISelectScenario mSingleton = null;

    public static TWISelectScenario createSingleton(XApp app) {
        assert (TWISelectScenario.mSingleton == null);

        TWISelectScenario.mSingleton = new TWISelectScenario(app);
        return TWISelectScenario.mSingleton;
    }

    public static TWISelectScenario getSingleton() {
        assert (TWISelectScenario.mSingleton != null);

        return TWISelectScenario.mSingleton;
    }

    private TWISelectScenario(XApp app) {
        super(app);
    }

    @Override
    protected void addScenes() {
        this.addScene(
            TWISelectScenario.SelectReadyScene.createSingleton(this)
        );
        this.addScene(
            TWISelectScenario.SelectScene.createSingleton(this)
        );
        this.addScene(
            TWISelectScenario.SelectedReadyScene.createSingleton(this)
        );
    }

    // scenes
    public static class SelectReadyScene extends TWIScene {
        // singleton pattern
        private static SelectReadyScene mSingleton = null;

        public static SelectReadyScene createSingleton(XScenario scenario) {
            assert(SelectReadyScene.mSingleton == null);

            SelectReadyScene.mSingleton = new SelectReadyScene(scenario);
            return SelectReadyScene.mSingleton;
        }

        public static SelectReadyScene getSingleton() {
            assert(SelectReadyScene.mSingleton != null);

            return SelectReadyScene.mSingleton;
        }

        private SelectReadyScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            TWI TWI = (TWI) this.mScenario.getApp();

            Point pt = e.getPoint();
            TWICmdToCreateSelectionBox.execute(TWI, pt);

            XCmdToChangeScene.execute(
                TWI,
                TWISelectScenario.SelectScene.getSingleton(),
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
            TWI TWI = (TWI) this.mScenario.getApp();
            int code = e.getKeyCode();

            switch (code) {
                case KeyEvent.VK_SHIFT -> {
                    boolean isSelectionBoxEmpty =
                        TWI.getTileMgr().getTile().getSelectedPatterns()
                            .isEmpty();

                    if (isSelectionBoxEmpty) {
                        XCmdToChangeScene.execute(
                            TWI,
                            TWIDefaultScenario.ReadyScene.getSingleton(),
                            null
                        );
                    } else {
                        XCmdToChangeScene.execute(
                            TWI,
                            TWISelectScenario.SelectedReadyScene.getSingleton(),
                            null
                        );
                    }
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
            twi.getTileMgr().render(g2, new Point(0, 0));
        }

        @Override
        public void getReady() {}

        @Override
        public void wrapUp() {}
    }


    public static class SelectScene extends TWIScene {
        // singleton pattern
        private static SelectScene mSingleton = null;

        public static SelectScene createSingleton(XScenario scenario) {
            assert(SelectScene.mSingleton == null);

            SelectScene.mSingleton = new SelectScene(scenario);
            return SelectScene.mSingleton;
        }

        public static SelectScene getSingleton() {
            assert(SelectScene.mSingleton != null);

            return SelectScene.mSingleton;
        }

        private SelectScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {}

        @Override
        public void handleMouseDrag(MouseEvent e) {
            TWI TWI = (TWI) this.mScenario.getApp();

            Point pt = e.getPoint();
            TWICmdToUpdateSelectionBox.execute(TWI, pt);

            TWICmdToUpdateSelectedPatterns.execute(TWI);
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            TWI TWI = (TWI) this.mScenario.getApp();
            XCmdToChangeScene.execute(
                TWI,
                TWISelectScenario.SelectReadyScene.getSingleton(),
                this.mReturnScene
            );
        }

        @Override
        public void handleKeyDown(KeyEvent e) {}

        @Override
        public void handleKeyUp(KeyEvent e) {
            TWI TWI = (TWI) this.mScenario.getApp();
            int code = e.getKeyCode();

            switch (code) {
                case KeyEvent.VK_SHIFT -> {
                    if (
                        TWI.getTileMgr().getTile().getSelectedPatterns()
                            .isEmpty()
                    ) {
                        XCmdToChangeScene.execute(
                            TWI,
                            TWIDefaultScenario.ReadyScene.getSingleton(),
                            null
                        );
                    } else {
                        XCmdToChangeScene.execute(
                            TWI,
                            TWISelectScenario.SelectedReadyScene.getSingleton(),
                            null
                        );
                    }
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
            twi.getTileMgr().render(g2, new Point(0, 0));

            TWISelectScenario.getSingleton().drawSelectionBox(
                g2, new Point(0, 0)
            );
        }

        @Override
        public void getReady() {}

        @Override
        public void wrapUp() {
            TWI TWI = (TWI) this.mScenario.getApp();
            TWICmdToDestroySelectionBox.execute(TWI);
        }
    }


    public static class SelectedReadyScene extends TWIScene {
        // singleton pattern
        private static SelectedReadyScene mSingleton = null;

        public static SelectedReadyScene createSingleton(XScenario scenario) {
            assert(SelectedReadyScene.mSingleton == null);

            SelectedReadyScene.mSingleton = new SelectedReadyScene(scenario);
            return SelectedReadyScene.mSingleton;
        }

        public static SelectedReadyScene getSingleton() {
            assert(SelectedReadyScene.mSingleton != null);

            return SelectedReadyScene.mSingleton;
        }

        private SelectedReadyScene(XScenario scenario) {
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
            TWI TWI = (TWI) this.mScenario.getApp();

            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_SHIFT -> {
                    XCmdToChangeScene.execute(
                        TWI,
                        TWISelectScenario.SelectReadyScene.getSingleton(),
                        this
                    );
                }
            }
        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            TWI TWI = (TWI) this.mScenario.getApp();

            int code = e.getKeyCode();
            switch(code) {
                case KeyEvent.VK_DELETE -> {
                    TWICmdToRemoveSelectedPattern.execute(TWI);

                    XCmdToChangeScene.execute(
                        TWI,
                        TWIDefaultScenario.ReadyScene.getSingleton(),
                        null
                    );
                }
                case KeyEvent.VK_ESCAPE -> {
                    TWICmdToDeselectSelectedPatterns.execute(TWI);

                    XCmdToChangeScene.execute(
                        TWI,
                        TWIDefaultScenario.ReadyScene.getSingleton(),
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
            twi.getTileMgr().render(g2, new Point(0, 0));
        }

        @Override
        public void getReady() {}

        @Override
        public void wrapUp() {}
    }


    // class fields
    private TWISelectionBox mSelectionBox = null;

    public TWISelectionBox getSelectionBox() {
        return this.mSelectionBox;
    }

    public void setSelectionBox(TWISelectionBox selectionBox) {
        this.mSelectionBox = selectionBox;
    }

    private void drawSelectionBox(Graphics2D g2, Point origin) {
        TWISelectionBox selectionBox =
            TWISelectScenario.getSingleton().getSelectionBox();

        if (selectionBox != null) {
            selectionBox.render(g2, origin);
        }
    }
}
package TWI.scenario;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import TWI.TWI;
import TWI.cmd.TWICmdToCreateLine;
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
            TWIDefaultScenario.ReadySnapScene.createSingleton(this)
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
                if (TWICmdToCreateLine.execute(twi, e.getPoint())) {
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
                    TWICmdToTurnSnapOnOff.execute(twi, true);

                    XCmdToChangeScene.execute(
                        this.mScenario.getApp(),
                        TWIDefaultScenario.ReadySnapScene.getSingleton(),
                        TWIDefaultScenario.ReadyScene.getSingleton()
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
        public void renderScreenObjects(Graphics2D g2) {
            TWI twi = (TWI) this.mScenario.getApp();
            twi.getTileMgr().render(g2, new Point(0, 0));
        }

        @Override
        public void getReady() {}

        @Override
        public void wrapUp() {}
    }

    public static class ReadySnapScene extends TWIScene {
        // singleton pattern
        private static ReadySnapScene mSingleton = null;

        public static ReadySnapScene createSingleton(XScenario scenario) {
            assert(ReadySnapScene.mSingleton == null);

            ReadySnapScene.mSingleton = new ReadySnapScene(scenario);
            return ReadySnapScene.mSingleton;
        }

        public static ReadySnapScene getSingleton() {
            assert(ReadySnapScene.mSingleton != null);

            return ReadySnapScene.mSingleton;
        }

        private ReadySnapScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            TWI twi = (TWI) this.getScenario().getApp();

            TWICmdToCreateLine.execute(twi, e.getPoint());

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
        public void handleKeyDown(KeyEvent e) {}

        @Override
        public void handleKeyUp(KeyEvent e) {
            TWI twi = (TWI) this.getScenario().getApp();

            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_S -> {
                    TWICmdToTurnSnapOnOff.execute(twi, false);

                    XCmdToChangeScene.execute(
                        this.mScenario.getApp(),
                        TWIDefaultScenario.ReadyScene.getSingleton(),
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
        public void renderScreenObjects(Graphics2D g2) {
            TWI twi = (TWI) this.mScenario.getApp();
            twi.getTileMgr().render(g2, new Point(0, 0));
        }

        @Override
        public void getReady() {}

        @Override
        public void wrapUp() {}
    }
}
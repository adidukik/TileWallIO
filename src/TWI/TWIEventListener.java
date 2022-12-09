package TWI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import TWI.scenario.TWIScene;

public class TWIEventListener implements
  MouseListener, MouseMotionListener, KeyListener {
    // fields
    private TWI mTWI = null;

    // constructor
    public TWIEventListener(TWI TWI) {
        this.mTWI = TWI;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        TWIScene curScene = (TWIScene) this.mTWI.getScenarioMgr().getCurScene();
        curScene.handleMousePress(e);
        this.mTWI.getCanvas2d().repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        TWIScene curScene = (TWIScene) this.mTWI.getScenarioMgr().getCurScene();
        curScene.handleMouseDrag(e);
        this.mTWI.getCanvas2d().repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        TWIScene curScene = (TWIScene) this.mTWI.getScenarioMgr().getCurScene();
        curScene.handleMouseRelease(e);
        this.mTWI.getCanvas2d().repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    // methods for key event interface
    @Override
    public void keyPressed(KeyEvent e) {
        TWIScene curScene = (TWIScene) this.mTWI.getScenarioMgr().getCurScene();
        curScene.handleKeyDown(e);
        this.mTWI.getCanvas2d().repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        TWIScene curScene = (TWIScene) this.mTWI.getScenarioMgr().getCurScene();
        curScene.handleKeyUp(e);
        this.mTWI.getCanvas2d().repaint();
    }

    @Override
    public void keyTyped(KeyEvent arg0) {}
}
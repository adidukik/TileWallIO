package TWI;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import x.XScenario;
import x.XScene;

public abstract class TWIScene extends XScene{
    // protected constructor
    protected TWIScene(XScenario scenario) {
        super(scenario);
    }

    // event handling abstract methods
    public abstract void handleMousePress(MouseEvent e);
    public abstract void handleMouseDrag(MouseEvent e);
    public abstract void handleMouseRelease(MouseEvent e);

    public abstract void handleKeyDown(KeyEvent e);
    public abstract void handleKeyUp(KeyEvent e);

    public abstract void updateSupportObjects(Graphics2D g2);
    public abstract void renderWorldObjects(Graphics2D g2);
    public abstract void renderScreenObjects(Graphics2D g2);
}
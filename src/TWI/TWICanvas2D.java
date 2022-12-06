package TWI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class TWICanvas2D extends JPanel {
    // constant
    private static final Color COLOR_BG_DEFAULT = new Color(255, 255, 255, 255);


    // constant
    private static final Color COLOR_INFO = new Color(255, 0, 0, 128);

    private static final Font FONT_INFO =
        new Font("Monospaced", Font.PLAIN, 24);

    private static final int INFO_TOP_ALIGNMENT_X = 20;
    private static final int INFO_TOP_ALIGNMENT_Y = 30;

    // fields
    private TWI mTWI = null;

    private Color mBgColor = null;

    public Color getBgColor() {
        return this.mBgColor;
    }

    public void setBgColor(Color color) {
        this.mBgColor = color;
    }

    // constructor
    public TWICanvas2D(TWI twi) {
        this.mTWI = twi;
        this.mBgColor = TWICanvas2D.COLOR_BG_DEFAULT;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // convert Graphics to Graphics2D
        Graphics2D g2 = (Graphics2D) g;

        // turn on Anti-Aliasing
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        // world objects
        g2.transform(this.mTWI.getXform().getCurXformFromWorldToScreen());

        // render common world objects

        // render current scene's world object
        TWIScene curScene = (TWIScene) this.mTWI.getScenarioMgr().getCurScene();
        curScene.renderWorldObjects(g2);

        // screen objects
        g2.transform(this.mTWI.getXform().getCurXformFromScreenToWorld());

        // render common screen objects
        this.drawInfo(g2);

        // render the current scene's screen object
        curScene.renderScreenObjects(g2);
    }

    private void drawInfo(Graphics2D g2) {
        TWIScene curScene = (TWIScene) this.mTWI.getScenarioMgr().getCurScene();
        String str = curScene.getClass().getSimpleName();
        g2.setColor(TWICanvas2D.COLOR_INFO);
        g2.setFont(TWICanvas2D.FONT_INFO);
        g2.drawString(
            str,
            TWICanvas2D.INFO_TOP_ALIGNMENT_X,
            TWICanvas2D.INFO_TOP_ALIGNMENT_Y
        );
    }
}

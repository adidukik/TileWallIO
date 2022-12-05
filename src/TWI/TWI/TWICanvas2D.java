package TWI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class TWICanvas2D extends JPanel {
    // constant
    private static final Color COLOR_BG_DEFAULT = new Color(255, 255, 255, 255);

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

        // TODO
    }
}

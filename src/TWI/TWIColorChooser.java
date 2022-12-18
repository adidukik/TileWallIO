package TWI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class TWIColorChooser implements TWIRenderable {
    // constants
    private static final int CELL_NUM_H = 40;
    private static final int CELL_NUM_B = 10;
    private static final float SATURATION_DEFAULT = 0.5f;
    private static final float OPAQUENESS_DEFAULT = 1f;

    // fields
    private TWI mTWI = null;

    private Color[][] mColors = null;

    private float mSaturation = Float.NaN;
    private float mOpaqueness = Float.NaN;

    // constructor
    public TWIColorChooser(TWI twi) {
        this.mTWI = twi;

        // memory allocation
        this.mColors = new Color[TWIColorChooser.CELL_NUM_B][];
        for (int i = 0; i < TWIColorChooser.CELL_NUM_B; i ++) {
            this.mColors[i] = new Color[TWIColorChooser.CELL_NUM_H];
        }
        this.mSaturation = TWIColorChooser.SATURATION_DEFAULT;
        this.mOpaqueness = TWIColorChooser.OPAQUENESS_DEFAULT;

        this.calcCellColors();
    }

    private void calcCellColors() {
        float db = 1f / (float)(TWIColorChooser.CELL_NUM_B - 1);
        float dh = 1f / (float)(TWIColorChooser.CELL_NUM_H - 1);

        for (int i = 0; i < TWIColorChooser.CELL_NUM_B; i ++) {
            float b = db * (float)i;
            for (int j = 0; j < TWIColorChooser.CELL_NUM_H; j++) {
                float h = dh * (float)j;
                Color hsb = Color.getHSBColor(h, this.mSaturation, b);
                this.mColors[i][j] = new Color(
                    hsb.getRed(), hsb.getGreen(), hsb.getBlue(),
                    (int)(this.mOpaqueness * 255f)
                );
            }
        }
    }

    private void drawCells(Graphics2D g2, int w, int h, Point origin) {
        double ys = (double)h / 3.0 * 1.0;
        double ye = (double)h / 3.0 * 2.0;
        double dx = (double)w / (double) TWIColorChooser.CELL_NUM_H;
        double dy = (ye - ys) / (double) TWIColorChooser.CELL_NUM_B;

        for (int i = 0; i < TWIColorChooser.CELL_NUM_B; i ++) {
            double y = ys + dy * (double)i;
            for (int j = 0; j < TWIColorChooser.CELL_NUM_H; j ++) {
                double x = dx * (double)j;
                Rectangle2D rect = new Rectangle2D.Double(
                    x + origin.getX(), y + origin.getY(),
                    dx, dy
                );
                g2.setColor(this.mColors[i][j]);
                g2.fill(rect);
            }
        }

    }

    public Color calcColor(Point pt) {
        int w = this.mTWI.getCanvas2d().getWidth();
        int h = this.mTWI.getCanvas2d().getHeight();

        double ys = (double) h / 3.0 * 1.0;
        double ye = (double) h / 3.0 * 2.0;
        double dx = (double) w / (double) TWIColorChooser.CELL_NUM_H;
        double dy = (ye - ys) / (double) TWIColorChooser.CELL_NUM_B;

        int i = (int) (((double) pt.y - ys) / dy);
        int j = (int) (((double) pt.x) / dx);

        if (i < 0 || i >= TWIColorChooser.CELL_NUM_B) return null;

        return this.mColors[i][j];
    }

    // interface methods
    @Override
    public void render(Graphics2D g2, Point origin) {
        int w = this.mTWI.getCanvas2d().getWidth();
        int h = this.mTWI.getCanvas2d().getHeight();

        this.drawCells(g2, w, h, origin);
    }
}
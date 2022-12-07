package TWI.preview;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import TWI.TWIPattern;
import TWI.tile.TWITile;

public abstract class TWITileImage {
    // fields
    private TWITile mTile = null;

    private BufferedImage mImage = null;

    public BufferedImage getImage() {
        return this.mImage;
    }

    // constructor
    protected TWITileImage(TWITile tile) {
        this.mTile = tile;

        int image_width = (int)
            tile.getTileGeom().getShape().getBounds().getWidth();

        int image_height = (int)
            tile.getTileGeom().getShape().getBounds().getHeight();

        this.mImage = new BufferedImage(
            image_width,
            image_height,
            BufferedImage.TYPE_4BYTE_ABGR
        );

        this.generateImage();
    }

    // methods
    public void generateImage() {
        this.clearImage();

        ArrayList<TWIPattern> patterns = this.mTile.getPatterns();

        for (TWIPattern p : patterns) {
            Graphics2D g2 = (Graphics2D) this.mImage.getGraphics();
            p.getGeom().render(g2);
        }
    }

    public void renderImageAt(Graphics2D g2, AffineTransform xform) {
        g2.drawImage(mImage, xform, null);
    }

    private void clearImage() {
        Graphics2D g = this.mImage.createGraphics();
        g.setBackground(new Color(0, 0, 0, 0));
        g.clearRect(
            0, 0,
            this.mImage.getWidth(),
            this.mImage.getHeight()
        );
    }

    // abstract methods
    public abstract Point getRenderPosition(int i);
}

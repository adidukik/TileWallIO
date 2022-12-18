package TWI.tileImage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import TWI.pattern.TWIPattern;
import TWI.tile.TWITile;

public abstract class TWITileImage {
    // fields
    private TWITile mTile = null;

    protected BufferedImage mImage = null;

    public BufferedImage getImage() {
        return this.mImage;
    }

    // constructor
    protected TWITileImage(TWITile tile) {
        this.mTile = tile;

        int image_width = (int)
            tile.getTileGeom().getBounds().getWidth();

        int image_height = (int)
            tile.getTileGeom().getBounds().getHeight();

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

        Graphics2D g2 = this.mImage.createGraphics();

        ArrayList<TWIPattern> patterns = this.mTile.getPatterns();

        for (TWIPattern p : patterns) {
            p.render(g2, new Point(0, 0));
        }

        g2.dispose();
    }

    public void renderImageAt(
        Graphics2D g2,
        int x, int y, int w, int h
    ) {
        g2.drawImage(this.mImage, x, y, w, h, null);
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

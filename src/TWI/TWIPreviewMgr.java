package TWI;

import java.awt.Graphics2D;
import java.awt.Point;

import TWI.tileImage.TWITileImage;

public class TWIPreviewMgr implements TWIRenderable {
    // constant
    private static final int REDUCE_SCALE = 3;

    // fields
    private TWITileImage mTileImage = null;

    // Constructor
    public TWIPreviewMgr(TWI twi) {
        this.mTileImage = twi.getTileMgr().getTile().getTileImage();
    }

    // methods
    public void updateTileImage() {
        this.mTileImage.generateImage();
    }

    @Override
    public void render(Graphics2D g2, Point origin) {
        // TODO: Implement dynamic rendering to reduce unnecessary rendering
        for (int i = 0; i < 50 * 50; i ++) {
            Point renderPt = this.mTileImage.getRenderPosition(i);

            this.mTileImage.renderImageAt(
                g2,
                renderPt.x / TWIPreviewMgr.REDUCE_SCALE,
                renderPt.y / TWIPreviewMgr.REDUCE_SCALE,
                this.mTileImage.getImage().getWidth()
                    / TWIPreviewMgr.REDUCE_SCALE,
                this.mTileImage.getImage().getHeight()
                    / TWIPreviewMgr.REDUCE_SCALE
            );
        }
    }
}

package TWI.preview;

import java.awt.Graphics2D;
import java.awt.Point;

import TWI.TWI;
import TWI.TWIRenderable;
import TWI.tile.TWITile;

public class TWIPreviewMgr implements TWIRenderable {
    // fields
    private TWI mTWI = null;

    private TWITileImage mTileImage = null;

    public TWIPreviewMgr(TWI twi) {
        this.mTWI = twi;

        TWITile tile = this.mTWI.getTileMgr().getTile();

        this.mTileImage = new TWISquareTileImage(tile);
    }

    // methods
    @Override
    public void render(Graphics2D g2, Point origin) {
        // TODO Auto-generated method stub
    }
}

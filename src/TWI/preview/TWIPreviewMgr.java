package TWI.preview;

import TWI.TWI;
import TWI.tile.TWITile;

public class TWIPreviewMgr {
    // fields
    private TWI mTWI = null;

    private TWITileImage mTileImage = null;

    // TODO: Is here for debugging
    public TWITileImage getTileImage() {
        return this.mTileImage;
    }

    public TWIPreviewMgr(TWI twi) {
        this.mTWI = twi;

        TWITile tile = this.mTWI.getTileMgr().getTile();

        this.mTileImage = new TWISquareTileImage(tile);
    }

    // methods
    public void update() {
        // TODO
    }
}

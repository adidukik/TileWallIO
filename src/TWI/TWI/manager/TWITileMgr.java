package TWI.manager;

import TWI.tile.TWISquareTile;
import TWI.tile.TWITile;

public class TWITileMgr {
    // constant
    public enum TILE_SHAPE {
        SQUARE,
        HEXAGON,
        TRIANGLE
    }

    // fields
    private TWITile mTile = null;

    public TWITile getTile() {
        return this.mTile;
    }

    // constructor
    public TWITileMgr(TILE_SHAPE tileShape) {
        switch (tileShape) {
            case SQUARE -> {
                this.mTile = new TWISquareTile();
            }
            case HEXAGON -> {
                throw new UnsupportedOperationException(
                    "Unimplemented case: " + tileShape
                );
            }
            case TRIANGLE -> {
                throw new UnsupportedOperationException(
                    "Unimplemented case: " + tileShape
                );
            }
        }
    }
}

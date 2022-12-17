package TWI.start;

import java.awt.Graphics2D;
import java.awt.Point;

import TWI.TWI;
import TWI.TWIRenderable;

public class TWIStartMgr implements TWIRenderable {
    //constants
    protected static final double X_DEFAULT = 100.0;
    protected static final double Y_DEFAULT = 100.0;
    
    //fields
    private TWI mTWI = null;

    private Point mTileOrigin = null;

    public Point getTileOrigin() {
        return this.mTileOrigin;
    }
    
    //constructor
    public TWIStartMgr(TWI twi) {
        this.mTWI = twi;
        this.mTileOrigin = new Point(
            (int) TWIStartMgr.X_DEFAULT,
            (int) TWIStartMgr.Y_DEFAULT
        );

    }

    

    //methods 
    @Override
    public void render(Graphics2D g2, Point origin) {
        // TODO Auto-generated method stub
        
    }
}

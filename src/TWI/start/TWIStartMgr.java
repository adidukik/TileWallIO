package TWI.start;

import java.awt.Graphics2D;
import java.awt.Point;

import TWI.TWI;
import TWI.TWIRenderable;

public class TWIStartMgr implements TWIRenderable {

    private TWI mTWI = null;

    public TWIStartMgr(TWI twi) {
        this.mTWI = twi;
    }

    //methods 
    @Override
    public void render(Graphics2D g2, Point origin) {
        // TODO Auto-generated method stub
    }
}

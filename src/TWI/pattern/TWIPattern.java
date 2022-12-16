package TWI.pattern;

import java.util.ArrayList;

import TWI.TWIAnchorDot;
import TWI.geom.TWIGeom;

public interface TWIPattern extends TWIGeom {
    // interface methods
    public ArrayList<TWIAnchorDot> getAnchorDots();
    public void update();
}

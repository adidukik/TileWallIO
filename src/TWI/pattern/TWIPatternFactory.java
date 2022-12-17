package TWI.pattern;

import TWI.geom.TWIBezier;
import TWI.geom.TWIGeom;
import TWI.geom.TWILine;

public class TWIPatternFactory {
    public static TWIPattern getPattern(TWIGeom geom) {
        switch (geom) {
            case TWILine line -> {
                return new TWIPatternLine(line.getP1(), line.getP2());
            }
            case TWIBezier bezier -> {
                return new TWIPatternBezier(
                    bezier.getX1(), bezier.getY1(),
                    bezier.getCtrlX1(), bezier.getCtrlY1(),
                    bezier.getCtrlX2(), bezier.getCtrlY2(),
                    bezier.getX2(), bezier.getY2()
                );
            }
            default -> {
                throw new UnsupportedOperationException();
            }
        }
    }
}

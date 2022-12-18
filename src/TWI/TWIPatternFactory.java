package TWI;

import TWI.geom.TWIBezier;
import TWI.geom.TWIGeom;
import TWI.geom.TWILine;
import TWI.pattern.TWIPattern;
import TWI.pattern.TWIPatternBezier;
import TWI.pattern.TWIPatternLine;

public class TWIPatternFactory {
    public static TWIPattern getPattern(TWIGeom geom) {

        TWIPattern pattern;

        switch (geom) {
            case TWILine line -> {
                pattern = new TWIPatternLine(line.getP1(), line.getP2());
            }
            case TWIBezier bezier -> {
                pattern = new TWIPatternBezier(
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

        pattern.setStroke(geom.getStroke());
        pattern.setStrokeColor(geom.getStrokeColor());
        pattern.setFillColor(geom.getFillColor());
        return pattern;
    }
}

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

        if (geom instanceof TWILine) {
            TWILine line = (TWILine) geom;
            pattern = new TWIPatternLine(line.getP1(), line.getP2());
        } else if (geom instanceof TWIBezier) {
            TWIBezier bezier = (TWIBezier) geom;
            pattern = new TWIPatternBezier(
                bezier.getX1(), bezier.getY1(),
                bezier.getCtrlX1(), bezier.getCtrlY1(),
                bezier.getCtrlX2(), bezier.getCtrlY2(),
                bezier.getX2(), bezier.getY2()
            );
        } else {
            throw new UnsupportedOperationException();
        }

        pattern.setStroke(geom.getStroke());
        pattern.setStrokeColor(geom.getStrokeColor());
        pattern.setFillColor(geom.getFillColor());
        return pattern;
    }
}

package TWI.start;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import TWI.TWIClickable;
import TWI.TWIRenderable;
import TWI.geom.TWIDot;
import TWI.geom.TWIGeom;

public class TWIButton implements TWIRenderable, TWIClickable {
    
    //fields
    private BufferedImage mButton = null;
    //private File testBMP = testSquare.

    public BufferedImage getButton() {
        return this.mButton;
    }
    public TWIButton() {
        this.mButton = ImageIO.read(null)

    }
    
    
    public void render(Graphics2D g2, Point origin) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean isMousePointInside(Point pt) {
        return this.getTileGeom().getShape().contains(pt);
    }
}

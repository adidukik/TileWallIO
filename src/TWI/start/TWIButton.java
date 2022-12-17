package TWI.start;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.*;


import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import TWI.TWIClickable;
import TWI.TWIRenderable;

public class TWIButton implements TWIRenderable, TWIClickable {
    
    //fields
    private BufferedImage mSquareButton = null;
    private BufferedImage mHexagonButton = null;

    private File fileImageSquare = null;
    private File fileImageHexagon = null;

    private Rectangle squareImageBoundaries = null;
    private Rectangle hexagonImageBoundaries = null;

    public BufferedImage getSquareButton() {
        return this.mSquareButton;
    }

    public BufferedImage getHexagonButton() {
        return this.mHexagonButton;
    } 

    public Rectangle getImageBoundaries() {
        return this.imageBoundaries;
    }
    public TWIButton() {
        //this.mButton = ImageIO.read(null);
        try {
            //squares
            this.fileImageSquare = new File("src/TWI/start/squares.png");
            this.mSquareButton =  new BufferedImage(64, 64, BufferedImage.TYPE_INT_RGB);
            this.mSquareButton = ImageIO.read(fileImageSquare);
            this.mSquareButton = Scalr.resize(mSquareButton, Scalr.Method.BALANCED, 64, 64);

            //hexagons
            this.fileImageHexagon = new File("src/TWI/start/hexagons.png");
            this.mHexagonButton =  new BufferedImage(64, 64, BufferedImage.TYPE_INT_RGB);
            this.mHexagonButton = ImageIO.read(fileImageHexagon);
            this.mHexagonButton = Scalr.resize(mHexagonButton, Scalr.Method.BALANCED, 64, 64);

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.squareImageBoundaries = 
            new Rectangle(0, 0, mSquareButton.getWidth(), mSquareButton.getHeight());
        this.hexagonImageBoundaries = 
            new Rectangle(0, 0, mSquareButton.getWidth(), mSquareButton.getHeight());

    }
    public void render(Graphics2D g2, Point origin) {
        // TODO Auto-generated method stub
        //g2.drawImage(mSquareButton, null, null);
        //g2.drawImage(mHexagonButton, null, null);
        g2.drawImage(mSquareButton, 200, 200, null, null);
        g2.drawImage(mHexagonButton, 400, 200, null, null);
        g2.drawString("Please select the tesselation type", 100, 100);

    }

    //public abstract
    @Override
    public boolean isMousePointInside(Point pt) {
        return this.squareImageBoundaries.contains(pt);
    }
    
}

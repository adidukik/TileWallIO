package TWI.start;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.*;


import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import TWI.TWIRenderable;

public class TWIButtons implements TWIRenderable {
    
    //fields
    private BufferedImage mSquareButton = null;
    private BufferedImage mHexagonButton = null;
    private BufferedImage mTriangleButton = null;

    private File fileImageSquare = null;
    private File fileImageHexagon = null;
    private File fileImageTriangle = null;

    private Rectangle squareImageBoundaries = null;
    private Rectangle hexagonImageBoundaries = null;
    private Rectangle triangleImageBoundaries = null;

    //getters
    public BufferedImage getSquareButton() {
        return this.mSquareButton;
    }

    public BufferedImage getHexagonButton() {
        return this.mHexagonButton;
    } 

    public BufferedImage getTriangleButton() {
        return this.mTriangleButton;
    }

    public Rectangle getSquareImageBoundaries() {
        return this.squareImageBoundaries;
    }

    public Rectangle getHexagonImageBoundaries() {
        return this.hexagonImageBoundaries;
    }

    public Rectangle getTriangleImageBoundaries() {
        return this.triangleImageBoundaries;
    }

    public TWIButtons() {
        //handle exception as file might not exist
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

            //triangles
            this.fileImageTriangle = new File("src/TWI/start/triangles.png");
            this.mTriangleButton =  new BufferedImage(64, 64, BufferedImage.TYPE_INT_RGB);
            this.mTriangleButton = ImageIO.read(fileImageTriangle);
            this.mTriangleButton = Scalr.resize(mTriangleButton, Scalr.Method.BALANCED, 64, 64);

            //boundaries
            this.squareImageBoundaries = 
                new Rectangle(200, 300, mSquareButton.getWidth(), mSquareButton.getHeight());
            this.hexagonImageBoundaries = 
                new Rectangle(500, 300, mHexagonButton.getWidth(), mHexagonButton.getHeight());
            this.triangleImageBoundaries =
                new Rectangle(800, 300, mTriangleButton.getWidth(), mTriangleButton.getHeight());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //methods
    public void render(Graphics2D g2, Point origin) {

        //rendering buttons
        g2.drawImage(mSquareButton, 200, 300, null, null);
        g2.drawImage(mHexagonButton, 500, 300, null, null);
        g2.drawImage(mTriangleButton, 800, 300, null, null);

        //rendering label
        g2.drawString("Please select the tesselation type", 300, 100);
    }
}

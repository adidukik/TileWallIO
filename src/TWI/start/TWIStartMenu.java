package TWI.start;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import TWI.TWIRenderable;

public class TWIStartMenu implements TWIRenderable {
    // constants
    private static final String SQUARE_BUTTON_PATH =
        "src/TWI/start/squares.png";
    private static final String HEXAGON_BUTTON_PATH =
        "src/TWI/start/hexagons.png";
    private static final String TRIANGLE_BUTTON_PATH =
        "src/TWI/start/triangles.png";
    private static final Font FONT_LABEL =
        new Font("Monospaced", Font.BOLD, 24);

    private static final int X = 200;
    private static final int Y = 300;


    // fields
    private BufferedImage mSquareButton = null;
    private BufferedImage mHexagonButton = null;
    private BufferedImage mTriangleButton = null;

    private Rectangle squareImageBoundaries = null;

    public BufferedImage getSquareButton() {
        return this.mSquareButton;
    }


    private Rectangle hexagonImageBoundaries = null;

    public BufferedImage getHexagonButton() {
        return this.mHexagonButton;
    }


    private Rectangle triangleImageBoundaries = null;

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

    // constructor
    public TWIStartMenu() throws IOException {
            this.mSquareButton = readButtonImageFileName(
                TWIStartMenu.SQUARE_BUTTON_PATH
            );
            this.mHexagonButton = readButtonImageFileName(
                TWIStartMenu.HEXAGON_BUTTON_PATH
            );
            this.mTriangleButton = readButtonImageFileName(
                TWIStartMenu.TRIANGLE_BUTTON_PATH
            );

            //boundaries
            this.squareImageBoundaries =
                new Rectangle(TWIStartMenu.X, TWIStartMenu.Y, mSquareButton.getWidth(), mSquareButton.getHeight());
            this.hexagonImageBoundaries =
                new Rectangle(TWIStartMenu.X + 300, TWIStartMenu.Y, mHexagonButton.getWidth(), mHexagonButton.getHeight());
            this.triangleImageBoundaries =
                new Rectangle(TWIStartMenu.X + 500, TWIStartMenu.Y, mTriangleButton.getWidth(), mTriangleButton.getHeight());
    }

    //methods
    public void render(Graphics2D g2, Point origin) {

        //rendering buttons
        g2.drawImage(mSquareButton, TWIStartMenu.X, TWIStartMenu.Y, null, null);
        g2.drawImage(mHexagonButton, TWIStartMenu.X + 300, TWIStartMenu.Y, null, null);
        g2.drawImage(mTriangleButton, TWIStartMenu.X + 500, TWIStartMenu.Y, null, null);

        //rendering label
        g2.setFont(FONT_LABEL);
        g2.drawString("Please select the tesselation type", TWIStartMenu.X, TWIStartMenu.Y - 100);
    }

    private BufferedImage readButtonImageFileName(String path)
    throws IOException {
        File imageFile = new File(path);
        BufferedImage buttonImage = ImageIO.read(imageFile);
        buttonImage = Scalr.resize(
            buttonImage, Scalr.Method.BALANCED, 64, 64
        );

        return buttonImage;
    }
}

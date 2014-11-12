package trucktrivia.render;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author William Gulian
 * @since 11/11/2014
 */
public class PostionedImage implements IDrawable, IPositioned {

    private BufferedImage image;
    private int x, y;

    public PostionedImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public void draw(Graphics2D g, int offsetX, int offsetY) {
        g.drawImage(image, offsetX + x, offsetY + y, image.getWidth(), image.getHeight(), null);
    }

    @Override
    public void translate(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

}

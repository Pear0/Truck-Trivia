package trucktrivia.render;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * @author William Gulian
 * @since 11/6/2014
 */
public class AnimatedImage {

    private double imgsPerSecond;
    private AffineTransform transform;
    private BufferedImage[] images;
    private long startTime;

    public AnimatedImage(double animSpeed, BufferedImage... imgs) {
        if (imgs.length < 1)
            throw new IndexOutOfBoundsException("At least one image must be passed");
        imgsPerSecond = animSpeed;
        images = imgs;
        transform = new AffineTransform();
        startTime = System.currentTimeMillis();
    }

    public void startAnimation() {
        startTime = System.currentTimeMillis();
    }

    public void draw(Graphics2D g, int x, int y) {
        int index = (int) ((imgsPerSecond * System.currentTimeMillis() / 1000d) % images.length);
        draw(g, x, y, images[index].getWidth(), images[index].getHeight(), index);
    }

    public void draw(Graphics2D g, int x, int y, int width, int height) {
        int index = (int) ((imgsPerSecond * System.currentTimeMillis() / 1000d) % images.length);
        draw(g, x, y, width, height, index);
    }

    public void draw(Graphics2D g, int x, int y, int width, int height, int index) {
        AffineTransform oldTransform = g.getTransform();
        g.transform(transform);
        g.drawImage(images[index], 0, 0, width, height, null);
        g.setTransform(oldTransform);
    }

    public AffineTransform getTransform() {
        return transform;
    }

    public void setTransform(AffineTransform t) {
        transform = t;
    }

}

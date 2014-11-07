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
    private int[] animationOrder;
    private long startTime;

    public AnimatedImage(double animSpeed, BufferedImage... imgs) {
        if (imgs.length < 1)
            throw new IndexOutOfBoundsException("At least one image must be passed as an argument");
        imgsPerSecond = animSpeed;
        images = imgs;
        animationOrder = new int[images.length];
        for (int i = 0; i < animationOrder.length; i++)
            animationOrder[i] = i;
        transform = new AffineTransform();
        startTime = System.currentTimeMillis();
    }

    public void startAnimation() {
        startTime = System.currentTimeMillis();
    }

    public void draw(Graphics2D g, int x, int y) {
        int index = getIndex();
        draw(g, x, y, images[index].getWidth(), images[index].getHeight(), index);
    }

    public void draw(Graphics2D g, int x, int y, int width, int height) {
        int index = getIndex();
        draw(g, x, y, width, height, index);
    }

    public void draw(Graphics2D g, int x, int y, int width, int height, int index) {
        AffineTransform oldTransform = g.getTransform();
        g.translate(x, y);
        g.transform(transform);
        g.drawImage(images[animationOrder[index]], 0, 0, width, height, null);
        g.setTransform(oldTransform);
    }

    public AffineTransform getTransform() {
        return transform;
    }

    public void setTransform(AffineTransform t) {
        transform = t;
    }

    private int getIndex() {
        return (int) ((imgsPerSecond * (System.currentTimeMillis() - startTime) / 1000d) % animationOrder.length);
    }

    public int[] getAnimationOrder() {
        return animationOrder;
    }

    public void setAnimationOrder(int[] animationOrder) {
        this.animationOrder = animationOrder;
    }

}

package trucktrivia.render;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * @author William Gulian
 * @since 11/6/2014
 */
public class AnimatedImage implements IDrawable, IPositioned {

    private double imgsPerSecond;
    private AffineTransform transform;
    private BufferedImage[] images;
    private int[] animationOrder;
    private long startTime;
    private int stopIndex;
    private boolean isStopped;
    private int x, y;

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
        stopIndex = -1;
    }

    public void startAnimation() {
        stopIndex = -1;
        isStopped = false;
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
        g.translate(this.x + x, this.y + y);
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
        int index = (int) ((imgsPerSecond * (System.currentTimeMillis() - startTime) / 1000d) % animationOrder.length);
        if (stopIndex == -1) {
            if (isStopped)
                startAnimation();
        } else if (index == stopIndex)
            isStopped = true;
        if (isStopped)
            index = stopIndex;
        return index;
    }

    public void stopAnimation() {
        stopAtIndex(0);
    }

    public void stopAtIndex(int index) {
        stopAtIndex(index, false);
    }

    /**
     * This method stops the animation at an index
     *
     * @param index       is the index to stop at
     * @param jumpToIndex is whether or not animation should continue until the index is reached or just jump to it.
     */
    public void stopAtIndex(int index, boolean jumpToIndex) {
        stopIndex = index;
        isStopped = jumpToIndex;
    }

    public int[] getAnimationOrder() {
        return animationOrder;
    }

    public void setAnimationOrder(int[] animationOrder) {
        this.animationOrder = animationOrder;
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

    public int getWidth() {
        return images[0].getWidth();
    }

    public int getHeight() {
        return images[0].getHeight();
    }

}

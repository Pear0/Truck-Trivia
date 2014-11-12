package trucktrivia.render;

import java.awt.*;

/**
 * Use this wrap objects that implement IDrawable but not IPositioned
 *
 * @author William Gulian
 * @since 11/11/2014
 */
public class Positioned<TYPE extends IDrawable> implements IPositioned, IDrawable {

    private int x, y;

    private TYPE drawable;

    public Positioned(TYPE drawable) {
        this.drawable = drawable;
    }

    @Override
    public void draw(Graphics2D g, int offsetX, int offsetY) {
        drawable.draw(g, offsetX + x, offsetY + y);
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

    public TYPE getDrawable() {
        return drawable;
    }

    public void setDrawable(TYPE drawable) {
        this.drawable = drawable;
    }

}

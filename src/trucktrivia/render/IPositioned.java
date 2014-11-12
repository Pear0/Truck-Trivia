package trucktrivia.render;

/**
 * @author William Gulian
 * @since 11/11/2014
 */
public interface IPositioned {

    public void translate(int x, int y);

    public void setX(int x);

    public void setY(int y);

    public int getX();

    public int getY();

}

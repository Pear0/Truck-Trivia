package trucktrivia.render;

/**
 * @author William Gulian
 * @since 11/6/2014
 */
public class Test {

    public static void main(String[] args) {
        Positioned<AnimatedImage> img = new Positioned<AnimatedImage>(new AnimatedImage(20,
                Resources.loadImage("numbers/0.png"),
                Resources.loadImage("numbers/1.png"),
                Resources.loadImage("numbers/2.png"),
                Resources.loadImage("numbers/3.png"),
                Resources.loadImage("numbers/4.png"),
                Resources.loadImage("numbers/5.png"),
                Resources.loadImage("numbers/6.png"),
                Resources.loadImage("numbers/7.png"),
                Resources.loadImage("numbers/8.png"),
                Resources.loadImage("numbers/9.png"),
                Resources.loadImage("numbers/10.png")));

        DrawCanvas canvas = new DrawCanvas(true);
        canvas.addDrawable(img);

        new SimpleWindow("Test", 720, 560, null, canvas);

    }

}

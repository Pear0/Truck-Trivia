package trucktrivia.render;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

/**
 * @author William Gulian
 * @since 11/10/2014
 */
public class DrawCanvas extends Canvas {

    private volatile boolean isRunning = false;
    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (isRunning) {
                BufferStrategy buf = getBufferStrategy();
                if (buf == null) {
                    createBufferStrategy(2);
                    continue;
                }
                Graphics2D g = (Graphics2D) buf.getDrawGraphics();
                render(g);
                g.dispose();
                buf.show();
            }
        }
    });

    private ArrayList<Drawable> drawables = new ArrayList<Drawable>();

    public synchronized void render(Graphics2D g) {
        for (Drawable drawable : drawables)
            drawable.draw(g, 0, 0);
    }

    public synchronized void start() {
        isRunning = true;
        thread.start();
    }

    public synchronized void stop() {
        isRunning = false;
    }

    public ArrayList<Drawable> getDrawables() {
        return drawables;
    }

    public void setDrawables(ArrayList<Drawable> drawables) {
        this.drawables = drawables;
    }

}

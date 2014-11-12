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
                    if (isDisplayable())
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

    public DrawCanvas() {
        this(false);
    }

    public DrawCanvas(boolean autoStart) {
        if (autoStart)
            start();
    }

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

    public void addDrawable(Drawable drawable) {
        this.drawables.add(drawable);
    }

    public void addDrawables(Drawable... drawables) {
        this.drawables.ensureCapacity(this.drawables.size() + drawables.length);
        for (Drawable drawable : drawables)
            addDrawable(drawable);
    }

    public void setDrawables(ArrayList<Drawable> drawables) {
        this.drawables = drawables;
    }

    public void setDrawables(Drawable... drawables) {
        this.drawables.clear();
        addDrawables(drawables);
    }

}

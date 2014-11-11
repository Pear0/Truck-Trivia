package trucktrivia.render;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * @author William Gulian
 * @since 11/10/2014
 */
public class Window {

    private JFrame frame;
    private WindowEventHandler eventHandler;


    public Window(String title, int width, int height, final WindowEventHandler eventHandler) {
        frame = new JFrame();
        frame.setSize(width, height);
        frame.setTitle(title);
        this.eventHandler = eventHandler;
        this.eventHandler.setWindow(this);
        frame.addWindowListener(new WindowClosingListener());

        this.eventHandler.onCompleted();
    }

    public JFrame getFrame() {
        return frame;
    }

    public static interface WindowEventHandler {

        public void onCompleted();

        public void windowClosing();

        public void setWindow(Window w);

    }

    private class WindowClosingListener implements WindowListener {
        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            if (eventHandler != null)
                eventHandler.windowClosing();
            else {
                System.err.println("windowClosing ran but eventHandler is null");
                new Exception().printStackTrace();
            }
        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    }

}

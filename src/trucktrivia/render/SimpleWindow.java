package trucktrivia.render;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * @author William Gulian
 * @since 11/10/2014
 */
public class SimpleWindow {

    private JFrame frame;
    private WindowEventHandler eventHandler;

    public SimpleWindow(String title, int width, int height) {
        this(title, width, height, null);
    }

    public SimpleWindow(String title, int width, int height, WindowEventHandler eventHandler, Component... components) {
        if (eventHandler == null)
            eventHandler = new WindowEventHandler() {
                @Override
                public void onCompleted() {
                }
            };

        frame = new JFrame();
        frame.setSize(width, height);
        frame.setTitle(title);
        this.eventHandler = eventHandler;
        this.eventHandler.setWindow(this);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowClosingListener());

        for (Component component : components)
            frame.add(component);

        this.eventHandler.onCompleted();
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public static abstract class WindowEventHandler {
        protected SimpleWindow window;

        public abstract void onCompleted();

        /**
         * Executed when the window's close button is pressed
         *
         * @return whether or not to exit the program immediately
         */
        public boolean windowClosing() {
            return true;
        }

        public void setWindow(SimpleWindow w) {
            window = w;
        }

    }

    private class WindowClosingListener implements WindowListener {
        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            if (eventHandler != null)
                if (eventHandler.windowClosing())
                    System.exit(0);
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

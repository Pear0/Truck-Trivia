package trucktrivia.render;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * @author William Gulian
 * @since 11/6/2014
 */
public class Resources {

    public static BufferedImage loadImage(String str) {
        try {
            URL url = Resources.class.getResource("/" + str);
            return ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

package trucktrivia.render;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author William Gulian
 * @since 11/6/2014
 */
public class Resources {

    private static Map<String, String> aliases = new HashMap<String, String>();
    private static Map<String, BufferedImage> cache = new HashMap<String, BufferedImage>();

    /**
     * Loads an image by its file name or its alias
     *
     * @param name the file name or alias
     *             An alias can only be used if it has been set up with loadWithAlias
     * @return the image or null if there is an error
     */
    public static BufferedImage get(String name) {
        if (aliases.containsKey(name))
            name = aliases.get(name);
        if (!cache.containsKey(name)) {
            BufferedImage img = loadImage(name);
            if (img != null)
                cache.put(name, img);
        }
        return cache.containsKey(name) ? cache.get(name) : null;
    }

    /**
     * Identical to get but returns whether the image has been loaded
     *
     * @param name is the file name to load and cache for future use, and name cannot be an alias.
     * @return whether loading was successful or true if the image has already been loaded
     */
    public static boolean load(String name) {
        if (cache.containsKey(name))
            return true;
        else
            return get(name) != null;
    }

    /**
     * Identical to load but assigns an alias as well
     *
     * @param name  is the file name to load and cache for future use, and name cannot be an alias.
     * @param alias is the alias to that will reference the image.
     *              the alias must not have forward slashes or dots.
     * @return whether loading was successful or true if the image has already been
     * loaded and the alias is assigned
     */
    public static boolean loadWithAlias(String name, String alias) {
        if (alias.contains("/") || alias.contains("."))
            return false;

        if (!load(name))
            return false;

        if (!aliases.containsKey(alias)) {
            aliases.put(alias, name);
            return true;
        } else if (aliases.get(alias) == name)
            return true;
        else {
            System.err.print("The alias \"" + alias + "\" has already been ");
            System.err.println("assigned to \"" + aliases.get(alias) + "\"");
            return false;
        }
    }

    /**
     * Removes an alias but not the image itself
     *
     * @param alias is the alias to remove
     * @return whether the alias was removed
     */
    public static boolean removeAlias(String alias) {
        if (aliases.containsKey(alias)) {
            aliases.remove(alias);
            return true;
        }
        return false;
    }

    /**
     * Removes the image with name, name.
     * If name is an alias, it will remove the alias too.
     * This method may leave an alias that has its image not cached.
     * If this happens, when the alias is called, the image will be reloaded.
     *
     * @param name the name or alias to remove.
     * @return whether removing the image was successful.
     */
    public static boolean unload(String name) {
        if (aliases.containsKey(name)) {
            String alias = aliases.get(name);
            removeAlias(name);
            name = alias;
        }
        if (cache.containsKey(name)) {
            cache.remove(name);
            return true;
        }
        return false;
    }

    private static BufferedImage loadImage(String str) {
        try {
            URL url = Resources.class.getResource("/" + str);
            return ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

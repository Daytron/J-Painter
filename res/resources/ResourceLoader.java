
/**
 * File: ResourceLoader.java
 * 
 * Description: A utility class for returning the path of the image resources.
 */

package resources;

import java.awt.Image;
import java.awt.Toolkit;

/**
 * ResourceLoader class
 * 
 * Purpose: Using the Toolkit class, to extract the path of the image
 * resources.
 * 
 * @author Ryan Gilera <jalapaomaji-github@yahoo.com>
 */
public class ResourceLoader {

    private static final ResourceLoader resLoad = new ResourceLoader();

    public static Image getImage(String path) {
        return Toolkit.getDefaultToolkit()
                .getImage(resLoad.getClass().getResource("img/" + path));
    }

    public static Toolkit getDefaultToolkit() {
        return Toolkit.getDefaultToolkit();
    }

}

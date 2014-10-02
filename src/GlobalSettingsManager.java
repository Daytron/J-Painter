/**
 * File: GlobalSettingsManager.java
 *
 * Description: The utility class for all resources and settings.
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import resources.ResourceLoader;

/**
 * The class responsible for managing all application resources such as global
 * variables and images. The class is made final so it cannot be inherited by
 * other class. The methods are static to stop instantiating the class.
 *
 * @author Ryan Gilera <jalapaomaji-github@yahoo.com>
 */
final class GlobalSettingsManager {

    // FRAME SIZE
    public static final int FRAME_WIDTH = 1100;
    public static final int FRAME_HEIGHT = 790;

    // CANVAS SIZE
    public static final int CANVAS_WIDTH = 1100;
    public static final int CANVAS_HEIGHT = 611;

    // APPLICATION ICON
    public static final Image APP_ICON
            = ResourceLoader.getImage("appIcon.png");

    // STATUS BAR SIZE
    // Width is set to 0, means inherit width from JFrame.
    // Height is set to 40px.
    public static final Dimension STATUS_DIMENSION
            = new Dimension(0, 40);

    // MENU ICON IMAGES
    // Gets all image resources for the menu item pulled
    //      by the ResourceLoader class reside in the Resource folder.
    public static final ImageIcon FILE_NEW_ICON
            = new ImageIcon(ResourceLoader.getImage("fileNew.png"));
    public static final ImageIcon FILE_EXIT_ICON
            = new ImageIcon(ResourceLoader.getImage("fileExit.png"));
    public static final ImageIcon EDIT_CLEAR_ICON
            = new ImageIcon(ResourceLoader.getImage("editClear.png"));
    public static final ImageIcon ABOUT_ICON
            = new ImageIcon(ResourceLoader.getImage("about.png"));

    // TOOLBAR ICON IMAGES
    public static final ImageIcon TOOL_DRAW_ICON
            = new ImageIcon(ResourceLoader.getImage("toolPencil.png"));
    public static final ImageIcon TOOL_CLEAR_CANVAS_ICON
            = new ImageIcon(ResourceLoader.getImage("toolClear.png"));
    public static final ImageIcon TOOL_UNDO_ICON
            = new ImageIcon(ResourceLoader.getImage("toolUndo.png"));
    public static final ImageIcon TOOL_REDO_ICON
            = new ImageIcon(ResourceLoader.getImage("toolRedo.png"));
    public static final ImageIcon TOOL_IMAGE_ICON
            = new ImageIcon(ResourceLoader.getImage("toolImage.png"));
    public static final ImageIcon TOOL_DRAWABLE_LINE_ICON
            = new ImageIcon(ResourceLoader.getImage("line.png"));
    public static final ImageIcon TOOL_DRAWABLE_SQUARE_ICON
            = new ImageIcon(ResourceLoader.getImage("square.png"));
    public static final ImageIcon TOOL_DRAWABLE_CIRCLE_ICON
            = new ImageIcon(ResourceLoader.getImage("circle.png"));
    public static final ImageIcon TOOL_DRAWABLE_ELLIPSE_ICON
            = new ImageIcon(ResourceLoader.getImage("ellipse.png"));
    public static final ImageIcon TOOL_DRAWABLE_TRIANGLE_ICON
            = new ImageIcon(ResourceLoader.getImage("triangle.png"));
    public static final ImageIcon TOOL_DRAWABLE_ARC_ICON
            = new ImageIcon(ResourceLoader.getImage("arc.png"));

    // DEFAULT COLOR CHOOSER SELECTED COLOR
    public static final Color COLOR_CHOOSER_DEFAULT = Color.BLACK;

    // LINE THICKNESS MENU ITEM IMAGES
    public static final ImageIcon LINE_THICK_0
            = new ImageIcon(ResourceLoader.getImage("thickLineItem/px1.png"));
    public static final ImageIcon LINE_THICK_1
            = new ImageIcon(ResourceLoader.getImage("thickLineItem/px2.png"));
    public static final ImageIcon LINE_THICK_2
            = new ImageIcon(ResourceLoader.getImage("thickLineItem/px3.png"));
    public static final ImageIcon LINE_THICK_3
            = new ImageIcon(ResourceLoader.getImage("thickLineItem/px4.png"));
    public static final ImageIcon LINE_THICK_4
            = new ImageIcon(ResourceLoader.getImage("thickLineItem/px5.png"));

    // CURSOR CUSTOM IMAGE
    public static final Image CURSOR_ICON
            = ResourceLoader.getImage("cursor.png");

    // Class constructor.
    // Stops from instantiating this class.
    private GlobalSettingsManager() {
    }
}

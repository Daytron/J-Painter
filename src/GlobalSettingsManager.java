
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
    private static final int FRAME_WIDTH = 1100;
    private static final int FRAME_HEIGHT = 790;

    // CANVAS SIZE
    private static final int CANVAS_WIDTH = 1100;
    private static final int CANVAS_HEIGHT = 611;

    // APPLICATION ICON
    private static final Image APP_ICON
            = ResourceLoader.getImage("appIcon.png");

    // STATUS BAR SIZE
    // Width is set to 0, means inherit width from JFrame.
    // Height is set to 40px.
    private static final Dimension STATUS_DIMENSION
            = new Dimension(0, 40);

    // MENU ICON IMAGES
    // Gets all image resources for the menu item pulled
    //      by the ResourceLoader class reside in the Resource folder.
    private static final ImageIcon FILE_NEW_ICON
            = new ImageIcon(ResourceLoader.getImage("fileNew.png"));
    private static final ImageIcon FILE_EXIT_ICON
            = new ImageIcon(ResourceLoader.getImage("fileExit.png"));
    private static final ImageIcon EDIT_CLEAR_ICON
            = new ImageIcon(ResourceLoader.getImage("editClear.png"));
    private static final ImageIcon ABOUT_ICON
            = new ImageIcon(ResourceLoader.getImage("about.png"));

    // TOOLBAR ICON IMAGES
    private static final ImageIcon TOOL_DRAW_ICON
            = new ImageIcon(ResourceLoader.getImage("toolPencil.png"));
    private static final ImageIcon TOOL_CLEAR_CANVAS_ICON
            = new ImageIcon(ResourceLoader.getImage("toolClear.png"));
    private static final ImageIcon TOOL_UNDO_ICON
            = new ImageIcon(ResourceLoader.getImage("toolUndo.png"));
    private static final ImageIcon TOOL_REDO_ICON
            = new ImageIcon(ResourceLoader.getImage("toolRedo.png"));
    private static final ImageIcon TOOL_IMAGE_ICON
            = new ImageIcon(ResourceLoader.getImage("toolImage.png"));
    private static final ImageIcon TOOL_DRAWABLE_LINE_ICON
            = new ImageIcon(ResourceLoader.getImage("line.png"));
    private static final ImageIcon TOOL_DRAWABLE_SQUARE_ICON
            = new ImageIcon(ResourceLoader.getImage("square.png"));
    private static final ImageIcon TOOL_DRAWABLE_CIRCLE_ICON
            = new ImageIcon(ResourceLoader.getImage("circle.png"));
    private static final ImageIcon TOOL_DRAWABLE_ELLIPSE_ICON
            = new ImageIcon(ResourceLoader.getImage("ellipse.png"));
    private static final ImageIcon TOOL_DRAWABLE_TRIANGLE_ICON
            = new ImageIcon(ResourceLoader.getImage("triangle.png"));
    private static final ImageIcon TOOL_DRAWABLE_ARC_ICON
            = new ImageIcon(ResourceLoader.getImage("arc.png"));
    
    // DEFAULT COLOR CHOOSER SELECTED COLOR
    private static final Color COLOR_CHOOSER_DEFAULT = Color.BLACK;

    // LINE THICKNESS MENU ITEM IMAGES
    private static final ImageIcon LINE_THICK_0
            = new ImageIcon(ResourceLoader.getImage("thickLineItem/px1.png"));
    private static final ImageIcon LINE_THICK_1
            = new ImageIcon(ResourceLoader.getImage("thickLineItem/px2.png"));
    private static final ImageIcon LINE_THICK_2
            = new ImageIcon(ResourceLoader.getImage("thickLineItem/px3.png"));
    private static final ImageIcon LINE_THICK_3
            = new ImageIcon(ResourceLoader.getImage("thickLineItem/px4.png"));
    private static final ImageIcon LINE_THICK_4
            = new ImageIcon(ResourceLoader.getImage("thickLineItem/px5.png"));

    // CURSOR CUSTOM IMAGE
    private static final Image CURSOR_ICON
            = ResourceLoader.getImage("cursor.png");

    // Class constructor.
    // Stops from instantiating this class.
    private GlobalSettingsManager() {
    }

    // The methods below are self explanatory, thier only purpose is to share
    //      the values of the global variables.
    // Each method name gives a hint of which global variable is being return.
    public static final int getFrameHeight() {
        return FRAME_HEIGHT;
    }

    public static final int getFrameWidth() {
        return FRAME_WIDTH;
    }

    public static final int getCanvasHeight() {
        return CANVAS_HEIGHT;
    }

    public static final int getCanvasWidth() {
        return CANVAS_WIDTH;
    }

    public static final Image getAppIcon() {
        return APP_ICON;
    }

    public static final Dimension getStatusDimension() {
        return STATUS_DIMENSION;
    }

    public static final ImageIcon getFileNewIcon() {
        return FILE_NEW_ICON;
    }

    public static final ImageIcon getFileExitIcon() {
        return FILE_EXIT_ICON;
    }

    public static final ImageIcon getEditClearIcon() {
        return EDIT_CLEAR_ICON;
    }

    public static final ImageIcon getAboutIcon() {
        return ABOUT_ICON;
    }

    public static final ImageIcon getToolDrawIcon() {
        return TOOL_DRAW_ICON;
    }

    public static final ImageIcon getToolClearIcon() {
        return TOOL_CLEAR_CANVAS_ICON;
    }

    public static final ImageIcon getToolUndoIcon() {
        return TOOL_UNDO_ICON;
    }

    public static final ImageIcon getToolRedoIcon() {
        return TOOL_REDO_ICON;
    }
    
    public static final ImageIcon getToolImageIcon() {
        return TOOL_IMAGE_ICON;
    }
    
    public static final ImageIcon getToolDrawableLineIcon(){
        return TOOL_DRAWABLE_LINE_ICON;
    }
    
    public static final ImageIcon getToolDrawableSquareIcon(){
        return TOOL_DRAWABLE_SQUARE_ICON;
    }
    
    public static final ImageIcon getToolDrawableCircleIcon(){
        return TOOL_DRAWABLE_CIRCLE_ICON;
    }
    
    public static final ImageIcon getToolDrawableEllipseIcon(){
        return TOOL_DRAWABLE_ELLIPSE_ICON;
    }
    
    public static final ImageIcon getToolDrawableTriangleIcon(){
        return TOOL_DRAWABLE_TRIANGLE_ICON;
    }
    
    public static final ImageIcon getToolDrawableArcIcon(){
        return TOOL_DRAWABLE_ARC_ICON;
    }

    public static final Color getDefaultColorChooserColor() {
        return COLOR_CHOOSER_DEFAULT;
    }

    public static final ImageIcon getLineThickImage0() {
        return LINE_THICK_0;
    }

    public static final ImageIcon getLineThickImage1() {
        return LINE_THICK_1;
    }

    public static final ImageIcon getLineThickImage2() {
        return LINE_THICK_2;
    }

    public static final ImageIcon getLineThickImage3() {
        return LINE_THICK_3;
    }

    public static final ImageIcon getLineThickImage4() {
        return LINE_THICK_4;
    }

    public static final Image getCursorImage() {
        return CURSOR_ICON;
    }

}

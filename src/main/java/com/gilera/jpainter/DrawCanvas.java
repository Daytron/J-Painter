package com.gilera.jpainter;


/**
 * File: DrawCanvas.java
 * 
 * Description: The class responsible for drawing Drawable object on the canvas.
 * This class is subclass of the JPanel class.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * DrawCanvas class
 * 
 * Purpose: Provides the canvas and its drawing mechanism. Updates status bar
 * for all canvas activities. Manage Drawable objects drawn on the canvas.
 *
 * @author Ryan Gilera <jalapaomaji-github@yahoo.com>
 */
public class DrawCanvas extends JPanel{

    /**
     * Object variables declarations
     * <ul>
     * <li>listOfDrawables - list of Drawable objects to draw on the canvas.</li>
     * <li>listOfClearedDrawables - list of "erased" Drawables that can be 
     * redrawn using redo</li>
     * <li>startDraw - flag for determining weather to draw or not. keeps from
     * drawing early with assigning any endpoints.</li>
     * <li>statusBar - a JLabel object for the status bar information. Made as
     * an object variable because it's instantiation is created by the canvas,
     * so that canvas can easily update the string for new status.</li>
     * <li>mousePosBar - a JLabel object for displaying current mouse position
     * on the canvas.</li>
     * <li>bufferImage - an Image object used to buffer all drawing process
     * before drawing it on the canvas.</li>
     * </ul>
     */
    private List<Drawable> listOfDrawables;
    private List<Drawable> listOfClearedDrawables;
    private boolean startDraw;
    private final JLabel statusBar;
    private final JLabel mousePosBar;
    private final Image bufferImage;

    /**
     * Class constructor with no arguments. Initialise all object variables.
     */
    public DrawCanvas() {
        this.startDraw = false;
        this.setBackground(Color.WHITE);
        this.listOfDrawables = new ArrayList<>();
        this.listOfClearedDrawables = new ArrayList<>();
        this.statusBar = new JLabel("  Ready to draw..");
        this.mousePosBar = new JLabel(" ");
        this.bufferImage = new BufferedImage(
                GlobalSettingsManager.CANVAS_WIDTH,
                GlobalSettingsManager.CANVAS_HEIGHT,
                BufferedImage.TYPE_INT_ARGB);
    }

    /**
     * Returns the JLabel object, to be used for layout positioning of the
     * object in the UserInteface class.
     *
     * @return JLabel object
     */
    public JLabel getStatusBar() {
        return statusBar;
    }

    /**
     * Returns the JLabel object, to be used for layout positioning of the
     * object.
     *
     * @return JLabel object
     */
    public JLabel getMousePosBar() {
        return this.mousePosBar;
    }

    /**
     * Displays the status of the JLabel object.
     *
     * @param s a String object to display as the status.
     */
    public void setStatus(String s) {
        this.statusBar.setText(s);
    }

    /**
     * Displays the current position of the mouse on the canvas.
     *
     * @param x X-coordinate
     * @param y Y-coordinate
     */
    public void setPosStatus(int x, int y) {
        this.mousePosBar.setText(x + "," + y + "  ");
    }

    /**
     * Allows to easily remove the last Drawable drawn. The line is removed 
     * from the listOfDrawables and then added to listOfClearedDrawables.
     */
    public void undo() {
        if (!this.listOfDrawables.isEmpty()) {
            this.listOfClearedDrawables.add(
                    this.listOfDrawables.remove(this.listOfDrawables.size() - 1));
        }
    }

    /**
     * Allows to easily redraw the last deleted or cleared Drawable. 
     * The Drawable is remove from the listOfClearedDrawables and then added 
     * to the listOfDrawables.
     */
    public void redo() {
        if (!this.listOfClearedDrawables.isEmpty()) {
            this.listOfDrawables.add(
                    this.listOfClearedDrawables.remove(this.listOfClearedDrawables.size() - 1));
        }
    }

    /**
     * Allows the generated Drawables to be drawn on the canvas.
     *
     * @param graphics Graphics object created by the system.
     */
    @Override
    public void paintComponent(Graphics graphics) {
        // Controls when canvas needs to be drawn onto.
        if (this.startDraw) {
            // Get the reference of graphics ovject and pass it to screengc.
            Graphics screengc = graphics;

            // Get the graphics instead from the bufferImage.
            graphics = this.bufferImage.getGraphics();
            // Sets the drawing area of the bufferImage.
            graphics.fillRect(0, 0, GlobalSettingsManager.CANVAS_WIDTH, 
                    GlobalSettingsManager.CANVAS_HEIGHT);

            // Iterates the list of Drawables then draw it on the bufferImage.
            for (Drawable aShape : this.listOfDrawables) {
                aShape.drawShape(graphics);
            }

            // Draws the buffer image onto the JPanel.
            screengc.drawImage(this.bufferImage, 0, 0, null);

            // Afterwards dispose all graphic contents to save memory.
            graphics.dispose();
        }
    }

    /**
     * Adds new Drawables to the listOfDrawables ArrayList. New Drawable 
     * object is created inside the MouseClickListener class.
     *
     * @param shape A Bresenham shape object.
     */
    public void addShape(Drawable shape) {
        this.listOfDrawables.add(shape);
    }

    /**
     * Triggers the canvas to start drawing any Drawable added to 
     * the listOfDrawables.
     */
    public void activateDraw() {
        this.startDraw = true;
        this.repaint();
    }

    
    
    /**
     * Simply clears the ArrayList object to clear the drawings. The Drawable
     * objects listOfDrawables are simply added to the listOfClearedDrawables. 
     * This allows to undo the changes later on. To completely delete all 
     * Drawables, the user must trigger the File > New menu.
     */
    public void clearCanvas() {
        this.listOfClearedDrawables.addAll(this.listOfDrawables);
        this.listOfDrawables = new ArrayList<>();
    }

    /**
     * Simply deletes all Drawable objects from the two ArrayList objects by
     * creating a new ArrayList objects. This is triggered when user 
     * clicks File-New menu.
     */
    public void startANew() {
        this.listOfDrawables = new ArrayList<>();
        this.listOfClearedDrawables = new ArrayList<>();
    }

    /**
     * Calculates the necessary thickness from the given MenuItem index 
     * from the Line thickness combo box.
     *
     * @param t Integer index of the ComboBox MenuItem
     * @return actual thickness in pixels.
     */
    public int getThickness(int t) {
        int thickness = 1;

        switch (t) {
            case 0:
                thickness = 1;
                break;
            case 1:
                thickness = 4;
                break;
            case 2:
                thickness = 6;
                break;
            case 3:
                thickness = 9;
                break;
            case 4:
                thickness = 14;
                break;
        }

        return thickness;
    }

}

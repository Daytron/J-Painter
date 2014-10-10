package com.gilera.jpainter;


/**
 * File: MouseClickListener.java
 * 
 * Description: The class responsible for listening and implementing the
 * mouseclick events.
 */
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * MouseClickListener class
 * 
 * Purpose: Provides implementation of all mouseclick events involve. The class
 * is a subclass of the MouseAdapter, a class that implements MouseListener,
 * MouseMotionListener and MouseWheelListener.
 *
 * @author Ryan Gilera <jalapaomaji-github@yahoo.com>
 */
public class MouseClickListener extends MouseAdapter {

    /**
     * Object variables declarations.
     * <ul>aCanvas - The main canvas object.
     * <li>aMap - A HashMap object for storing the first endpoint of the
     * line.</li>
     * <li>currentColor - The colour to apply to the line.</li>
     * <li>lineThickness - The thickness to apply to the line.</li>
     * <li>isFootprintSquare - Helper variable for determining the
     * footprint.</li>
     * <li>counter - A simple incremental counter for determining how many times
     * the user has click since Draw button is pressed. It is used to
     * distinguish the endpoints of the line.</li>
     * <li>inDragMode - A helper variable for determining if the mouse is being
     * dragged.</li>
     * <li>cursorDragOn - A helper variable for determining when to revert the
     * cursor back to its original icon.</li>
     * <li>shape - A Drawable object that is about to be drawn</li>
     * <li>drawableSelector - helper variable to distinguish which Drawable
     * object is to draw</li>
     * <li>imageLoader used to hold image to draw into the canvas</li>
     * </ul>
     */
    private final DrawCanvas aCanvas;
    private final Map<String, Integer> aMap;
    private final Color currentColor;
    private final int lineThickness;
    private final boolean isFootprintSquare;
    private int counter;
    private boolean inDragMode;
    private boolean cursorDragOn;
    private Drawable shape;
    private final int drawableSelector;
    private Image imageLoader;

    /**
     * Class constructor, initialising all object variables.
     *
     * @param aCanvas The canvas.
     * @param aColor The colour of the shape.
     * @param thick The thickness of the shape.
     * @param footprint The footprint type.
     * @param drawableSelector The type of Drawable object to draw
     */
    public MouseClickListener(DrawCanvas aCanvas, Color aColor,
            int thick, boolean footprint, int drawableSelector) {
        this.aCanvas = aCanvas;
        this.aMap = new HashMap<>();
        this.counter = 0;
        this.currentColor = aColor;
        this.lineThickness = thick;
        this.isFootprintSquare = footprint;
        this.cursorDragOn = false;
        this.drawableSelector = drawableSelector;
    }

    /**
     * <p>
     * Implements the necessary actions when the mouse is pressed on canvas. If
     * the mouse is pressed on canvas for the first time after the Draw button
     * is pressed, It initially records the first point of the Drawable object
     * to a HashMap object. Then it sets the flag helper inDragMode to true,
     * there is a possibility that the user will drag mouse instead of releasing
     * it on the same coordinate; to be use in mouseDragged method. Then it
     * creates a new Drawable object and add it to the ArrayList listOfDrawables
     * in the DrawCanvas object, waiting for the other endpoint from the user.
     * Then updates the status in the status bar.</p>
     * <p>
     * If the user click on a canvas for the canvas, it means that user intends
     * to draw by clicking the points instead of dragging. The a new Drawable is
     * created based on two clicks position made by the user</p>
     * <p>
     * Otherwise, remove all listeners involved in the canvas since Drawable is
     * already drawn on the canvas.</p>
     *
     * @param e - The MouseEvent object
     */
    @Override
    public void mousePressed(MouseEvent e) {

        // if the counter is zero, means that it is the first time the mouse
        //      is clicked, since Draw button is clicked.
        if (this.counter == 0) {
            // Grabs the location at this mousclick
            int x = e.getX();
            int y = e.getY();

            // Sets helper variable to true for a potential drag movement.
            this.inDragMode = true;

            // Records the position into a HashMap object.
            this.aMap.put("xi", x);
            this.aMap.put("yi", y);

            // Computes the actual thickness from the index of the line
            //      combo box.
            int trueThickness = this.aCanvas.getThickness(lineThickness);

            // Creates a new Drawable object depending on the drawableSelector,
            //      for now the two endpoints are the same while waiting 
            //      for the 2nd mouseclick (counter == 1)
            switch (this.drawableSelector) {
                case 1:
                    this.shape = new DrawableLine(x, y, x, y,
                            trueThickness, this.currentColor,
                            this.isFootprintSquare);
                    break;
                case 2:
                    this.shape = new DrawableCircle(x, y, x, y,
                            trueThickness, currentColor,
                            this.isFootprintSquare);
                    break;

                case 3:
                    this.shape = new DrawableSquare(x, y, x, y,
                            trueThickness, currentColor,
                            this.isFootprintSquare);
                    break;

                case 4:
                    this.shape = new DrawableEllipse(x, y, x, y,
                            trueThickness, this.currentColor,
                            this.isFootprintSquare);
                    break;
                case 5:
                    this.shape = new DrawableTriangle(x, y, x, y,
                            trueThickness, this.currentColor,
                            this.isFootprintSquare);
                    break;

                case 6:
                    this.shape = new DrawableArc(x, y, x, y,
                            trueThickness, this.currentColor,
                            this.isFootprintSquare);
                    break;
                case 7:
                    this.shape = new DrawableImage(x, y, x, y,
                            trueThickness, this.currentColor,
                            this.isFootprintSquare, this.imageLoader);
                    break;
            }

            // Add this Drawable object to the list of Drawables in the canvas.
            this.aCanvas.addShape(this.shape);

            // Updates the status - current position selected.
            this.aCanvas.setStatus("  [" + x + "," + y + "]");
        }

        // If counter hits to 1, it means that the mouse has been clicked
        //      twice by now. It will now be able to draw
        //      the Drawables from previous (x,y) to this now 
        //      new position (x,y), by upating the endpoint of the 
        //      Drawables and repaint it by calling the 
	//	updateEndpoints(x, y) method of the canvas.
        if (this.counter == 1) {
            // Grabs the location at this mouseclick
            int x = e.getX();
            int y = e.getY();

            // Records the position into a HashMap object.
            this.aMap.put("xn", x);
            this.aMap.put("yn", y);

            // Calls the method for updating the endpoints and repaint it.
            this.updateEndpoints(x, y);
            // Calls the method for updating the status and the cursor.
            this.updateStatusAndCursor();
        }

        // Otherwise, remove all listeners from the canvas object.
        if (this.counter > 1) {
            // getMouseListeners returns an array of listeners
            // if array's length is > 0, means there's at least one listener 
            //      in the canvas
            if (this.aCanvas.getMouseListeners().length > 0) {
                this.aCanvas.removeMouseListener(this);
                this.aCanvas.removeMouseMotionListener(this);
            }
        }
    }

    /**
     * Add image to the imageLoader.
     *
     * @param imageLoad The object variable that holds the image to draw.
     */
    public void setImage(Image imageLoad) {
        this.imageLoader = imageLoad;
    }

    /**
     * Implements the necessary actions when the mouse is released from being
     * pressed. Every time the mouse is released from clicking it, counter is
     * incremented by 1 and sets the helper variable to false.
     * <p>
     * But if the mouse released happened from the drag movement, it sets the
     * counter a higher number to disable any further action. Then updates the
     * status.
     *
     * @param e Mouse event object
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        this.counter++;

        if (this.cursorDragOn) {
            this.counter = 9;
            this.updateStatusAndCursor();
        }

        if (this.counter > 1) {
            this.aCanvas.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }

        this.inDragMode = false;
    }

    /**
     * If the mouse is dragged after clicking the canvas for the first time
     * after draw button is clicked, it updates the end points of the 
     * new Drawables to the new position where the mouse is being dragged into.
     * Then it updates the status for every drag movement, displaying the
     * coordinates at that position.
     *
     * @param e The MouseEvent object.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (this.inDragMode) {
            // Grabs the location at this mouseclick
            int x = e.getX();
            int y = e.getY();

            // Sets the a helper variable to true, to be used
            //      for mouseReleased event.
            this.cursorDragOn = true;

            // Updates the new endpoints of the Drawable, calling this method.
            this.updateEndpoints(x, y);

            // Updates status
            this.aCanvas.setStatus("Endpoint: "
                    + this.aMap.get("xi") + "," + this.aMap.get("yi")
                    + "  Release the mouse to draw the shape..");
            this.aCanvas.setPosStatus(x, y);

            // Records the current position, it continuously overwrites
            //      with new position as long is the mouse is being dragged.
            this.aMap.put("xn", x);
            this.aMap.put("yn", y);
        }
    }

    /**
     * Updates the status bar and cursor if a new Drawable is finalised 
     * either by clicking or dragging.
     */
    public void updateStatusAndCursor() {
        int trueThickness = this.aCanvas.getThickness(lineThickness);

        switch (this.drawableSelector) {
            case 1:
                this.aCanvas.setStatus("  Line is created at ["
                        + this.aMap.get("xi") + "," + this.aMap.get("yi")
                        + "] to ["
                        + this.aMap.get("xn") + "," + this.aMap.get("yn")
                        + "] with " + trueThickness + " px thickness. "
                        + "Ready to draw..");
                break;
            case 2:
                this.aCanvas.setStatus("  Circle is created within the range of"
                        + " [" + this.aMap.get("xi") + "," + this.aMap.get("yi")
                        + "] to [" + this.aMap.get("xn") + "," + this.aMap.get("yn")
                        + "] with " + trueThickness + " px thickness. "
                        + "Ready to draw..");
                break;
            case 3:
                this.aCanvas.setStatus("  Square is created within the range of"
                        + " [" + this.aMap.get("xi") + "," + this.aMap.get("yi")
                        + "] to [" + this.aMap.get("xn") + "," + this.aMap.get("yn")
                        + "] with " + trueThickness + " px thickness. "
                        + "Ready to draw..");
                break;
            case 4:
                this.aCanvas.setStatus("  Ellipse is created within the range of"
                        + " [" + this.aMap.get("xi") + "," + this.aMap.get("yi")
                        + "] to [" + this.aMap.get("xn") + "," + this.aMap.get("yn")
                        + "] with " + trueThickness + " px thickness. "
                        + "Ready to draw..");
                break;
            case 5:
                this.aCanvas.setStatus("  Triangle is created within the range of"
                        + " [" + this.aMap.get("xi") + "," + this.aMap.get("yi")
                        + "] to [" + this.aMap.get("xn") + "," + this.aMap.get("yn")
                        + "] with " + trueThickness + " px thickness. "
                        + "Ready to draw..");
                break;
            case 6:
                this.aCanvas.setStatus("  Arc is created within the range of"
                        + " [" + this.aMap.get("xi") + "," + this.aMap.get("yi")
                        + "] to [" + this.aMap.get("xn") + "," + this.aMap.get("yn")
                        + "] with " + trueThickness + " px thickness. "
                        + "Ready to draw..");
                break;
            case 7:
                this.aCanvas.setStatus("  Image is created within the range of"
                        + " [" + this.aMap.get("xi") + "," + this.aMap.get("yi")
                        + "] to [" + this.aMap.get("xn") + "," + this.aMap.get("yn")
                        + "] with " + trueThickness + " px thickness. "
                        + "Ready to draw..");
                break;

        }

    }

    /**
     * Updates the endpoints if the new Drawable is being extended either 
     * by dragging or by clicking.
     *
     * @param x current x-coordinate position
     * @param y current y-coordinate position
     */
    public void updateEndpoints(int x, int y) {
        this.shape.setNewEndpoints(x, y);
        this.aCanvas.activateDraw();
    }

}

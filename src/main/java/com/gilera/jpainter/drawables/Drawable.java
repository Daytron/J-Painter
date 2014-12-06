package com.gilera.jpainter.drawables;


/**
 * File: Drawable.java
 * 
 * Description: The superclass for all Drawable subclasses.
 */
import java.awt.Color;
import java.awt.Graphics;

/**
 * Drawable class
 * 
 * Purpose: Holds all object variables similar for all Drawable subclasses. 
 * Defines the an abstract method for all subclasses and other utility methods.
 *
 * @author Ryan Gilera <jalapaomaji-github@yahoo.com>
 */
public abstract class Drawable {
    /**
     * Object variables declarations
     * <ul>
     * <li>xi - initial x position</li>
     * <li>yi - initial y position</li>
     * <li>xo - other end x position</li>
     * <li>yo - other end y position</li>
     * <li>sColor - Drawables color</li>
     * <li>sThick - Drawables thickness</li>
     * <li>footprint - Drawables footprint</li>
     * </ul>
     * 
     */
    private final int xi, yi;
    private int xo, yo;
    private final Color sColor;
    private final int sThick;
    private final boolean footprint;

    /**
     * Constructor initialising object variables.
     * @param xi initial x position
     * @param yi initial y position
     * @param xo other end x position
     * @param yo other end y position
     * @param sThick Drawables thickness
     * @param sColor Drawables color
     * @param footprint Drawables footprint
     */
    public Drawable(int xi, int yi, int xo, int yo, int sThick, Color sColor,
            boolean footprint) {
        this.xi = xi;
        this.yi = yi;
        this.xo = xo;
        this.yo = yo;
        this.sThick = sThick;
        this.sColor = sColor;
        this.footprint = footprint;
    }

    /**
     * Abstract method to be define by the Drawable subclasses. This is the
     * method for drawing the Drawable using the Graphics object.
     * @param graphics Graphics object
     */
    public abstract void drawShape(Graphics graphics);

    /**
     * Getter method for xi.
     * @return xi 
     */
    public int getXi() {
        return xi;
    }

    /**
     * Getter method for xo.
     * @return xo
     */
    public int getXo() {
        return xo;
    }

    /**
     * Getter method for yi.
     * @return yi 
     */
    public int getYi() {
        return yi;
    }

    /**
     * Getter method for yo.
     * @return yo 
     */
    public int getYo() {
        return yo;
    }

    /**
     * Getter method for sColor.
     * @return sColor
     */
    public Color getColor() {
        return sColor;
    }

    /**
     * Getter method for sThick.
     * @return sThick 
     */
    public int getThick() {
        return sThick;
    }

    /**
     * Getter method for footprint.
     * @return footprint 
     */
    public boolean getFootprint() {
        return footprint;
    }

    /**
     * Setter method for xo.
     * @param xo A new xo value
     */
    public void setXo(int xo) {
        this.xo = xo;
    }

    /**
     * Setter method for yo.
     * @param xo A new yo value
     */
    public void setYo(int yo) {
        this.yo = yo;
    }

    /**
     * Updates the end points
     * @param x new x end position
     * @param y new y end position
     */
    public void setNewEndpoints(int x, int y){
        this.xo = x;
        this.yo = y;
    }
    
}

package com.gilera.jpainter;


/**
 * File: DrawableLine.java
 * 
 * Description: The subclass drawing line.
 */
import java.awt.Color;
import java.awt.Graphics;

/**
 * DrawableLine class
 * 
 * Purpose: Responsible for drawing the line into the JPanel (Canvas).
 *
 * @author Ryan Gilera <jalapaomaji-github@yahoo.com>
 */
public class DrawableLine extends Drawable {

    // Constructor - same setup as the Drawable class
    public DrawableLine(int x0, int y0, int xk, int yk,
            int thickness, Color aColor, boolean footprint) {
        super(x0, y0, xk, yk, thickness, aColor, footprint);
        
    }

    /**
     * Drawing method. The points resulted from clicking and dragging needs to
     * take into consideration. The idea is that the area covered by the
     * two points (rectangular/square shape area) is the scope or limit
     * of the new Drawable object to be drawn. An imaginary line is created
     * between these points and slope has to be taken into account in order
     * to draw this object naturally.
     * @param graphics Graphics object.
     */
    @Override
    public void drawShape(Graphics graphics) {
        // Applies the color initially.
        graphics.setColor(super.getColor());

        // Local variables declarations
        int xi, yi, xn, yn, p;
        int dx, dy, x, y, slopeSign;

        // Initialisations.
        xi = super.getXi();
        xn = super.getXo();
        yi = super.getYi();
        yn = super.getYo();

        dx = xn - xi;
        dy = yn - yi;

        // If the slope |m| < 1, x constantly increase by one
        //      while y depends on p value (variable), depending
        //      on the slope (if negative, less one or the same)
        //      (if positive slope, plus one or the same)
        if (Math.abs(dx) > Math.abs(dy)) {
            // If xi is greater than xk, then interchange xi, yi, and xk, yk.
            if (xi > xn) {
                int temp = xi;
                xi = xn;
                xn = temp;
                dx = -dx;

                temp = yi;
                yi = yn;
                yn = temp;
                dy = -dy;
            }

            // Initialise slopeSign value
            slopeSign = 1;

            // Now if yi > yk then change the sign of dy
            //      and assign slopeSign to -1;
            if (yi > yn) {
                dy = -dy;
                slopeSign = -1;
            }

            // Initialise the first p value.
            p = 2 * dy - dx;

            //Assign initial values to x and y for drawing pixel.
            x = xi;
            y = yi;

            // Draw the first pixel of the line at (x,y).
            graphics.drawLine(x, y, x, y);

            // Every loop a footprint is drawn for each new calculated
            //      values of x and y.
            // At |m| < 1, x increases by 1 constantly instead of y.
            for (x = xi + 1; x <= xn; x++) {

                if (p < 0) {
                    p += 2 * dy;
                } else {
                    p += 2 * dy - 2 * dx;
                    // if slope is negative y is reduce by one
                    //      otherwise, y is increase by one
                    y += slopeSign;
                }
                // Draws the footprint at each location of x,y
                this.drawBrushHead(x, y, graphics);
            }
            // Otherwise y constantly increase by one
            //      while x depends on p value, depending
            //      on the slope (if negative, less one or the same)
            //      (if positive slope, plus one or the same)
        } else {
            // If yi is greater than yk, then interchange xi, yi, and xk, yk.
            if (yi > yn) {
                int temp = xi;
                xi = xn;
                xn = temp;
                dx = xn - xi;

                temp = yi;
                yi = yn;
                yn = temp;
                dy = yn - yi;
            }

            // Initialise slopeSign value
            slopeSign = 1;

            // Now if xi > xk then change the sign of dx
            //      and assign slopeSign to -1;
            if (xi > xn) {
                dx = -dx;
                slopeSign = -1;
            }

            // Initialise the first p value.
            p = 2 * dx - dy;

            //Assign initial values to x and y for drawing pixel.
            x = xi;
            y = yi;

            // Draw the first pixel of the line at (x,y).
            graphics.drawLine(x, y, x, y);

            // Every loop a footprint is drawn for each new calculated
            //      values of x and y.
            // At |m| >= 1, y increases by 1 constantly instead of x.
            for (y = yi + 1; y <= yn; y++) {

                if (p < 0) {
                    p += 2 * dx;
                } else {
                    p += 2 * dx - 2 * dy;
                    // if slope is negative x is reduce by one
                    //      otherwise, x is increase by one
                    x += slopeSign;
                }
                // Draws the footprint at each location of x,y
                this.drawBrushHead(x, y, graphics);
            }
        }
    }

    /**
     * Gets the string representation of the line. For debugging purposes only.
     *
     * @return String format ready to print.
     */
    @Override
    public String toString() {
        return "xi,yi: " + super.getXi() + "," + super.getYi()
                + " xn,yn: " + super.getXo() + "," + super.getYo()
                + " color:" + super.getColor()
                + " thickness:" + super.getThick();
    }

    /**
     * The method determines which footprint is to used and call the
     * corresponding method. But if the thickness is 1 pixel, there is no need
     * to draw footprint, instead it just draw the line.
     *
     * @param x The base x-coordinate of the footprint (centre).
     * @param y The base y-coordinate of the footprint (centre).
     * @param g The graphics object pass to the methods.
     */
    private void drawBrushHead(int x, int y, Graphics g) {
        if (super.getThick() == 1) {
            g.drawLine(x, y, x, y);
        } else {
            // Calls the static class for generating footprint.
            Footprint.drawFootprint(g, x, y, super.getThick(), 
                    super.getFootprint());
        }
    }

}

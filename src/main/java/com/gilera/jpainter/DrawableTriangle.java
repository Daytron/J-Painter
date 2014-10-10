package com.gilera.jpainter;


/**
 * File: DrawableTriangle.java
 * 
 * Description: The subclass drawing triangle.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * DrawableTriangle class
 * 
 * Purpose: Responsible for drawing the triangle into the JPanel (Canvas).
 *
 * @author Ryan Gilera <jalapaomaji-github@yahoo.com>
 */
public class DrawableTriangle extends Drawable {

    // Constructor - same setup as the Drawable class
    public DrawableTriangle(int xi, int yi, int xo, int yo, int sThick,
            Color sColor, boolean footprint) {
        super(xi, yi, xo, yo, sThick, sColor, footprint);
    }

    /**
     * Drawing method. The points resulted from clicking and dragging needs to
     * take into consideration. The idea is that the area covered by the two
     * points (rectangular/square shape area) is the scope or limit of the new
     * Drawable object to be drawn. An imaginary line is created between these
     * points and slope has to be taken into account in order to draw this
     * object naturally.
     *
     * @param graphics Graphics object.
     */
    @Override
    public void drawShape(Graphics graphics) {
        int xn, yn, xm, ym;
        int xa, ya, xb, yb, xc, yc, xd, yd;

        // xn and yn is the first point the user clicked
        // xm and ym is the second point the user clicked
        xn = super.getXi();
        yn = super.getYi();
        xm = super.getXo();
        ym = super.getYo();

        graphics.setColor(super.getColor());

        int dx = xm - xn;
        int dy = ym - yn;

        // Scenario for slope less than 1.
        if (Math.abs(dx) > Math.abs(dy)) {
            if (xn > xm) {
                int tempX = xn;
                xn = xm;
                xm = tempX;

                int tempY = yn;
                yn = ym;
                ym = tempY;
            } else {
                xn = super.getXi();
                yn = super.getYi();
                xm = super.getXo();
                ym = super.getYo();
            }

            xa = xn;
            xb = xm;
            xc = xn;
            xd = xm;

            if (yn > ym) {
                ya = ym;
                yb = ym;
                yc = yn;
                yd = yn;

            } else {
                ya = yn;
                yb = yn;
                yc = ym;
                yd = ym;
            }

            // Scenario for slope is greater or equal to 1.
        } else {
            if (yn > ym) {
                int tempX = xn;
                xn = xm;
                xm = tempX;

                int tempY = yn;
                yn = ym;
                ym = tempY;
            } else {
                xn = super.getXi();
                yn = super.getYi();
                xm = super.getXo();
                ym = super.getYo();
            }

            ya = yn;
            yb = yn;
            yc = ym;
            yd = ym;

            if (xn > xm) {
                xa = xm;
                xb = xn;
                xc = xm;
                xd = xn;
            } else {
                xa = xn;
                xb = xm;
                xc = xn;
                xd = xm;
            }
        }

        this.drawTriangle(graphics, xa, ya, xb, yb, xc, yc, xd, yd);

    }
    
    /**
     * Draws triangle after the points limits are defined
     * @param graphics Graphics object
     * @param xa x coordinate of top left of the square area
     * @param ya y coordinate of top left of the square area
     * @param xb x coordinate of top right of the square area
     * @param yb y coordinate of top right of the square area
     * @param xc x coordinate of lower left of the square area
     * @param yc y coordinate of lower left of the square area
     * @param xd x coordinate of lower right of the square area
     * @param yd y coordinate of lower right of the square area
     */
    private void drawTriangle(Graphics graphics, int xa, int ya, int xb, int yb,
            int xc, int yc, int xd, int yd) {

        ArrayList<DrawableLine> triangle = new ArrayList<>();

        // computes the peak of the triangle
        int xmid = ((xb - xa) / 2) + xa;
        int ymid = ya;

        // Draws the necessary lines to form a triangle
        triangle.add(new DrawableLine(xmid, ymid, xc, yc,
                super.getThick(), super.getColor(), super.getFootprint()));

        triangle.add(new DrawableLine(xc, yc, xd, yd,
                super.getThick(), super.getColor(), super.getFootprint()));

        triangle.add(new DrawableLine(xmid, ymid, xd, yd,
                super.getThick(), super.getColor(), super.getFootprint()));

        // Draws clockwise.
        for (DrawableLine aLine : triangle) {
            aLine.drawShape(graphics);
        }
    }
}

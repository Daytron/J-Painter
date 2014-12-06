package com.gilera.jpainter.drawables;


/**
 * File: Footprint.java
 * 
 * Description: The class responsible for drawing footprint. A utility for all
 * Drawable objects.
 */
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * Footprint class
 * 
 * Purpose: Provides the footprint mechanism for all Drawables. A utility 
 * static class that doesn't need to be instantiated, saves memory usage.
 *
 * @author Ryan Gilera <jalapaomaji-github@yahoo.com>
 */
public final class Footprint {

    // A static method for drawing the footprint, parent method for all
    //      sub methods called inside.
    public static final void drawFootprint(Graphics graphics, int xc, int yc,
            int thickness, boolean footprintType) {

        // Determines which kind of footprint to draw
        if (footprintType) {
            drawSquareFootprint(graphics, xc, yc, thickness);
        } else {
            drawCircleFootprint(graphics, xc, yc, thickness);
        }
    }

    private static final void drawSquareFootprint(Graphics graphics, int x,
            int y, int thickness) {
        /**
         * x and y are shifted by half of the thickness given. If the thickness
         * is odd, it's evenly spread top, bottom, left and right for the given
         * x,y. Otherwise, extra one line for both top and left sides are added.
         */
        x -= thickness / 2;
        y -= thickness / 2;

        // Draw the lines from left to right first to fill horizontal space
        // Then move from top to bottom to fill vertical space.
        for (int i = 0; i < thickness; i++) {
            for (int j = 0; j < thickness; j++) {
                graphics.drawLine(x + j, y + i, x + j, y + i);
            }
        }

    }

    /**
     * Draws circle footprint. To fill the circle the method gets all points 
     * along the circle for the 1st and 2nd octant. From there it fills
     * it by drawing pixel by pixel from y centre to the points as the limit.
     * Then mirror it to the other 3 quadrant areas of the circle.
     * @param graphics Graphics object
     * @param xc centre x position 
     * @param yc centre y position
     * @param thickness thickness
     */
    private static final void drawCircleFootprint(Graphics graphics, int xc,
            int yc, int thickness) {

        // Declares and intialise helper variables
        final List<Integer[]> edges1stOctant, edges2ndOctant, edgesQuadrant;
        edgesQuadrant = new ArrayList<>();
        edges2ndOctant = new ArrayList<>();

        // Extract the radius from the thickness
        final int radius = thickness / 2;

        // Capture or record all points along the 1st octant.
        edges1stOctant = drawCircle(graphics, xc, yc, radius);

        // Add the first half of the points along the quadrant where 1st 
        //      octant lies.
        edgesQuadrant.addAll(edges1stOctant);

        // Temperorary helper for reversing the element position of 
        //      edges1stOctant to this helper variable.
        // Reverse because the 2nd octant is simply a reverse of the 1st octant.
        List<Integer[]> listHelper = new ArrayList<>();

        // Reverse the order of the 1st octant on the newly copied helper var
        for (int i = edges1stOctant.size() - 1; i > -1; i--) {
            listHelper.add(edges1stOctant.get(i));
        }

        // Temporary holder for swapping x and y in the second octant
        Integer[] helper;

        // Extract all coordinates for 2nd octant with the use of listHelper
        //      helper variables. 
        // In the second octant, the x and y of the 1st octant are reverse.
        // 2nd octant lies adjacent next to positive x at y = 0;
        // 1st octant lies adjacent next to positive y at x = 0;
        for (Integer[] aPoint : listHelper) {
            helper = new Integer[2];
            helper[0] = aPoint[1];
            helper[1] = aPoint[0];

            edges2ndOctant.add(helper);
        }

        // Add the remaining coordinates to the edgesQuadrant list
        edgesQuadrant.addAll(edges2ndOctant);

        // REMOVES DOUBLE EXTRA X COORDINATES on the same Y COORDINATE
        // Since some of the edges points (x coordinate) are more than one
        //      (due to Bresenham algorithm) in the same y coordinate 
        //      (helps eliminate filling the same x horizontal plane 
        //      when iterating the edges later for filing
        //      gaps), these points are redundant so it must not be use as
        //      reference later when filling the circle footprint.
        List<Integer[]> newEdgeQuadrant = new ArrayList<>();
        int yOld = -1, yNew; // Initialize for comparing 1st element with this.
        // there's no -1 y coordinate so it safe to use this as initial value.

        for (Integer[] aPoint : edgesQuadrant) {
            yNew = aPoint[1];
            if (yNew != yOld) {
                newEdgeQuadrant.add(aPoint);
                yOld = yNew;
            }
        }

        // FILLING THE CIRCLE by QUADRANT SYMMETRY //
        int xmin, xmax, ymin, ymax;

        xmin = 0;
        xmax = radius;
        ymin = 0;
        ymax = radius;

        // It starts at point xmin and ymax (0,r) all the way to point xmin and ymin (0,0)
        for (int y = ymax; y >= ymin; y--) {
            for (Integer[] aPoint : newEdgeQuadrant) {
                if (aPoint[1] == y) {
                    // Fills the circle horizantally from xmin to xmax
                    for (int x = xmin; x < aPoint[0]; x++) {
                        fillCircleQuadrant(graphics, x, y, xc, yc);
                    }
                    break;
                }
            }
        }

        // Fills the middle horizontal x line gap from xmin to xmax and
        //      from xmin to negative xmax;
        for (int x = xmin, y = 0; x <= radius; x++) {
            graphics.drawLine(x + xc, y + yc, x + xc, y + yc);
            graphics.drawLine(-x + xc, y + yc, -x + xc, y + yc);
        }

    }

    /**
     * The method for mirroring the filling mechanism to the rest of quadrants
     * @param graphics Graphics object
     * @param x x position to fill
     * @param y y position to fill
     * @param xc Shifts back to original x position
     * @param yc Shifts back to original y position
     */
    private static final void fillCircleQuadrant(Graphics graphics, int x,
            int y, int xc, int yc) {
        graphics.drawLine(x + xc, y + yc, x + xc, y + yc);
        graphics.drawLine(-x + xc, y + yc, -x + xc, y + yc);
        graphics.drawLine(-x + xc, -y + yc, -x + xc, -y + yc);
        graphics.drawLine(x + xc, -y + yc, x + xc, -y + yc);
    }

    /**
     * The method for drawing the circle.
     *
     * @param xc The x-coordinate of the centre of the circle.
     * @param yc The y-coordinate of the centre of the circle.
     * @param radius The radius of the circle.
     * @param graphics The graphics object.
     */
    private static final List<Integer[]> drawCircle(Graphics graphics,
            int xc, int yc, int radius) {

        /*
         * Declare and initialise local variables.
         * The origin is base on positive y-axis at (0,r)
         * 
         * int x and y - Circle coordinates use 
         *               to draw the pixels of circle's 8 arcs.
         * int p - A computed variable responsible for determining 
         *              which pixels to draw.
         */
        int x, y, p;
        Integer[] coordinate;
        List<Integer[]> edges1stOctant = new ArrayList<>();

        x = 0;
        y = radius;

        // Initialises first points of the 8 arcs.
        for (int i = 1; i <= 8; i++) {
            drawCircleArcs(graphics, x, y, xc, yc, i);
        }

        // Initialise the value of P, base on the Bresenham's algorithm.      
        p = 3 - 2 * radius;

        while (x <= y) {
            // Increments x at each iteration regardless the value of p.
            x++;

            // If the value of P is negative then choose (x, y)
            //      as the next pixel to draw
            // Then calculate the next value of p
            if (p < 0) {
                p += (4 * x) + 6;
            } // Else choose the pixel (x, y-1) to draw
            // Then calculate the next value of p
            else {
                y--;
                p += (4 * (x - y)) + 10;
            }

            // Records all pixel drawn, later use to as limit in filling circle
            coordinate = new Integer[2];
            coordinate[0] = x;
            coordinate[1] = y;

            edges1stOctant.add(coordinate);

            // Draws the 8 pixels for each octant of the circle through symmetry;
            //      by calling the drawTheCircle method which takes
            //      circle parameters as method arguments.
            for (int i = 1; i <= 8; i++) {
                drawCircleArcs(graphics, x, y, xc, yc, i);
            }
        }

        return edges1stOctant;
    }

    
    /**
     * The method for mirroring the arc to complete the circle.
     *
     * @param g The graphics object.
     * @param x The x-coordinate to draw the arc.
     * @param y The y-coordinate to draw the arc.
     * @param xc The x-coordinate of the centre of the circle.
     * @param yc The y-coordinate of the centre of the circle.
     * @param octant The flag variable that determines the octant location.
     */
    private static final void drawCircleArcs(Graphics g, int x, int y, int xc,
            int yc, int octant) {

        switch (octant) {
            // 1st Octant
            case 1:
                g.drawLine(y + xc, x + yc,
                        y + xc, x + yc);
                break;
            // 2nd Octant
            case 2:
                g.drawLine(x + xc, y + yc,
                        x + xc, y + yc);
                break;
            // 3rd Octant
            case 3:
                g.drawLine(-x + xc, y + yc,
                        -x + xc, y + yc);
                break;
            // 4th Octant
            case 4:
                g.drawLine(-y + xc, x + yc,
                        -y + xc, x + yc);
                break;
            // 5th Octant
            case 5:
                g.drawLine(-y + xc, -x + yc,
                        -y + xc, -x + yc);
                break;
            // 6th Octant
            case 6:
                g.drawLine(-x + xc, -y + yc,
                        -x + xc, -y + yc);
                break;
            // 7th Octant
            case 7:
                g.drawLine(x + xc, -y + yc,
                        x + xc, -y + yc);
                break;
            // 8th Octant
            case 8:
                g.drawLine(y + xc, -x + yc,
                        y + xc, -x + yc);
                break;
        }
    }

}

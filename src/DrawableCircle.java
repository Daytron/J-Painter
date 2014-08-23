
/**
 * File: DrawableCircle.java
 * 
 * Description: The subclass drawing circle.
 */
import java.awt.Color;
import java.awt.Graphics;

/**
 * DrawableCircle class
 * 
 * Purpose: Responsible for drawing the circle into the JPanel (Canvas).
 *
 * @author Ryan Gilera <jalapaomaji-github@yahoo.com>
 */
public class DrawableCircle extends Drawable {

    // Constructor - same setup as the Drawable class
    public DrawableCircle(int xi, int yi, int xo, int yo, int sThick,
            Color sColor, boolean footprint) {
        super(xi, yi, xo, yo, sThick, sColor, footprint);
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
        int thick = super.getThick();
        int radius;

        // x and y are the coordinates for the centre of the circle
        int x, y;
        
        // xn and yn is the first point the user clicked
        // xm and ym is the second point the user clicked
        int xn, yn, xm, ym;
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

            x = ((xm - xn) / 2) + xn;

            if (yn > ym) {
                y = ((yn - ym) / 2) + ym;
            } else {
                y = ((ym - yn) / 2) + yn;
            }

            radius = (xm - xn) / 2;

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

            y = ((ym - yn) / 2) + yn;

            if (xn > xm) {
                x = ((xn - xm) / 2) + xm;
                radius = (xn - xm) / 2;
            } else {
                x = ((xm - xn) / 2) + xn;
                radius = (xm - xn) / 2;
            }
        }

        // Calls the drawCircle method to draw the circle in each loop.
        this.drawCircle(x, y, radius, graphics);
    }

    /**
     * The method for drawing the circle.
     *
     * @param xc The x-coordinate of the centre of the circle.
     * @param yc The y-coordinate of the centre of the circle.
     * @param r The radius of the circle.
     * @param g The graphics object.
     */
    private void drawCircle(int xc, int yc, int r, Graphics g) {
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

        x = 0;
        y = r;

        // Initialises first points of the 8 arcs.
        this.drawCircleArcs(g, x, y, xc, yc);

        // Initialise the value of P, base on the Bresenham's algorithm.      
        p = 3 - 2 * r;

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

            // Draws the 8 pixels for each octant of the circle through symmetry;
            //      by calling the drawTheCircle method which takes
            //      circle parameters as method arguments.
            if (super.getThick() == 1) {
                this.drawCircleArcs(g, x, y, xc, yc);
            } else {
                this.drawCircleArcsFootprint(g, x, y, xc, yc);
            }

        }
    }

    /**
     * The method for mirroring the arc to complete the circle.
     *
     * @param g The graphics object.
     * @param x The x-coordinate to draw the arc.
     * @param y The y-coordinate to draw the arc.
     * @param xc The x-coordinate of the centre of the circle.
     * @param yc The y-coordinate of the centre of the circle.
     */
    private void drawCircleArcs(Graphics g, int x, int y, int xc, int yc) {
        g.drawLine(y + xc, x + yc, y + xc, x + yc);
        g.drawLine(x + xc, y + yc, x + xc, y + yc);
        g.drawLine(-x + xc, y + yc, -x + xc, y + yc);
        g.drawLine(-y + xc, x + yc, -y + xc, x + yc);
        g.drawLine(-y + xc, -x + yc, -y + xc, -x + yc);
        g.drawLine(-x + xc, -y + yc, -x + xc, -y + yc);
        g.drawLine(x + xc, -y + yc, x + xc, -y + yc);
        g.drawLine(y + xc, -x + yc, y + xc, -x + yc);

    }

    /**
     * The method for mirroring the arc to complete the circle.
     *
     * @param g The graphics object.
     * @param x The x-coordinate to draw the arc.
     * @param y The y-coordinate to draw the arc.
     * @param xc The x-coordinate of the centre of the circle.
     * @param yc The y-coordinate of the centre of the circle.
     */
    private void drawCircleArcsFootprint(Graphics g, int x, int y, int xc,
            int yc) {
        Footprint.drawFootprint(g, y + xc, x + yc, super.getThick(),
                super.getFootprint());
        Footprint.drawFootprint(g, x + xc, y + yc, super.getThick(),
                super.getFootprint());
        Footprint.drawFootprint(g, -x + xc, y + yc, super.getThick(),
                super.getFootprint());
        Footprint.drawFootprint(g, -y + xc, x + yc, super.getThick(),
                super.getFootprint());
        Footprint.drawFootprint(g, -y + xc, -x + yc, super.getThick(),
                super.getFootprint());
        Footprint.drawFootprint(g, -x + xc, -y + yc, super.getThick(),
                super.getFootprint());
        Footprint.drawFootprint(g, x + xc, -y + yc, super.getThick(),
                super.getFootprint());
        Footprint.drawFootprint(g, y + xc, -x + yc, super.getThick(),
                super.getFootprint());
    }

}

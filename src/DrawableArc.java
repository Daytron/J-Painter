
/**
 * File: DrawableArc.java
 * <p>
 * Description: The subclass drawing Arc.
 */
import java.awt.Color;
import java.awt.Graphics;

/**
 * DrawableArc class
 * <p>
 * Purpose: Responsible for drawing the arc into the JPanel (Canvas).
 *
 * @author Ryan Gilera <jalapaomaji-github@yahoo.com>
 */
public class DrawableArc extends Drawable {

    // Constructor - same setup as the Drawable class
    public DrawableArc(int xi, int yi, int xo, int yo, int sThick,
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
        int p;
        int a, b, xc, yc;
        int semiMajor, semiMinor;
        
        // variable which side of arc will be drawn depending if xn > xm 
        //      or xm > xn
        int side;

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

                side = 2;
            } else {
                side = 1;
                xn = super.getXi();
                yn = super.getYi();
                xm = super.getXo();
                ym = super.getYo();
            }

            // see notes for explanation (notebook)
            semiMajor = Math.abs(xm - xn) / 2;
            xc = xn + semiMajor;

            if (yn > ym) {
                semiMinor = Math.abs(yn - ym) / 2;
                yc = ym + semiMinor;
            } else {
                semiMinor = Math.abs(ym - yn) / 2;
                yc = yn + semiMinor;
            }

            a = semiMajor;
            b = semiMinor;

            // stops generating arc if is too small, causing a and b to
            // become zero
            if (a < 2 && b < 2) {
                return;
            }

            int aSqr = a * a;
            int bSqr = b * b;

            p = (aSqr * (1 - (2 * b))) + (2 * bSqr);

            for (int x = 0, y = b; ((bSqr * x) <= (aSqr * y)); x++) {
                if (p < 0) {
                    p += (2 * bSqr) * ((2 * x) + 3);
                } else {
                    p += (4 * aSqr) * (1 - y) + (2 * bSqr)
                            * ((2 * x) + 3);
                    y -= 1;
                }

                if (super.getThick() == 1) {
                    this.drawEllipse(graphics, x, y, xc, yc, side);
                } else {
                    this.drawEllipseFootprint(graphics, x, y, xc, yc, side);
                }
            }

            p = bSqr * (1 - (2 * a)) + (2 * aSqr);

            for (int x = a, y = 0; (aSqr * y) <= (bSqr * x); y++) {
                if (p < 0) {
                    p += (2 * aSqr) * ((2 * y) + 3);
                } else {
                    p += (4 * bSqr) * (1 - x) + (2 * aSqr)
                            * ((2 * y) + 3);
                    x -= 1;
                }

                if (super.getThick() == 1) {
                    this.drawEllipse(graphics, x, y, xc, yc, side);
                } else {
                    this.drawEllipseFootprint(graphics, x, y, xc, yc, side);
                }
            }

            // Scenario for slope is greater or equal to 1.
        } else {
            // x and y are swap if the absolute value of the slope is
            //      1 or greater than 1.

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

            // see notes for explanation (notebook)
            semiMajor = Math.abs(ym - yn) / 2;
            xc = yn + semiMajor;

            if (xn > xm) {
                side = 2;
                semiMinor = Math.abs(xn - xm) / 2;
                yc = xm + semiMinor;
            } else {
                side = 1;
                semiMinor = Math.abs(xm - xn) / 2;
                yc = xn + semiMinor;
            }

            a = semiMajor;
            b = semiMinor;

            // stops generating arc if is too small, causing a and b to
            // become zero
            if (a < 2 && b < 2) {
                return;
            }

            int aSqr = a * a;
            int bSqr = b * b;

            p = (aSqr * (1 - (2 * b))) + (2 * bSqr);

            for (int y = 0, x = b; ((bSqr * y) <= (aSqr * x)); y++) {
                if (p < 0) {
                    p += (2 * bSqr) * ((2 * y) + 3);
                } else {
                    p += (4 * aSqr) * (1 - x) + (2 * bSqr)
                            * ((2 * y) + 3);
                    x -= 1;
                }

                if (super.getThick() == 1) {
                    this.drawEllipse(graphics, x, y, yc, xc, side);
                } else {
                    this.drawEllipseFootprint(graphics, x, y, yc, xc, side);
                }
            }

            p = bSqr * (1 - (2 * a)) + (2 * aSqr);

            for (int y = a, x = 0; (aSqr * x) <= (bSqr * y); x++) {
                if (p < 0) {
                    p += (2 * aSqr) * ((2 * x) + 3);
                } else {
                    p += (4 * bSqr) * (1 - y) + (2 * aSqr)
                            * ((2 * x) + 3);
                    y -= 1;
                }

                if (super.getThick() == 1) {
                    this.drawEllipse(graphics, x, y, yc, xc, side);
                } else {
                    this.drawEllipseFootprint(graphics, x, y, yc, xc, side);
                }
            }
        }

    }

    /**
     * Method for mirroring the rest of the area of the arc
     *
     * @param graphics Graphics object
     * @param x calculated x coordinate
     * @param y calculated y coordinate
     * @param xc original x coordinate
     * @param yc original y coordinate
     */
    private void drawEllipse(Graphics graphics, int x, int y, int xc, int yc,
            int side) {
        if (side == 1) {
            graphics.drawLine(-x + xc, y + yc, -x + xc, y + yc);
            graphics.drawLine(-x + xc, -y + yc, -x + xc, -y + yc);
        } else {
            graphics.drawLine(x + xc, y + yc, x + xc, y + yc);
            graphics.drawLine(x + xc, -y + yc, x + xc, -y + yc);
        }

    }

    /**
     * Draws the footprint by calling the footprint utility static class.
     *
     * @param graphics Graphics object
     * @param x calculated x coordinate
     * @param y calculated y coordinate
     * @param xc original x coordinate
     * @param yc original y coordinate
     */
    private void drawEllipseFootprint(Graphics graphics, int x, int y, int xc,
            int yc, int side) {
        if (side == 1) {
            Footprint.drawFootprint(graphics, -x + xc, y + yc, super.getThick(),
                    super.getFootprint());
            Footprint.drawFootprint(graphics, -x + xc, -y + yc, super.getThick(),
                    super.getFootprint());
        } else {
            Footprint.drawFootprint(graphics, x + xc, y + yc, super.getThick(),
                super.getFootprint());
            Footprint.drawFootprint(graphics, x + xc, -y + yc, super.getThick(),
                super.getFootprint());
        }
        
    }

}

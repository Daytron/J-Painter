
/**
 * File: MousePositionListener.java
 * 
 * Description: The class responsible for updating and displaying the of the
 * position of the mouse at any point of the application in the MousePosBar
 * JLabel object regardless of any action.
 */
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * MousePositionListener class
 * 
 * Purpose: Grabs the current (x,y) position of the mouse and pass it to the
 * setPosStatus method in the canvas, where it will display the current
 * position.
 *
 * @author Ryan Gilera <jalapaomaji-github@yahoo.com>
 */
public class MousePositionListener extends MouseAdapter {

    private final DrawCanvas canvas;

    public MousePositionListener(DrawCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        this.canvas.setPosStatus(x, y);
    }

}

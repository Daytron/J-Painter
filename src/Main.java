
/**
 * File: Main.java
 * <p>
 * Description: An application implementing pixel duplication and moving pen
 * algorithm applying the Bresenham algorithm. Allows user to adjust line
 * parameters such as the end points of the line and its thickness.
 *
 * @author Ryan Gilera <jalapaomaji-github@yahoo.com>
 * @version 1.0 21 July 2014
 */
import com.alee.laf.WebLookAndFeel;
import javax.swing.SwingUtilities;

/**
 * Main class
 * <p>
 * Purpose: Entry point of the application. Executes and initialise object.
 */
public class Main {

    // Main method
    public static void main(String[] args) {
        // Shifts the look and feel of the application to WEBLaF
        WebLookAndFeel.install();

        // Creates an object for UserInterface class
        // This will serve as main application object 
        UserInterface ui = new UserInterface();
        SwingUtilities.invokeLater(ui);
    }

}

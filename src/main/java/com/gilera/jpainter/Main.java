package com.gilera.jpainter;


/**
 * File: Main.java
 * 
 * Description: A paint-like Java application implementing Bresenham algorithm. 
 *
 * @author Ryan Gilera <jalapaomaji-github@yahoo.com>
 * @version 1.0 21 July 2014
 */
import com.alee.laf.WebLookAndFeel;
import javax.swing.SwingUtilities;

/**
 * Main class
 * 
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

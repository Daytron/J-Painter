package com.gilera.jpainter;


/**
 * File: MenuListener.java
 * 
 * Description: The class responsible for implementing the JMenu UI components.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * MenuListener class
 * 
 * Purpose: Implements behaviour for File > New, Edit > Clear and About>about
 * menu items.
 *
 * @author Ryan Gilera <jalapaomaji-github@yahoo.com>
 */
public class MenuListener implements ActionListener {

    // Object variables declarations.
    private final JFrame frame;
    private final DrawCanvas theCanvas;

    /**
     * Method Constructor, initialising the object variables.
     *
     * @param frame     The main frame of the UI.
     * @param theCanvas The main canvas of the UI.
     */
    public MenuListener(JFrame frame, DrawCanvas theCanvas) {
        this.frame = frame;
        this.theCanvas = theCanvas;
    }

    /**
     * Method listener implementation for all JMenuBar activities
     *
     * @param e An ActionEvent object from a JMenu source.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Extracts the type of source by typecasting it to JMenuItem.
        JMenuItem source = (JMenuItem) (e.getSource());

        // If menu is selected is the Clear Canvas, 
        //      this will clear the canvas and updates status bar.
        if (source.getText().equals("Clear Canvas")) {
            this.theCanvas.clearCanvas();
            this.theCanvas.repaint();
            this.theCanvas.setStatus(
                    "  Canvas is now cleared. Ready to draw.. ");
        }

        // If menu is selected is New
        //      this will destroy all line objects created and clear canvas.
        if (source.getText().equals("New")) {
            this.theCanvas.startANew();
            this.theCanvas.repaint();
            this.theCanvas.setStatus("  New Canvas created. Ready to draw..");
        }

        // If menu is selected is About Application
        //      this will show a pane describing about the application. 
        if (source.getText().equals("About Application")) {
            String pt1 = "<html><body width='";
            String pt2
                    = "'><h1>J-PAINTER</h1>"
                    + "<p>Written in Java 1.7 using AWT and SWING API."
                    + " Look And Feel design by Mikle Garin (WebLaF 1.28) "
                    + "http://weblookandfeel.com"
                    + " "
                    + "<br><br>"
                    + "<p>All Rights Reserved 2014 <br>Ryan Gilera"
                    + "";

            int width = 175;
            String s = pt1 + width + pt2;

            JOptionPane.showMessageDialog(null, s);
        }
    }

}

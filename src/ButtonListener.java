/**
 * File: ButtonListener.java
 * <p>
 * Description: The class responsible for listening and implementation of any
 * action events like clicking of a button.
 */
import com.alee.extended.colorchooser.WebColorChooserField;
import com.alee.global.GlobalConstants;
import com.alee.laf.button.WebToggleButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.filechooser.WebFileChooser;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import resources.ResourceLoader;

/**
 * ButtonListener class
 * <p>
 * Purpose: The class responsible for the implementation of the action events
 * from the user. Implements the ActionListener interface.
 * <p>
 * This includes drawing a Drawable, undoing and redoing Drawable, 
 * clearing the canvas, updating status bar, checks the footprint type and 
 * change to a custom mouse cursor.
 *
 * @author Ryan Gilera <jalapaomaji-github@yahoo.com>
 */
public class ButtonListener implements ActionListener {

    /**
     * Object variables declarations.
     * <ul>
     * <li>aCanvas - The main canvas object.</li>
     * <li>pencilToolButton - The draw button from the toolbar.</li>
     * <li>undoToolButton - The undo button from the toolbar.</li>
     * <li>redoToolButton - The redo button from the toolbar.</li>
     * <li>clearToolButton - The clear canvas button from the toolbar.</li>
     * <li>colorComboBox - The colour picker from the toolbar.</li>
     * <li>lineComboBox - The line thickness combo box from the toolbar.</li>
     * <li>radioSquare - The square footprint radio button from the toolbar.
     * </li>
     * <li>radioCircle - The circle footprint radio button from the toolbar.
     * </li>
     * <li>cursor - The cursor of the application.</li>
     * <li>drawableLine - Drawable object.</li>
     * <li>drawableCircle - Drawable object.</li>
     * <li>drawableSquare - Drawable object.</li>
     * <li>drawableEllipse - Drawable object.</li>
     * <li>drawableTriangle - Drawable object.</li>
     * <li>imageButton - The button that triggers image/photo drawing</li>
     * </ul>
     *
     */
    private final DrawCanvas aCanvas;
    private final JButton pencilToolButton;
    private final JButton undoToolButton;
    private final JButton redoToolButton;
    private final JButton clearToolButton;
    private final WebColorChooserField colorComboBox;
    private final WebComboBox lineComboBox;
    private final JRadioButton radioSquare;
    private final JRadioButton radioCircle;
    private final Image cursor;
    private final WebToggleButton drawableLine;
    private final WebToggleButton drawableCircle;
    private final WebToggleButton drawableSquare;
    private final WebToggleButton drawableEllipse;
    private final WebToggleButton drawableTriangle;
    private final JButton imageButton;

    // For image loader
    private WebFileChooser imageChooser;
    private File file;

    /**
     * The class constructor. Initialise object variables.
     *
     * @param aCanvas The main canvas object.
     * @param pencilToolButton The draw button from the toolbar.
     * @param undoToolButton The undo button from the toolbar.
     * @param redoToolButton The redo button from the toolbar.
     * @param clearToolButton The clear canvas button from the toolbar.
     * @param aComboBox The colour picker from the toolbar.
     * @param lineCombo The line thickness combo box from the toolbar.
     * @param aSquareRadio The square footprint radio button from the toolbar.
     * @param aCircleRadio The circle footprint radio button from the toolbar.
     * @param cursor The cursor of the application.
     * @param drawableLine The Drawable object.
     * @param drawableCircle The Drawable object.
     * @param drawableSquare The Drawable object.
     * @param drawableEllipse The Drawable object.
     * @param drawableTriangle The Drawable object.
     * @param image The image button initialiser.
     */
    public ButtonListener(DrawCanvas aCanvas, JButton pencilToolButton,
            JButton undoToolButton, JButton redoToolButton,
            JButton clearToolButton, WebColorChooserField aComboBox,
            WebComboBox lineCombo, JRadioButton aSquareRadio,
            JRadioButton aCircleRadio, Image cursor,
            WebToggleButton drawableLine, WebToggleButton drawableCircle,
            WebToggleButton drawableSquare, WebToggleButton drawableEllipse,
            WebToggleButton drawableTriangle, JButton image) {
        this.aCanvas = aCanvas;
        this.pencilToolButton = pencilToolButton;
        this.undoToolButton = undoToolButton;
        this.redoToolButton = redoToolButton;
        this.clearToolButton = clearToolButton;
        
        this.colorComboBox = aComboBox;
        this.lineComboBox = lineCombo;
        this.radioSquare = aSquareRadio;
        this.radioCircle = aCircleRadio;
        this.cursor = cursor;
        
        this.drawableLine = drawableLine;
        this.drawableCircle = drawableCircle;
        this.drawableSquare = drawableSquare;
        this.drawableEllipse = drawableEllipse;
        this.drawableTriangle = drawableTriangle;
        
        this.imageButton = image;
        this.imageChooser = null;
        this.file = null;
    }

    /**
     * Implements all action events from the JToolBar components.
     *
     * @param event ActionEvent object, the involve UI component.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        // For the pencilToolButton
        if (event.getSource() == this.pencilToolButton) {
            // Change the cursor image when user cliks the pencilToolButton.
            this.aCanvas.setCursor(ResourceLoader.getDefaultToolkit()
                    .createCustomCursor(cursor, new Point(0, 31), "Pencil"));

            // Sets the default footprint to true, corresponds to 
            //      the square footprint.
            boolean footprintFlag = true;

            // Displays the instructions on the status bar.
            this.aCanvas.setStatus(
                    " Click on the canvas for the end point");

            // If the circle footprint is selected, set the helper variable
            //      to false;
            if (this.radioCircle.isSelected()) {
                footprintFlag = false;
            }

            // Sets the default value to draw DrawableLine
            int drawableSelector = 1;

            // Filters the drawableSelector choice
            if (this.drawableLine.isSelected()) {
                drawableSelector = 1;
            } else if (this.drawableCircle.isSelected()) {
                drawableSelector = 2;
            } else if (this.drawableSquare.isSelected()) {
                drawableSelector = 3;
            } else if (this.drawableEllipse.isSelected()) {
                drawableSelector = 4;
            } else if (this.drawableTriangle.isSelected()) {
                drawableSelector = 5;
            } else {
                drawableSelector = 6;
            }

            // Creates a new MouseListener to initiate drawing.
            MouseClickListener aClickListener
                    = new MouseClickListener(this.aCanvas,
                            this.colorComboBox.getColor(),
                            this.lineComboBox.getSelectedIndex(),
                            footprintFlag, drawableSelector);
            // Adds the two listeners to the canvas.
            // This is necessary because when MouseAdapter is being
            //      used instead of these, the MouseAdapter requires
            //      to explixitly add the involve mouseListeners.
            // MouseListener is for clicking events.
            // MouseMotionListener is for dragging events.
            this.aCanvas.addMouseListener(aClickListener);
            this.aCanvas.addMouseMotionListener(aClickListener);
        }

        // For the undoToolButton
        // Calls the canvas' undo() and repaint() method
        if (event.getSource() == this.undoToolButton) {
            this.aCanvas.undo();
            this.aCanvas.repaint();
        }

        // For the redoToolButton
        // Calls the canvas' redo() and repaint() method
        if (event.getSource() == this.redoToolButton) {
            this.aCanvas.redo();
            this.aCanvas.repaint();
        }

        // For the clearToolButton
        // Calls the canvas' clearCanvas(), repaint() and setStatus() method.
        if (event.getSource() == this.clearToolButton) {
            this.aCanvas.clearCanvas();
            this.aCanvas.repaint();
            this.aCanvas.setStatus(
                    "  Canvas is now cleared. Ready to draw.. ");
        }

        // For the lineComboBox
        // Extract the index number of the selected line thickness
        //      in the lineComboBox using the canvas' getThickness method.
        // Then it updates its status in the status bar.
        if (event.getSource() == this.lineComboBox) {
            int index = this.aCanvas.getThickness(
                    this.lineComboBox.getSelectedIndex());
            this.aCanvas.setStatus("  Drawable thickness is now set to "
                    + index + " px. Ready to draw..");
        }

        // For the radioSquare
        // Updates status if user select the square footprint.
        if (event.getSource() == this.radioSquare) {
            this.aCanvas.setStatus(
                    "  Square footprint is selected. Ready to draw..");
        }

        // For the radioCircle
        // Updates status if user select the circle footprint.
        if (event.getSource() == this.radioCircle) {
            this.aCanvas.setStatus(
                    "  Circle footprint is selected. Ready to draw..");
        }

        // Process the image drawing when Image button is clicked
        if (event.getSource() == this.imageButton) {
            // Opens the image file window dialogue
            if (this.imageChooser == null) {
                this.imageChooser = new WebFileChooser();
                this.imageChooser.setMultiSelectionEnabled(false);
                this.imageChooser.setAcceptAllFileFilterUsed(false);
                this.imageChooser.addChoosableFileFilter(GlobalConstants.IMAGES_FILTER);
            }
            
            if (this.file != null) {
                this.imageChooser.setSelectedFile(file);
            }

            // Initialise draw mechanism when a file is selected
            if (this.imageChooser.showOpenDialog(this.aCanvas) == 
                    WebFileChooser.APPROVE_OPTION) {
                this.file = this.imageChooser.getSelectedFile();
                
                Image newImage;
                
                // Safely read the selected file and store in newImage
                try {
                    newImage = ImageIO.read(this.file);
                } catch (Exception e) {
                    return;
                }
                

                // Change the cursor image when user cliks the pencilToolButton.
                this.aCanvas.setCursor(Cursor
                        .getPredefinedCursor(Cursor.MOVE_CURSOR));

                // Displays the instructions on the status bar.
                this.aCanvas.setStatus(
                        " Click or drag on the canvas to draw image..");

                // Creates a new MouseListener to initiate drawing.
                MouseClickListener aClickListener
                        = new MouseClickListener(this.aCanvas,
                                this.colorComboBox.getColor(),
                                this.lineComboBox.getSelectedIndex(),
                                true, 7);
                aClickListener.setImage(newImage);
                
                // Adds the two listeners to the canvas.
                // This is necessary because when MouseAdapter is being
                //      used instead of these, the MouseAdapter requires
                //      to explixitly add the involve mouseListeners.
                // MouseListener is for clicking events.
                // MouseMotionListener is for dragging events.
                this.aCanvas.addMouseListener(aClickListener);
                this.aCanvas.addMouseMotionListener(aClickListener);
            }
        }
    }

}

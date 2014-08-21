
/**
 * File: UserInterface.java
 * <p>
 * Description: The class responsible for building the UI frame, panels and
 * components of the application.
 *
 * NOTE: ALL resources such as image and global variables are manage by the
 * GlobalSettingsManager Class. Please refer to that for more information.
 */
import com.alee.extended.colorchooser.WebColorChooserField;
import com.alee.extended.panel.WebButtonGroup;
import com.alee.laf.button.WebToggleButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.radiobutton.WebRadioButton;
import com.alee.laf.toolbar.WebToolBar;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * UserInterface class
 * <p>
 * Purpose: Implements the Runnable interface to implement its only method
 * run(). This allows to choose what statements will run in the thread.
 *
 * @author Ryan Gilera <jalapaomaji-github@yahoo.com>
 */
public class UserInterface implements Runnable {

    // Object variable declaration of JFrame.
    private JFrame frame;

    /**
     * The override method for Runnable interface, run, creates a new instance
     * of JFrame for the GUI application and sets it parameters and settings.
     */
    @Override
    public void run() {
        frame = new JFrame("J-Painter");
        // Sets the window frame size.
        frame.setPreferredSize(
                new Dimension(GlobalSettingsManager.getFrameWidth(),
                        GlobalSettingsManager.getFrameHeight()));

        // Sets the behaviour of the frame when user click close.
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setResizable(false);
        frame.setIconImage(
                GlobalSettingsManager.getAppIcon());

        // Calls the createComponents method for initialising the 
        //      UI components for the Jframe, passing the content pane 
        //      of the JFrame to the method.
        this.createComponents(frame);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * The method for building the UI components of the application.
     *
     * @param frame The JFrame is pass as an argument to be used by other UI
     *              components.
     */
    private void createComponents(JFrame frame) {
        // Create new container object for the frame by extracting
        //      the content pane of the frame.
        Container container = frame.getContentPane();

        // Sets the layout of the application
        BorderLayout mainLayout = new BorderLayout();
        container.setLayout(mainLayout);

        // Create a new DrawCanvas object
        DrawCanvas aCanvas = new DrawCanvas();

        // Creates the Menu by calling the buildMenu() method
        this.buildMenu(frame, aCanvas);

        // Creates the toolbar menu by calling the
        //      buildToolbar() method
        JToolBar appToolBar = this.buildToolbar(aCanvas);

        /// STATUS BAR ///
        // Creates two JLabel objects, one for the status of program,
        //      the other for the x,y coordinates of the mouse when
        //      moving on the canvas.
        JLabel statusBar = aCanvas.getStatusBar();
        JLabel mousePosBar = aCanvas.getMousePosBar();
        MousePositionListener mouseListener
                = new MousePositionListener(aCanvas);
        aCanvas.addMouseMotionListener(mouseListener);

        // Creates a JPanel to contain the two JLabel objects
        JPanel statusPanel = new JPanel();
        // Sets the layout for the statusPanel and other adjustments
        BoxLayout statusLayout
                = new BoxLayout(statusPanel, BoxLayout.LINE_AXIS);
        statusPanel.setLayout(statusLayout);
        statusPanel.setMaximumSize(GlobalSettingsManager.getStatusDimension());
        statusPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        // Adds the two JPanel objects to the statusPanel
        statusPanel.add(statusBar);
        statusPanel.add(Box.createHorizontalGlue());
        statusPanel.add(mousePosBar);

        // Put all components together in the container object
        container.add(appToolBar, BorderLayout.NORTH);
        container.add(aCanvas, BorderLayout.CENTER);
        container.add(statusPanel, BorderLayout.SOUTH);
    }

    /**
     * The method for generating the menu of the application.
     *
     * @param frame   The JFrame of the application.
     * @param aCanvas The Canvas of the application.
     */
    private void buildMenu(JFrame frame, DrawCanvas aCanvas) {
        // Declares and initialise a MouseListener
        MenuListener aMenuListener = new MenuListener(frame, aCanvas);
        // Creates a JMenuBar object to hold all menu item objects.
        JMenuBar menubar = new JMenuBar();

        /// FILE MENU ///
        // Creates the File menu.
        JMenu file = new JMenu("File");
        // Sets the keyboard shorcut for File menu (ALT+F)
        file.setMnemonic(KeyEvent.VK_F);

        // Creates the menu items inside the File menu.
        // FileNew menu item is for creating a new canvas to draw, 
        //      by disposing all line objects from the canvas.
        JMenuItem fileNew = new JMenuItem(
                "New", GlobalSettingsManager.getFileNewIcon());
        // Sets the tooltip text for FileNew.
        fileNew.setToolTipText("New Canvas");
        // Sets the keyboard shorcut for FileNew (ALT+N).
        fileNew.setMnemonic(KeyEvent.VK_N);
        // Adds the MenuListener for FileNew, for detecting user action.
        fileNew.addActionListener(aMenuListener);

        // FileExit menu item, that allows user to exit the application.
        JMenuItem fileExit = new JMenuItem(
                "Exit", GlobalSettingsManager.getFileExitIcon());
        // Sets the keyboard shortcut for FileExit.
        fileExit.setMnemonic(KeyEvent.VK_X);
        // Sets the tooltip text for FileExit.
        fileExit.setToolTipText("Exit application");
        // Sets the key combination which invokes the menu item's 
        //      action listeners without navigating the menu hierarchy.
        fileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
                ActionEvent.ALT_MASK));

        // Implements the action listener for exiting the application if
        //      the corresponding keyboard shortcut is pressed.
        fileExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }

        });

        // Adds all File menu items into the File menu object.
        file.add(fileNew);
        file.addSeparator();
        file.add(fileExit);

        /// EDIT MENU ///
        // Creates a JMenu object for Edit functions.
        JMenu edit = new JMenu("Edit");
        // Sets its keyboard shortcut (ALT+E).
        edit.setMnemonic(KeyEvent.VK_E);

        // The EditClear menu item for clearing the canvas.
        JMenuItem editClear = new JMenuItem(
                "Clear Canvas", GlobalSettingsManager.getEditClearIcon());
        // Assign a MenuListener to implement its functionality.
        editClear.addActionListener(aMenuListener);
        // Adds the menu item to the Edit Menu.
        edit.add(editClear);

        /// ABOUT MENU ///
        // Creates a JMenu object for displaying 
        //      information about the application.
        JMenu about = new JMenu("About");
        // Sets its keyboard shortcut (ALT+A).
        about.setMnemonic(KeyEvent.VK_A);

        // The AboutAbout menu item for the about menu.
        JMenuItem aboutAbout = new JMenuItem(
                "About Application", GlobalSettingsManager.getAboutIcon());
        // Sets its keyboard shortcut (ALT+A)
        aboutAbout.setMnemonic(KeyEvent.VK_A);
        // Assign the MenuListener to the menu item.
        aboutAbout.addActionListener(aMenuListener);
        // Adds the menu item to the About Menu
        about.add(aboutAbout);

        // Add all JMenu objects to the JMenuBar object.
        menubar.add(file);
        menubar.add(edit);
        menubar.add(about);

        // Adds the JMenuBar to the JFrame.
        frame.setJMenuBar(menubar);
    }

    /**
     * The method for generating the ToolBar Menu.
     *
     * @param aCanvas The application's canvas.
     * @return The toolbar object to be added to the container of the frame.
     */
    public JToolBar buildToolbar(DrawCanvas aCanvas) {
        // Creates a WebToolBar object as the toolbar container.
        // WebToolBar is a subclass of JToolBar, part of the 
        //      WEBLaF look and feel theme.
        WebToolBar toolbar = new WebToolBar();

        // Sets the layout of the toolbar.
        GroupLayout toolbarLayout = new GroupLayout(toolbar);
        toolbar.setLayout(toolbarLayout);
        // Hinders the user from dragging the toolbar from its place.
        toolbar.setFloatable(false);
        // Sets the rollover state of this toolbar. 
        // If the rollover state is true then the border of the toolbar buttons 
        //      will be drawn only when the mouse pointer hovers over them. 
        toolbar.setRollover(true);

        // Creates a JButton for drawing the Drawables.
        // Sets the text label, position and its tooltip helper.
        JButton pencilButton = new JButton(
                GlobalSettingsManager.getToolDrawIcon());
        pencilButton.setText("Draw");
        pencilButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        pencilButton.setHorizontalTextPosition(SwingConstants.CENTER);
        pencilButton.setToolTipText("Draw a shape");

        // Creates a JButton for undoing the last Drawable drawn.
        // Sets the text label, position and its tooltip helper.
        JButton undoButton = new JButton(
                GlobalSettingsManager.getToolUndoIcon());
        undoButton.setText("Undo");
        undoButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        undoButton.setHorizontalTextPosition(SwingConstants.CENTER);
        undoButton.setToolTipText("Undo changes");

        // Creates a JButton for redoing the last Drawable erased.
        // Sets the text label, position and its tooltip helper.
        JButton redoButton = new JButton(
                GlobalSettingsManager.getToolRedoIcon());
        redoButton.setText("Redo");
        redoButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        redoButton.setHorizontalTextPosition(SwingConstants.CENTER);
        redoButton.setToolTipText("Redo changes");

        // Creates a JButton for clearing the canvas from all the Drawables
        //      drawn. Sets the text label, position and its tooltip helper.
        JButton clearButton = new JButton(
                GlobalSettingsManager.getToolClearIcon());
        clearButton.setText("Clear");
        clearButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        clearButton.setHorizontalTextPosition(SwingConstants.CENTER);
        clearButton.setToolTipText("Clear the canvas");
        
        // Creates a JButton for launching image.
        // Sets the text label, position and its tooltip helper.
        JButton imageAddButton = new JButton(
                GlobalSettingsManager.getToolImageIcon());
        imageAddButton.setText("Image");
        imageAddButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        imageAddButton.setHorizontalTextPosition(SwingConstants.CENTER);
        imageAddButton.setToolTipText("Add an image");

        
        /// CREATES DRAWABLE SELECTOR ///
        
        // JPanel to hold all Drawable buttons
        JPanel drawablePanel = new JPanel();
        
        // Sets opacity to zero
        drawablePanel.setOpaque(false);
        
        
         // Drawables buttons
        WebToggleButton drawableLineButton = new WebToggleButton(
            GlobalSettingsManager.getToolDrawableLineIcon());
        drawableLineButton.setSelected(true);
        WebToggleButton drawableCircleButton = new WebToggleButton(
            GlobalSettingsManager.getToolDrawableCircleIcon());
        WebToggleButton drawableSquareButton = new WebToggleButton(
            GlobalSettingsManager.getToolDrawableSquareIcon());
        WebToggleButton drawableEllipseButton = new WebToggleButton(
            GlobalSettingsManager.getToolDrawableEllipseIcon());
        WebToggleButton drawableTriangleButton = new WebToggleButton (
            GlobalSettingsManager.getToolDrawableTriangleIcon());
        WebToggleButton drawableArcButton = new WebToggleButton(
            GlobalSettingsManager.getToolDrawableArcIcon());
        WebButtonGroup g1 = new WebButtonGroup ( drawableLineButton, 
                drawableCircleButton, drawableSquareButton, 
                drawableEllipseButton, drawableTriangleButton, 
                drawableArcButton );


        // Grouping Drawables together allowing only one button to
        //      be pressed at a time.
        WebButtonGroup groupWebToggle = new WebButtonGroup (
                WebButtonGroup.VERTICAL, true, g1);
        groupWebToggle.setButtonsDrawFocus(false);
        drawablePanel.add(groupWebToggle);
        
        /// COLOR PICKER SECTION ///
        // Creates a JPanel to hold the colorpicker and label objects.
        JPanel colorPanel = new JPanel();
        // Sets the layout for the JPanel.
        GroupLayout colorLayout = new GroupLayout(colorPanel);
        colorPanel.setLayout(colorLayout);
        // Sets the opaque effect to false, making it transparent.
        colorPanel.setOpaque(false);

        // Allows automatic spacing for the JPanel.
        colorLayout.setAutoCreateContainerGaps(true);
        colorLayout.setAutoCreateGaps(true);

        // Creates a WebLabel object, for labeling the colorpicker.
        // WebLabel is a subclass of JLabel, 
        //      created for the WEBLaF layout theme.
        WebLabel colorlabel = new WebLabel("Line Color");
        // Activates a label effect for webLabel object. 
        colorlabel.setDrawShade(true);

        // Creates the WebColorChooserField object for the colorpicker.
        WebColorChooserField colorChooser
                = new WebColorChooserField(
                        GlobalSettingsManager.getDefaultColorChooserColor());
        colorChooser.setOpaque(true);
        colorChooser.setEditable(false);
        colorChooser.setMinimumHeight(40);

        // Sets the horizontal position of the WebColorChooserField 
        //      and WebLabel in the JPanel.
        colorLayout.setHorizontalGroup(
                colorLayout.createSequentialGroup()
                .addGroup(colorLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(colorChooser, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(colorlabel))
        );

        // Sets the vertical position of the WebColorChooserField 
        //      and WebLabel in the JPanel.
        colorLayout.setVerticalGroup(
                colorLayout.createSequentialGroup()
                .addComponent(colorChooser, GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(colorlabel)
        );

        /// LINE THICKNESS SECTION ///
        // Creates a JPanel for to hold the WebComboBox and WebLabel objects.
        JPanel lineThickPanel = new JPanel();
        // Sets the layout and opaque of the panel.
        GroupLayout lineThickLayout = new GroupLayout(lineThickPanel);
        lineThickPanel.setLayout(lineThickLayout);
        lineThickPanel.setOpaque(false);

        // Sets automatic spacing between UI components inside the panel.
        lineThickLayout.setAutoCreateContainerGaps(true);
        lineThickLayout.setAutoCreateGaps(true);

        // Creates a WebLabel object for label of the line thickness picker.
        // WebLabel is a subclass of JLabel, created for WEBLaF layout theme.
        WebLabel lineThicklabel = new WebLabel("Line Thickness");
        // Sets a text shade effect for the label.
        lineThicklabel.setDrawShade(true);

        // Sets the items (different images) for the line thickness combo box.
        Object[] items
                = {
                    GlobalSettingsManager.getLineThickImage0(),
                    GlobalSettingsManager.getLineThickImage1(),
                    GlobalSettingsManager.getLineThickImage2(),
                    GlobalSettingsManager.getLineThickImage3(),
                    GlobalSettingsManager.getLineThickImage4()
                };

        // Creates a new WebComboBox object and 
        //      apply the items variable to it.
        WebComboBox lineComboBox = new WebComboBox(items);
        // Sets maximum row count of combo box
        lineComboBox.setMaximumRowCount(5);
        // Allows heavyweight components 
        //      to whether or not a lightweight Component 
        //      should be used to contain the JComboBox, 
        //      versus a heavyweight Component such as a Panel or a Window.
        lineComboBox.setLightWeightPopupEnabled(false);

        // Sets the horizontal position of the WebComboBox 
        //      and WebLabel in the JPanel.
        lineThickLayout.setHorizontalGroup(
                lineThickLayout.createSequentialGroup()
                .addGroup(lineThickLayout.createParallelGroup(
                                GroupLayout.Alignment.CENTER)
                        .addComponent(lineComboBox)
                        .addComponent(lineThicklabel))
        );

        // Sets the vertical position of the WebComboBox 
        //      and WebLabel in the JPanel.
        lineThickLayout.setVerticalGroup(
                lineThickLayout.createSequentialGroup()
                .addComponent(lineComboBox)
                .addComponent(lineThicklabel)
        );

        /// BRUSH HEAD SECTION ///
        // Creates the radio button object for square footprint.
        // WebRadio button, is a sublcass created for the WEBLaF layout theme. 
        WebRadioButton radioSquare
                = new WebRadioButton("Square Footprint");
        // Sets the animation feature of this button to true.
        radioSquare.setAnimated(true);
        // Sets the default selection for this button.
        radioSquare.setSelected(true);

        // Creates the radio button for the circle footprint.
        WebRadioButton radioCircle
                = new WebRadioButton("Circle Footprint");
        // Sets the animation feature of the button to true.
        radioCircle.setAnimated(true);

        // Creates a button group that allows only one radio button
        //      selection between the two buttons.
        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(radioSquare);
        bgroup.add(radioCircle);

        // Creates a webLabel, the label for the Drawable thickness section.
        WebLabel brushlabel = new WebLabel("Brush Head Type");
        // Apply text style to the label.
        brushlabel.setDrawShade(true);

        // Creates a JPanel to hold the label and the radio buttons
        JPanel brushPanel = new JPanel();
        // Sets the layout of the panel
        GroupLayout brushLayout = new GroupLayout(brushPanel);
        brushPanel.setLayout(brushLayout);
        // Sets opaque to false, making the panel transparent.
        brushPanel.setOpaque(false);

        // Sets automatic spacing for the components inside the panel.
        brushLayout.setAutoCreateContainerGaps(true);
        brushLayout.setAutoCreateGaps(true);

        // Sets the horizontal position of the radio buttons 
        //      and WebLabel in the JPanel.
        brushLayout.setHorizontalGroup(
                brushLayout.createSequentialGroup()
                .addGroup(brushLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(radioSquare)
                        .addComponent(radioCircle)
                        .addComponent(brushlabel))
        );

        // Sets the vertical position of the WebComboBox 
        //      and WebLabel in the JPanel.
        brushLayout.setVerticalGroup(
                brushLayout.createSequentialGroup()
                .addComponent(radioSquare)
                .addComponent(radioCircle)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                        GroupLayout.DEFAULT_SIZE, 10)
                .addComponent(brushlabel)
        );

        // Loads the custom cursor image to a new cursor object.
        // Please refer to GlobalResourceManager for all resources.
        Image cursor = GlobalSettingsManager.getCursorImage();

        // Creates an actionListener object for all toolbar buttons.
        ButtonListener aButtonListener = new ButtonListener(aCanvas,
                pencilButton, undoButton, redoButton, clearButton, colorChooser,
                lineComboBox, radioSquare, radioCircle, cursor,
                drawableLineButton, drawableCircleButton, drawableSquareButton,
                drawableEllipseButton, drawableTriangleButton, imageAddButton);

        // Apply the actionListener to the buttons.
        pencilButton.addActionListener(aButtonListener);
        undoButton.addActionListener(aButtonListener);
        redoButton.addActionListener(aButtonListener);
        clearButton.addActionListener(aButtonListener);
        lineComboBox.addActionListener(aButtonListener);
        radioSquare.addActionListener(aButtonListener);
        radioCircle.addActionListener(aButtonListener);
        imageAddButton.addActionListener(aButtonListener);

        // Sets the horizontal positions of 
        //      all major components in the toolbar
        toolbarLayout.setHorizontalGroup(
                toolbarLayout.createSequentialGroup()
                .addComponent(pencilButton)
                .addComponent(undoButton)
                .addComponent(redoButton)
                .addComponent(clearButton)
                .addComponent(imageAddButton)
                .addComponent(drawablePanel)
                .addComponent(colorPanel)
                .addComponent(lineThickPanel)
                .addComponent(brushPanel)
        );

        // Sets the veritical positions of 
        //      all major components in the toolbar.
        toolbarLayout.setVerticalGroup(
                toolbarLayout.createSequentialGroup()
                .addGroup(toolbarLayout.createParallelGroup(
                                GroupLayout.Alignment.LEADING)
                        .addComponent(pencilButton)
                        .addComponent(undoButton)
                        .addComponent(redoButton)
                        .addComponent(clearButton)
                        .addComponent(imageAddButton)
                        .addComponent(drawablePanel)
                        .addComponent(colorPanel)
                        .addComponent(lineThickPanel)
                        .addComponent(brushPanel))
        );

        // Returns the newly generated toolbar.
        return toolbar;
    }

}

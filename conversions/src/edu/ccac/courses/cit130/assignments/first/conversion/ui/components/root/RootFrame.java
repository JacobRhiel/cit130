package edu.ccac.courses.cit130.assignments.first.conversion.ui.components.root;

import edu.ccac.courses.cit130.assignments.first.conversion.ui.components.conversion.ConversionPanel;
import edu.ccac.courses.cit130.assignments.first.conversion.ui.components.conversion.children.popup.ConversionSummaryPanel;
import edu.ccac.courses.cit130.assignments.first.conversion.ui.state.ConversionApplicationStateController;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jun 08, 2021
 */
public class RootFrame extends JFrame {

    public RootFrame() {
        setTitle("CIT130 Conversion Assignment");
        // sets the default size of the root frame.
        setSize(new Dimension(675, 725));
        // sets the layout to BorderLayout to allow the content pane to expand 100hw when set to BorderLayout.CENTER.
        setLayout(new BorderLayout());
        // creates the root panel to display our programmatically generated conversion panels.
        add(new RootPanel(), BorderLayout.CENTER);
        // sets the position to the center of the screen.
        setLocationRelativeTo(null);
        // set the close action (dispose to clean threads).
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // sets this frame to visible.
        setVisible(true);
    }

    public void showReportSummary() {
        int selectedOption = JOptionPane.showOptionDialog(this, new ConversionSummaryPanel(ConversionApplicationStateController.getConversionPanels().toArray(new ConversionPanel[0])),
                "Final Conversion Report Summary", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[]{"Close", "Restart"}, "Restart");
        switch (selectedOption) {
            // close option
            // disposes any resources, then closes the thread/application.
            case JOptionPane.YES_OPTION -> this.dispose();
            // restart option
            case JOptionPane.NO_OPTION -> ConversionApplicationStateController.resetApplicationStates();
        }
    }

}

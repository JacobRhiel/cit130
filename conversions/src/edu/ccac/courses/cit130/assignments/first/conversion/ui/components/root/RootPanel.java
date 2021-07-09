package edu.ccac.courses.cit130.assignments.first.conversion.ui.components.root;

import edu.ccac.courses.cit130.assignments.first.conversion.ui.components.conversion.ConversionPanel;
import edu.ccac.courses.cit130.assignments.first.conversion.ui.state.ConversionApplicationStateController;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

/**
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jun 08, 2021
 */
public class RootPanel extends JPanel {

    public RootPanel() {
        Collection<ConversionPanel> conversionPanels = ConversionApplicationStateController.getConversionPanels();
        setLayout(new GridLayout(conversionPanels.size(), 1));
        setBackground(Color.GRAY);
        for (ConversionPanel panel : conversionPanels) {
            add(panel);
        }
    }

}

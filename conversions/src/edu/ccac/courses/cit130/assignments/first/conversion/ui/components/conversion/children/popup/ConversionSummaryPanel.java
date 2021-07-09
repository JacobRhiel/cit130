package edu.ccac.courses.cit130.assignments.first.conversion.ui.components.conversion.children.popup;

import edu.ccac.courses.cit130.assignments.first.conversion.ConversionApplication;
import edu.ccac.courses.cit130.assignments.first.conversion.ui.components.conversion.ConversionPanel;
import edu.ccac.courses.cit130.assignments.first.conversion.utils.EnumNameFormatter;

import javax.swing.*;
import java.awt.*;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER;

/**
 * Creates a summary of the user selections.
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jun 12, 2021
 */
public class ConversionSummaryPanel extends JPanel {

    public ConversionSummaryPanel(ConversionPanel[] panels) {
        // gets the component size of parent.
        Dimension parentSize = ConversionApplication.INSTANCE.getPreferredSize();
        // sets the size to half of the parent.
        setPreferredSize(new Dimension((int)parentSize.getWidth() / 2, (int)parentSize.getHeight() / 2));
        setLayout(new BorderLayout());
        // multi-dimensional array for panels size and their results.
        Object[][] tableData = new Object[panels.length][3];
        for (int index = 0; index < panels.length; index++) {
            ConversionPanel panel = panels[index];
            tableData[index][0] = EnumNameFormatter.formatEnumName(panel.getFirstSelectedUnit().name());
            tableData[index][1] = EnumNameFormatter.formatEnumName(panel.getSecondSelectedUnit().name());
            tableData[index][2] = panel.getConversionValue();
        }
        DisplayTable displayTable = new DisplayTable(tableData);
        // container is required for the table to display nicely.
        JScrollPane tableContainer = new JScrollPane(displayTable);
        // hide the scrollbar ui below as it's not scrollable and we want the table to look embedded.
        tableContainer.setBorder(null);
        tableContainer.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
        tableContainer.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);

        add(tableContainer, BorderLayout.CENTER);
    }

}

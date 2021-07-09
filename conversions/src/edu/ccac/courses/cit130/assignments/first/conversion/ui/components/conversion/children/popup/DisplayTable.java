package edu.ccac.courses.cit130.assignments.first.conversion.ui.components.conversion.children.popup;

import javax.swing.*;
import java.awt.*;

/**
 * Creats a new {@link JTable} for the {@link ConversionSummaryPanel}.
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jun 12, 2021
 */
public class DisplayTable extends JTable {

    public DisplayTable(Object[][] tableData) {
        super(tableData, new String[] { "From Unit", "To Unit", "Converted Value" });
        setIntercellSpacing(new Dimension(0, 0));
        // hide the ugly grid lines
        setShowGrid(false);
        // hide the table border
        setBorder(BorderFactory.createEmptyBorder());
        // sets the cells uneditable.
        setDefaultEditor(Object.class, null);
        setFillsViewportHeight(true);
    }

}

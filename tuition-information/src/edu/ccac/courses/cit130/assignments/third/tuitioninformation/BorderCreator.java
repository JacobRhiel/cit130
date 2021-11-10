package edu.ccac.courses.cit130.assignments.third.tuitioninformation;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Static functions for creating simple borders.
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jul 31, 2021
 */
public class BorderCreator {
    /**
     * Creates a titled border.
     * @param title The title of the border.
     * @return The TitledBorder.
     */
    public static TitledBorder createBoldTitledBorder(String title) {
        TitledBorder border = BorderFactory.createTitledBorder(title);
        border.setTitleColor(Color.BLACK);
        border.setTitleFont(border.getTitleFont().deriveFont(Font.BOLD));
        return border;
    }
}

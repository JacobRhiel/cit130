package edu.ccac.courses.cit130.assignments.third.tuitioninformation;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jun 09, 2021
 */
public class FormattedEnumFieldNameRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        //toUpperCase for this instance.
        label.setText(EnumNameFormatter.formatEnumName(label.getText()).toUpperCase());
        return label;
    }
}

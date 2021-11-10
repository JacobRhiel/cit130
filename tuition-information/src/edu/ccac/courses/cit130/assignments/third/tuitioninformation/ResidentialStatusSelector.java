package edu.ccac.courses.cit130.assignments.third.tuitioninformation;

import javax.swing.*;

/**
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jul 31, 2021
 */
public class ResidentialStatusSelector extends JComboBox<ResidentStatus> {

    public ResidentialStatusSelector() {
        ResidentialStatusComboBoxModel model = new ResidentialStatusComboBoxModel(ResidentStatus.values());
        setModel(model);
        // because the enum uppercase naming drives me nuts.
        setRenderer(new FormattedEnumFieldNameRenderer());
    }
}

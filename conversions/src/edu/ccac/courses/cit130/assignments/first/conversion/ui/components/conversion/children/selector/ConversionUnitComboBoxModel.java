package edu.ccac.courses.cit130.assignments.first.conversion.ui.components.conversion.children.selector;

import edu.ccac.courses.cit130.assignments.first.conversion.unit.ConversionUnitType;

import javax.swing.*;

/**
 * An empty ComboBoxModel.
 * This enables us to utilize the actions differently.
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jun 09, 2021
 */
public class ConversionUnitComboBoxModel extends DefaultComboBoxModel<ConversionUnitType> {

    public ConversionUnitComboBoxModel(ConversionUnitType[] types) {
        super(types);
    }

}

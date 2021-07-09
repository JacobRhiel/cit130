package edu.ccac.courses.cit130.assignments.first.conversion.ui.components.conversion.children.selector;

import edu.ccac.courses.cit130.assignments.first.conversion.ui.components.conversion.ConversionPanel;
import edu.ccac.courses.cit130.assignments.first.conversion.ui.components.swing.FormattedEnumFieldNameRenderer;
import edu.ccac.courses.cit130.assignments.first.conversion.unit.ConversionUnitParent;
import edu.ccac.courses.cit130.assignments.first.conversion.unit.ConversionUnitType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashSet;
import java.util.Set;

/**
 * The dropdown selector.
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jun 08, 2021
 */
public class ConversionUnitSelector extends JComboBox<ConversionUnitType> implements ItemListener {

    private final ConversionPanel rootPanel;
    private final Set<ConversionUnitType> cachedItemOptions;
    private final ConversionUnitComboBoxModel model;
    private final boolean fromParent;

    public ConversionUnitSelector(ConversionPanel rootPanel, ConversionUnitParent parent, boolean fromParent) {
        this.rootPanel = rootPanel;
        this.cachedItemOptions = new HashSet<>();
        this.fromParent = fromParent;
        this.model = new ConversionUnitComboBoxModel(ConversionUnitType.values());
        for(ConversionUnitType unit : ConversionUnitType.values()) {
            if(unit.parentType() != parent) continue;
            cachedItemOptions.add(unit);
        }
        setModel(model);
        // because the enum uppercase naming drives me nuts.
        setRenderer(new FormattedEnumFieldNameRenderer());
        addItemListener(this);
    }

    /**
     * Updates the items on the current {@link ConversionUnitSelector} from it's partner.
     * @param from The type to be converted from.
     */
    public void updateItems(ConversionUnitType from) {
        model.removeAllElements();
        for(ConversionUnitType type : cachedItemOptions) {
            if(!fromParent && type.name().equals(from.name())) continue;
            model.addElement(type);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ConversionUnitSelector source = (ConversionUnitSelector)e.getSource();
        ConversionUnitType selectedType = (ConversionUnitType)source.getModel().getSelectedItem();
        System.out.println(e.getActionCommand());
        if(selectedType != null)
            rootPanel.fireUpdateSelectors(source, selectedType);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        ConversionUnitSelector source = (ConversionUnitSelector)e.getSource();
        ConversionUnitType selectedType = (ConversionUnitType)source.getModel().getSelectedItem();
        if(selectedType != null)
            rootPanel.fireUpdateSelectors(source, selectedType);
    }
}

package edu.ccac.courses.cit130.assignments.first.conversion.ui.components.conversion;

import edu.ccac.courses.cit130.assignments.first.conversion.ConversionApplication;
import edu.ccac.courses.cit130.assignments.first.conversion.ui.components.conversion.children.ConversionUnitTextField;
import edu.ccac.courses.cit130.assignments.first.conversion.ui.components.conversion.children.selector.ConversionUnitSelector;
import edu.ccac.courses.cit130.assignments.first.conversion.ui.state.PanelState;
import edu.ccac.courses.cit130.assignments.first.conversion.unit.ConversionUnitParent;
import edu.ccac.courses.cit130.assignments.first.conversion.unit.ConversionUnitType;
import edu.ccac.courses.cit130.assignments.first.conversion.ui.state.ConversionApplicationStateController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * An instance of a Conversion to handle the inner components and states that relate to the conversion process.
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jun 08, 2021
 */
public class ConversionPanel extends JPanel implements ActionListener {

    private PanelState state = PanelState.ENABLED;

    private final ConversionUnitSelector fromSelector, toSelector;
    private final ConversionUnitParent parent;

    private final boolean isFinalPanel;
    private double conversionValue;

    private final String fromLabelDefaultText = "Convert from: ";
    private final JLabel fromLabel = new JLabel(fromLabelDefaultText);
    private final ConversionUnitTextField fromTextField = new ConversionUnitTextField(this);
    private final String toLabelDefaultText = "Converted to: ";
    private final JLabel toLabel = new JLabel(toLabelDefaultText);
    private final JButton convertButton = new JButton(">>");

    /**
     * Constructs a new {@link ConversionPanel}.
     * @param parent The {@link ConversionUnitParent} parent of this instance.
     * @param finalPanel Whether this is the final panel.
     */
    public ConversionPanel(ConversionUnitParent parent, boolean finalPanel) {
        this.parent = parent;
        this.isFinalPanel = finalPanel;
        // formats the enum name type with uppercase first char.
        String formattedName = parent.name().charAt(0) + parent.name().substring(1).toLowerCase();
        setBorder(BorderFactory.createTitledBorder(formattedName));
        setLayout(new GridLayout(0, 3, 10, 10));
        fromSelector = new ConversionUnitSelector(this, parent, true);
        toSelector = new ConversionUnitSelector(this, parent, false);
        // updates the items in correspondence to eachother.
        fromSelector.updateItems(((ConversionUnitType)toSelector.getSelectedItem()));
        toSelector.updateItems(((ConversionUnitType)fromSelector.getSelectedItem()));
        add(fromSelector);
        // empty cell for cleanliness.
        JLabel EMPTY_CELL = new JLabel();
        add(EMPTY_CELL);
        add(toSelector);
        JPanel fromPanel = new JPanel();
        fromPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        fromPanel.add(fromLabel);
        fromPanel.add(fromTextField);
        add(fromPanel);
        convertButton.setEnabled(false);
        convertButton.addActionListener(this);
        add(convertButton);
        add(toLabel);
    }

    /**
     * Updates a selected type from it's source, since the toSelector won't contain the from options
     * we can assert that all options will come from the 'fromSelector'.
     * @param source The source {@link ConversionUnitSelector}.
     * @param selectedType The {@link ConversionUnitType}.
     */
    public void fireUpdateSelectors(ConversionUnitSelector source, ConversionUnitType selectedType) {
        if(fromSelector.equals(source))
            toSelector.updateItems(selectedType);
    }

    /**
     * Updates the convertButton if the unit amount is changed.
     * @param reset If a reset should occur.
     */
    public void fireUnitAmountChanged(boolean reset) {
        convertButton.setEnabled(reset);
    }

    /**
     * The current state of the panel. [Enabled, Disabled]
     * @return The {@link PanelState}.
     */
    public PanelState getState() {
        return this.state;
    }

    /**
     * Sets the panel's state. [Enabled, Disabled].
     * Sets the components state as well.
     * @param state The {@link PanelState} to update too.
     */
    public void setState(PanelState state) {
        if(this.state == state) return;
        this.state = state;
        boolean enabled = state == PanelState.ENABLED;
        fromSelector.setEnabled(enabled);
        toSelector.setEnabled(enabled);
        fromLabel.setEnabled(enabled);
        toLabel.setEnabled(enabled);
        // we only want to set the button to disabled here and let the input controller handle the button state.
        if(!enabled)
            convertButton.setEnabled(false);
        fromTextField.setEnabled(enabled);
        setEnabled(enabled);
    }

    /**
     * The parent of this conversion type.
     * @return {@link ConversionUnitParent}.
     */
    public ConversionUnitParent getUnitParent() {
        return this.parent;
    }

    /**
     * Gets the first dropdowns selected unit.
     * @return The {@link ConversionUnitType} of the first dropdown.
     */
    public ConversionUnitType getFirstSelectedUnit() {
        return (ConversionUnitType)this.fromSelector.getSelectedItem();
    }

    /**
     * Gets the second dropdowns selected unit.
     * @return The {@link ConversionUnitType} of the second dropdown.
     */
    public ConversionUnitType getSecondSelectedUnit() {
        return (ConversionUnitType)this.toSelector.getSelectedItem();
    }

    /**
     * Gets the conversion value from the calculation.
     * @return The conversion calculated value.
     */
    public double getConversionValue() {
        return this.conversionValue;
    }

    /**
     * Resets this current panel instance.
     */
    public void resetPanel() {
        setState(PanelState.DISABLED);
        toSelector.setSelectedIndex(0);
        fromSelector.updateItems(((ConversionUnitType)toSelector.getSelectedItem()));
        toSelector.updateItems(((ConversionUnitType)fromSelector.getSelectedItem()));
        fromLabel.setText(fromLabelDefaultText);
        toLabel.setText(toLabelDefaultText);
        fromTextField.resetTextField();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == convertButton) {
            ConversionUnitType firstType = (ConversionUnitType)fromSelector.getSelectedItem();
            ConversionUnitType secondType = (ConversionUnitType)toSelector.getSelectedItem();

            if(fromTextField.getText() == fromLabelDefaultText
                || fromTextField.getText().isEmpty())
                return;

            float resultValue = Objects.requireNonNull(firstType).convertUnit(Float.parseFloat(fromTextField.getText()), secondType);
            // typically i'd round, but with the float being E^10+ in some scenarios, I figured i'd make it minimalistic.
            conversionValue = resultValue;
            toLabel.setText(toLabel.getText() + " " + resultValue);

            // let's disable this panel, and enable the next.
            ConversionApplicationStateController.activateNextConversionPanel();

            // if this is the last panel option, let's offer a summary and option to restart.
            if(isFinalPanel)
                ConversionApplication.INSTANCE.showReportSummary();
        }
    }
}

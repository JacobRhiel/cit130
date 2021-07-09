package edu.ccac.courses.cit130.assignments.first.conversion.ui.components.conversion.children;

import edu.ccac.courses.cit130.assignments.first.conversion.ui.components.conversion.ConversionPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jun 12, 2021
 */
public class ConversionUnitTextField extends JTextField implements FocusListener, DocumentListener {

    private final ConversionPanel conversionPanel;
    private final String rootText;

    /**
     * Typically I'd do a dynamic approach here but since this is a one off object,
     * I am going to define it manually.
     */
    public ConversionUnitTextField(ConversionPanel parent) {
        super("Enter an amount", 16);
        this.conversionPanel = parent;
        rootText = getText();
        addFocusListener(this);
        getDocument().addDocumentListener(this);
    }

    /**
     * Checks if this contains the default text.
     * @return If it is the default text.
     */
    public boolean isDefault() {
        return getText().equals(rootText);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(getText().equals(rootText))
            setText("");
    }

    /**
     * Updates the conversionPanel's display as we type.
     */
    private void updateWithKeyInput() {
        boolean matches = getText().matches("[0-9]+(\\.[0-9]*)?");
        conversionPanel.fireUnitAmountChanged(matches);
    }

    /**
     * Resets this text fields display text to it's default text.
     */
    public void resetTextField() {
        setText(rootText);
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(getText().equals(rootText))
        {

            return;
        }
        if(getText().isEmpty())
            setText(rootText);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateWithKeyInput();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateWithKeyInput();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateWithKeyInput();
    }
}

package edu.ccac.courses.cit130.assignments.second.inventory.gui.manager.input;

import edu.ccac.courses.cit130.assignments.second.inventory.gui.manager.input.filter.ProductFieldDocumentFilter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ProductEntryTextField extends JTextField implements FocusListener {

    private final String rootText;

    /**
     * Typically I'd do a dynamic approach here but since this is a one off object,
     * I am going to define it manually.
     */
    public ProductEntryTextField(InputType type) {
        super(16);
        rootText = getText();
        addFocusListener(this);
        ((AbstractDocument)getDocument()).setDocumentFilter(new ProductFieldDocumentFilter(type));
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(getText().equals(rootText))
            setText("");
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

    /**
     * Resets this text fields display text to it's default text.
     */
    public void resetTextField() {
        setText(rootText);
    }
}

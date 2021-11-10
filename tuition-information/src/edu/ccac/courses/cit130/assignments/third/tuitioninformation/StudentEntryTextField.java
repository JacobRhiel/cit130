package edu.ccac.courses.cit130.assignments.third.tuitioninformation;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Represents a text field backed by a document filter.
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jul 31, 2021
 */
public class StudentEntryTextField extends JTextField implements FocusListener {

    private final String rootText;

    /**
     * Typically I'd do a dynamic approach here but since this is a one off object,
     * I am going to define it manually.
     */
    public StudentEntryTextField(InputType type) {
        super(16);
        rootText = getText();
        addFocusListener(this);
        ((AbstractDocument)getDocument()).setDocumentFilter(new ProductFieldDocumentFilter(this, type));
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(getText().equals(rootText))
            setText("");
        CreateStudentPanel.pushTextFieldUpdate(this);
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(getText().equals(rootText))
            return;
        if(getText().isEmpty())
            setText(rootText);
        CreateStudentPanel.pushTextFieldUpdate(this);
    }

    /**
     * Resets this text fields display text to it's default text.
     */
    public void resetTextField() {
        setText(rootText);
    }
}

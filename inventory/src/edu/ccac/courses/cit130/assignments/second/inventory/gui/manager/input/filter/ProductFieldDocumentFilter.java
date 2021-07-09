package edu.ccac.courses.cit130.assignments.second.inventory.gui.manager.input.filter;

import edu.ccac.courses.cit130.assignments.second.inventory.gui.manager.input.InputType;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

/**
 * Custom document filter to prevent unwanted input.
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jul 05, 2021
 */
public class ProductFieldDocumentFilter extends DocumentFilter {

    private final InputType type;

    public ProductFieldDocumentFilter(InputType type) {
        this.type = type;
    }

    public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a) throws BadLocationException {
        String text = fb.getDocument().getText(0, fb.getDocument().getLength());
        text += str;
        if(matchesType(text)) {
            super.replace(fb, offs, length, str, a);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    public void insertString(FilterBypass fb, int offs, String str, AttributeSet a) throws BadLocationException {
        String text = fb.getDocument().getText(0, fb.getDocument().getLength());
        text += str;
        if(matchesType(text)) {
            super.insertString(fb, offs, str, a);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    private boolean matchesType(String text) {
        boolean matches = false;
        switch(type) {
            case NUMERIC -> matches = text.matches("[0-9]+");
            case DECIMAL -> matches = text.matches("[0-9]+(\\.[0-9]*)?");
            case STRING ->  matches = true;
        }
        return matches;
    }

}

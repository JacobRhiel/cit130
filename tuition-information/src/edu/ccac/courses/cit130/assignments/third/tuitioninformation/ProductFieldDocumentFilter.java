package edu.ccac.courses.cit130.assignments.third.tuitioninformation;

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

    private final StudentEntryTextField parent;
    private final InputType type;

    public ProductFieldDocumentFilter(StudentEntryTextField parent, InputType type) {
        this.parent = parent;
        this.type = type;
    }

    public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a) throws BadLocationException {
        String text = fb.getDocument().getText(0, fb.getDocument().getLength());
        text += str;
        if(matchesType(text)) {
            super.replace(fb, offs, length, str, a);
            CreateStudentPanel.pushTextFieldUpdate(parent);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    public void insertString(FilterBypass fb, int offs, String str, AttributeSet a) throws BadLocationException {
        String text = fb.getDocument().getText(0, fb.getDocument().getLength());
        text += str;
        if(matchesType(text)) {
            super.insertString(fb, offs, str, a);
            CreateStudentPanel.pushTextFieldUpdate(parent);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    private boolean matchesType(String text) {
        boolean matches;
        switch(type) {
            case ALPHA_SPACE:
                matches = text.matches("^[a-zA-Z\\s]*$");
                break;
            case NUMERIC:
                matches = text.matches("[0-9]+");
                break;
            case DECIMAL:
                matches = text.matches("[0-9]+(\\.[0-9]*)?");
                break;
            case STRING:
                matches = true;
                break;
            default:
                return false;
        }
        return matches;
    }

}

package edu.ccac.courses.cit130.assignments.third.tuitioninformation;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a button group of JRadioButton's.
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jul 30, 2021
 */
public class StudentStatusCheckbox extends JPanel {

    private final JRadioButton fullTimeStudentButton;
    private final JRadioButton partTimeStudentButton;

    public final Color defaultForegroundColor;

    public StudentStatusCheckbox() {
        setLayout(new FlowLayout(FlowLayout.LEADING));
        ButtonGroup buttonGroup = new ButtonGroup();
        fullTimeStudentButton = new JRadioButton("Full-Time");
        partTimeStudentButton = new JRadioButton("Part-Time");
        defaultForegroundColor = fullTimeStudentButton.getForeground();
        buttonGroup.add(fullTimeStudentButton);
        buttonGroup.add(partTimeStudentButton);
        add(fullTimeStudentButton);
        add(partTimeStudentButton);
    }

    /**
     * If the full-time student box is selected.
     * @return if it is selected.
     */
    public boolean isFullTimeStudent() {
        return fullTimeStudentButton.isSelected();
    }

    /**
     * Checks if there is an error, then displays it.
     * @return If an error occurred.
     */
    public boolean checkAndDisplayError() {
        if (!fullTimeStudentButton.isSelected() && !partTimeStudentButton.isSelected()) {
            fullTimeStudentButton.setForeground(Color.RED);
            partTimeStudentButton.setForeground(Color.RED);
            return true;
        }
        fullTimeStudentButton.setForeground(defaultForegroundColor);
        partTimeStudentButton.setForeground(defaultForegroundColor);
        return false;
    }

    /**
     * Update status if credits changed, without manually selecting.
     * @param credits The amount of credits.
     */
    public void updateStatusForCredits(int credits) {
        boolean isFullTime = credits >= 12;
        partTimeStudentButton.setSelected(!isFullTime);
        fullTimeStudentButton.setSelected(isFullTime);
    }

    /**
     * Clears the errors by setting foreground color back to default.
     */
    public void clearErrors() {
        fullTimeStudentButton.setForeground(defaultForegroundColor);
        partTimeStudentButton.setForeground(defaultForegroundColor);
    }
}

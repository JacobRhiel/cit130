package edu.ccac.courses.cit130.assignments.third.tuitioninformation;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jul 27, 2021
 */
public class TuitionInformationApplication extends JFrame {

    public TuitionInformationApplication() {
        setTitle("Student Management");
        setLayout(new BorderLayout());
        add(new CreateStudentPanel(), BorderLayout.WEST);
        add(new StudentDisplayPanel(), BorderLayout.CENTER);
        pack();
        setSize(new Dimension(1700, 490));
        // center
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        setupLaf();
        SwingUtilities.invokeLater(TuitionInformationApplication::new);
    }

    /**
     * Sets up the look and feel to look like windows.
     */
    private static void setupLaf() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}

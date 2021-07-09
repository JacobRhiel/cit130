package edu.ccac.courses.cit130.assignments.first.conversion;

import edu.ccac.courses.cit130.assignments.first.conversion.ui.components.root.RootFrame;

import javax.swing.*;

/**
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jun 08, 2021
 */
public class ConversionApplication {

    public static RootFrame INSTANCE;

    public static void main(String[] args) {

        setupLaf();

        SwingUtilities.invokeLater(() -> INSTANCE = new RootFrame());

    }

    private static void setupLaf() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

}

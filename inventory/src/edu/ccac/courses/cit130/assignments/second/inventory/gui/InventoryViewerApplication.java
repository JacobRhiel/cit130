package edu.ccac.courses.cit130.assignments.second.inventory.gui;

import edu.ccac.courses.cit130.assignments.second.inventory.gui.explorer.InventoryExplorerPanel;
import edu.ccac.courses.cit130.assignments.second.inventory.gui.manager.InventoryEntryPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Root frame for the inventory system.
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jul 04, 2021
 */
public class InventoryViewerApplication extends JFrame {

    public static InventoryExplorerPanel EXPLORER_PANEL;

    private JTabbedPane paneView;
    private InventoryEntryPanel inventoryEntryPanel;

    public InventoryViewerApplication() {
        setLayout(new BorderLayout());
        this.paneView = new JTabbedPane();
        this.paneView.setBorder(BorderFactory.createTitledBorder("Inventory Viewer"));
        EXPLORER_PANEL = new InventoryExplorerPanel();
        this.inventoryEntryPanel = new InventoryEntryPanel();
        this.paneView.addTab("Inventory Explorer", EXPLORER_PANEL);
        this.paneView.addTab("Inventory Management", this.inventoryEntryPanel);
        add(this.paneView, BorderLayout.CENTER);
        pack();
        setSize(new Dimension(1000, 325));
        // center
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        setupLaf();
        SwingUtilities.invokeLater(InventoryViewerApplication::new);
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



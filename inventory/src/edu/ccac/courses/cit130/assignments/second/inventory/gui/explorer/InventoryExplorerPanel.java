package edu.ccac.courses.cit130.assignments.second.inventory.gui.explorer;

import edu.ccac.courses.cit130.assignments.second.inventory.Inventory;
import edu.ccac.courses.cit130.assignments.second.inventory.Product;
import edu.ccac.courses.cit130.assignments.second.inventory.gui.table.InventoryTable;
import edu.ccac.courses.cit130.assignments.second.inventory.gui.table.InventoryTableModel;

import javax.swing.*;
import java.awt.*;

/**
 * Inventory explorer panel that holds the scrollable content.
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jul 05, 2021
 */
public class InventoryExplorerPanel extends JPanel {

    private final String defaultOverviewLabelText = "Inventory Overview: ";
    private final JLabel tableOverviewLabel;
    private final InventoryTable inventoryTable;

    public InventoryExplorerPanel() {
        this.tableOverviewLabel = new JLabel(defaultOverviewLabelText);
        setLayout(new BorderLayout());
        this.inventoryTable = new InventoryTable();
        JScrollPane inventoryTableScrollPane = new JScrollPane(this.inventoryTable);
        add(inventoryTableScrollPane, BorderLayout.CENTER);
        add(this.tableOverviewLabel, BorderLayout.SOUTH);
        updateOverviewLabel();
    }

    /**
     * API for updating the table.
     */
    public void updateTable() {
        ((InventoryTableModel)this.inventoryTable.getModel()).updateTable();
    }

    /**
     * API for removing a product.
     * @param product The product to remove.
     */
    public void removeProductEntry(Product product) {
        ((InventoryTableModel)this.inventoryTable.getModel()).removeInventoryEntry(product);
    }

    /**
     * Updates the overview label with table data.
     */
    public void updateOverviewLabel() {
        this.tableOverviewLabel.setText(defaultOverviewLabelText + "Total Value: $"
                + Inventory.getTotalInventoryValue()
                + ", Total Inventory: "
                + Inventory.getTotalInventoryCount() +
                ".");
    }

}

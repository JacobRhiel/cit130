package edu.ccac.courses.cit130.assignments.second.inventory.gui.table;

import edu.ccac.courses.cit130.assignments.second.inventory.Inventory;
import edu.ccac.courses.cit130.assignments.second.inventory.Product;

import javax.swing.*;

/**
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jul 05, 2021
 */
public class InventoryTable extends JTable {

    public InventoryTable() {
        super(new InventoryTableModel());
        JPopupMenu inventoryPopupMenu = new InventoryTableMenu(this);
        setFillsViewportHeight(true);
        setComponentPopupMenu(inventoryPopupMenu);
        Inventory.addProduct(new Product(0, "Copy Paper", 11.00f, 100, 0, 0));
        Inventory.addProduct(new Product(1, "Black Ink", 38.44f, 27, 33, 50));
        Inventory.addProduct(new Product(2, "Colored Ink", 41.15f, 11, 89, 44));
        ((InventoryTableModel)dataModel).updateTable();
    }

}

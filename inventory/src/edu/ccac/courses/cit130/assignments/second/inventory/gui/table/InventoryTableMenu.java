package edu.ccac.courses.cit130.assignments.second.inventory.gui.table;

import edu.ccac.courses.cit130.assignments.second.inventory.Inventory;
import edu.ccac.courses.cit130.assignments.second.inventory.Product;
import edu.ccac.courses.cit130.assignments.second.inventory.gui.InventoryViewerApplication;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;

/**
 * Popup menu for item selection in the table.
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jul 05, 2021
 */
public class InventoryTableMenu extends JPopupMenu {

    public InventoryTableMenu(JTable table) {
        JMenuItem deleteOption = new JMenuItem("Delete");
        add(deleteOption);
        InventoryTableMenu instance = this;
        deleteOption.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            // returns the row number of the point of mouse to component.
            int rowAtPoint = table.rowAtPoint(SwingUtilities.convertPoint(table, new Point(0, 0), table));
            if (rowAtPoint > -1) {
                table.setRowSelectionInterval(rowAtPoint, rowAtPoint);
            }
            // gets the product at row number.
            Product product = ((InventoryTableModel)table.getModel()).getProductAtRow(rowAtPoint);
            int selected = JOptionPane.showOptionDialog(this, "Are you sure you want to delete this entry?",
                    "Delete entry", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    new Object[]{ "Yes", "No", "Cancel" },
                    "Cancel");
            switch(selected) {
                case JOptionPane.YES_OPTION:
                    Inventory.deleteProductById(product.getNumber());
                    InventoryViewerApplication.EXPLORER_PANEL.removeProductEntry(product);
                    break;
                case JOptionPane.NO_OPTION:
                case JOptionPane.CANCEL_OPTION:
                    break;
            }
        }));
        // this code highlights the selected row.
        addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(() -> {
                    int rowAtPoint = table.rowAtPoint(SwingUtilities.convertPoint(instance, new Point(0, 0), table));
                    if (rowAtPoint > -1) {
                        table.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                    }
                });
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                // TODO Auto-generated method stub

            }
        });
    }

}

package edu.ccac.courses.cit130.assignments.second.inventory.gui.table;

import edu.ccac.courses.cit130.assignments.second.inventory.Inventory;
import edu.ccac.courses.cit130.assignments.second.inventory.Product;
import edu.ccac.courses.cit130.assignments.second.inventory.gui.InventoryViewerApplication;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Override of DefaultTableModel to allow custom features.
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jul 04, 2021
 */
public class InventoryTableModel extends DefaultTableModel {

    /**
     * Fields found by reflection.
     */
    private final Field[] fields;
    /**
     * Products of the model.
     */
    private final Map<Integer, Product> products;

    public InventoryTableModel() {
        this.products = new HashMap<>();
        this.fields = Product.class.getDeclaredFields();
        // find fields reflectively and format the naming from camel case to add spaces in between humps
        // and capitalize the first letter.
        for (Field field : fields) {
            String formattedFieldName = field.getName().replaceAll(
                    String.format("%s|%s|%s",
                            "(?<=[A-Z])(?=[A-Z][a-z])",
                            "(?<=[^A-Z])(?=[A-Z])",
                            "(?<=[A-Za-z])(?=[^A-Za-z])"
                    ),
                    " "
            );
            addColumn(formattedFieldName.substring(0, 1).toUpperCase()
                    + formattedFieldName.substring(1));
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        return super.getValueAt(row, column);
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        Product product = products.get(row);
        // defines values based on field name.
        switch (fields[column].getName()) {
            case "description":
                product.setDescription((String) aValue);
                super.setValueAt(product.getDescription(), row, column);
                break;
            case "price":
                if (!checkValidFloat(aValue)) return;
                float price = formattedFloat(Float.parseFloat((String) aValue));
                product.setPrice(price);
                product.synchronize();
                super.setValueAt(price, row, column);
                super.setValueAt(Inventory.getTotalInventoryValueForProductId(product.getNumber()), row, column + 5);
                break;
            case "quantitySold":
                if (!checkValidInt(aValue)) return;
                int quantitySold = Integer.parseInt((String) aValue);
                product.setQuantitySold(quantitySold);
                product.synchronize();
                super.setValueAt(quantitySold, row, column);
                super.setValueAt(Inventory.getTotalInventoryForProductId(product.getNumber()), row, column + 1);
                super.setValueAt(Inventory.getTotalInventoryValueForProductId(product.getNumber()), row, column + 2);
                super.setValueAt(Inventory.shouldOrderProductById(product.getNumber()), row, column + 3);
                break;
            case "onHandQuantity":
                if (!checkValidInt(aValue)) return;
                int onHandQuantity = Integer.parseInt((String) aValue);
                product.setOnHandQuantity(onHandQuantity);
                product.synchronize();
                super.setValueAt(onHandQuantity, row, column);
                super.setValueAt(Inventory.getTotalInventoryForProductId(product.getNumber()), row, column + 3);
                super.setValueAt(Inventory.getTotalInventoryValueForProductId(product.getNumber()), row, column + 4);
                super.setValueAt(Inventory.shouldOrderProductById(product.getNumber()), row, column + 5);
                break;
            case "onOrderQuantity":
                if (!checkValidInt(aValue)) return;
                int onOrderQuantity = Integer.parseInt((String) aValue);
                product.setOnOrderQuantity(onOrderQuantity);
                product.synchronize();
                super.setValueAt(onOrderQuantity, row, column);
                super.setValueAt(Inventory.getTotalInventoryForProductId(product.getNumber()), row, column + 2);
                super.setValueAt(Inventory.getTotalInventoryValueForProductId(product.getNumber()), row, column + 3);
                super.setValueAt(Inventory.shouldOrderProductById(product.getNumber()), row, column + 4);
                break;
            default:
                super.setValueAt(aValue, row, column);
                break;
        }
        // update the overview label.
        InventoryViewerApplication.EXPLORER_PANEL.updateOverviewLabel();
    }

    /**
     * Checks whether or not the value is a float.
     * @param aValue The value to check.
     * @return If the object is valid format.
     */
    private boolean checkValidFloat(Object aValue) {
        if(aValue instanceof String) {
            return ((String)aValue).matches("^[0-9]+\\.[0-9]+");
        }
        return false;
    }

    /**
     * Checks whether or not the value is a int.
     * @param aValue The value to check.
     * @return If the object is valid format.
     */
    private boolean checkValidInt(Object aValue) {
        if(aValue instanceof String) {
            return ((String)aValue).matches("^[0-9]+$");
        }
        return false;
    }

    /**
     * Formats a float value to the n2. hundredths
     * @param value The value to format.
     * @return Then formatted value.
     */
    private float formattedFloat(float value) {
        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(2);
        return Float.parseFloat(format.format(value).replace(",", ""));
    }

    /**
     * Updates the table rows and column values.
     */
    public void updateTable() {
        for (Product product : Inventory.getAllProducts()) {
            if(!products.containsKey(product.getNumber()))
                insertInventoryEntry(product);
        }
        for (int index = 0; index < getRowCount(); index++) {
            Product product = getProductAtRow(index);
            super.setValueAt(Inventory.getTotalInventoryValueForProductId(product.getNumber()), index, findColumn("Inventory Value"));
        }
    }

    /**
     * Removes entry from inventory.
     * @param product The product to remove.
     */
    public void removeInventoryEntry(Product product) {
        if(!products.containsKey(product.getNumber())) return;
        boolean removed = products.remove(product.getNumber(), product);
        if(!removed) return;
        removeRow(product.getNumber());
    }

    /**
     * Inserts product to inventory model.
     * @param product The product to insert.
     */
    public void insertInventoryEntry(Product product) {
        int row = getRowCount();
        products.put(row, product);
        insertRow(row, new Object[] {
                product.getNumber(), product.getDescription(), product.getPrice(),
                product.getOnHandQuantity(), product.getOnOrderQuantity(),
                product.getQuantitySold(), Inventory.getTotalInventoryForProductId(product.getNumber()),
                Inventory.getTotalInventoryValueForProductId(product.getNumber()),
                Inventory.shouldOrderProductById(product.getNumber())
        });
    }

    /**
     * Gets the product at row index.
     * @param rowIndex The index of the row.
     * @return The product at the row index.
     */
    public Product getProductAtRow(int rowIndex) {
        return products.getOrDefault(rowIndex, null);
    }

}

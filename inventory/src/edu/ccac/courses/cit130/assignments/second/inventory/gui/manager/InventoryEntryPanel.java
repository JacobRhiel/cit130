package edu.ccac.courses.cit130.assignments.second.inventory.gui.manager;

import edu.ccac.courses.cit130.assignments.second.inventory.Inventory;
import edu.ccac.courses.cit130.assignments.second.inventory.Product;
import edu.ccac.courses.cit130.assignments.second.inventory.gui.InventoryViewerApplication;
import edu.ccac.courses.cit130.assignments.second.inventory.gui.manager.input.InputType;
import edu.ccac.courses.cit130.assignments.second.inventory.gui.manager.input.ProductEntryTextField;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Wrapper panel for new product entry.
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jul 04, 2021
 */
public class InventoryEntryPanel extends JPanel {

    /**
     * The text fields for quick reference.
     */
    private final Map<Integer, ProductEntryTextField> textFields = new HashMap<>();

    public InventoryEntryPanel() {
        setLayout(new BorderLayout());
        JPanel productCreationPanel = new JPanel();
        // 6 = label count, 2 = column for label + item.
        productCreationPanel.setLayout(new GridLayout(6, 2));
        JLabel productNumber = new JLabel("Product Number");
        JLabel productDescription = new JLabel("Product Description");
        JLabel productPrice = new JLabel("Product Price");
        JLabel quantityOnHand = new JLabel("Quantity On Hand");
        JLabel quantityOnOrder = new JLabel("Quantity On Order");
        JLabel quantitySold = new JLabel("Quantity Sold");
        JButton addProductButton = new JButton("Add Product");
        JButton clearDataButton = new JButton("Clear Product");

        textFields.put(0, new ProductEntryTextField(InputType.NUMERIC));
        textFields.put(1, new ProductEntryTextField(InputType.STRING));
        textFields.put(2, new ProductEntryTextField(InputType.DECIMAL));
        textFields.put(3, new ProductEntryTextField(InputType.NUMERIC));
        textFields.put(4, new ProductEntryTextField(InputType.NUMERIC));
        textFields.put(5, new ProductEntryTextField(InputType.NUMERIC));

        // row 1
        productCreationPanel.add(productNumber);
        productCreationPanel.add(textFields.get(0));
        productCreationPanel.add(productDescription);
        productCreationPanel.add(textFields.get(1));
        productCreationPanel.add(productPrice);
        productCreationPanel.add(textFields.get(2));
        productCreationPanel.add(quantityOnHand);
        productCreationPanel.add(textFields.get(3));
        productCreationPanel.add(quantityOnOrder);
        productCreationPanel.add(textFields.get(4));
        productCreationPanel.add(quantitySold);
        productCreationPanel.add(textFields.get(5));
        add(productCreationPanel, BorderLayout.NORTH);
        addProductButton.addActionListener(e -> {
            Product newProduct = new Product();
            newProduct.setNumber(Integer.parseInt(textFields.get(0).getText()));
            if(Inventory.productNumberUsed(newProduct.getNumber())) {
                JOptionPane.showMessageDialog(this, "Product number is already in use!");
                textFields.get(0).setSelectionColor(Color.RED);
                return;
            }
            newProduct.setDescription(textFields.get(1).getText());
            DecimalFormat format = new DecimalFormat();
            format.setMaximumFractionDigits(2);
            Float floatValue = Float.parseFloat(textFields.get(2).getText());
            newProduct.setPrice(Float.parseFloat(format.format(floatValue).replace(",", "")));
            newProduct.setOnHandQuantity(Integer.parseInt(textFields.get(3).getText()));
            newProduct.setOnOrderQuantity(Integer.parseInt(textFields.get(4).getText()));
            newProduct.setQuantitySold(Integer.parseInt(textFields.get(4).getText()));
            Inventory.addProduct(newProduct);
            InventoryViewerApplication.EXPLORER_PANEL.updateTable();
            InventoryViewerApplication.EXPLORER_PANEL.updateOverviewLabel();
        });
        clearDataButton.addActionListener(e -> {
            for (ProductEntryTextField field : textFields.values()) {
                field.resetTextField();
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(clearDataButton);
        buttonPanel.add(addProductButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

}


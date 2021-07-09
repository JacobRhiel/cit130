package edu.ccac.courses.cit130.assignments.second.inventory;

import java.util.*;

/**
 * Represents a companies product inventorry.
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jul 04, 2021
 */
public class Inventory {

    /**
     * Products of this inventory.
     */
    private static final Map<Integer, Product> products = new HashMap<>();

    /**
     * Adds a product to this inventory.
     * @param product The product to add to the inventory.
     */
    public static void addProduct(Product product) {
        products.putIfAbsent(product.getNumber(), product);
    }

    /**
     * Delete product from inventory.
     * @param number The number of the product to find and delete.
     * @return If the product was deleted.
     */
    public static boolean deleteProduct(int number) {
        if(!products.containsKey(number)) return false;
        Product product = products.get(number);
        return products.remove(number, product);
    }

    /**
     * Get product by its id.
     * @param id The product id.
     * @return The product found or null.
     */
    public static Product getProductById(int id) {
        return products.getOrDefault(id, null);
    }

    /**
     * Gets all products in this inventory.
     * @return The collection of products.
     */
    public static Collection<Product> getAllProducts() {
        return products.values();
    }

    /**
     * Get total product count for product id.
     * @param id The id of the product to find the total inventory for.
     * @return The total count.
     */
    public static int getTotalInventoryForProductId(int id) {
        Product product = getProductById(id);
        if(product == null) return 0;
        return (product.getOnHandQuantity() + product.getOnOrderQuantity()) - product.getQuantitySold();
    }

    /**
     * Gets the total inventory value of the products found by id.
     * @param id The id of the products to find.
     * @return The value of the total inventory of products by id.
     */
    public static float getTotalInventoryValueForProductId(int id) {
        Product product = getProductById(id);
        if(product == null) return 0;
        return product.getPrice() * (product.getOnHandQuantity() + product.getOnOrderQuantity()) - product.getQuantitySold();
    }

    /**
     * Gets the total inventory value.
     * @return The total inventory value.
     */
    public static float getTotalInventoryValue() {
        Collection<Product> products = getAllProducts();
        float totalValue = 0f;
        for (Product product : products) {
            totalValue += getTotalInventoryValueForProductId(product.getNumber());
        }
        return totalValue;
    }

    /**
     * Gets the total inventory count.
     * @return The total count of inventory.
     */
    public static int getTotalInventoryCount() {
        Collection<Product> products = getAllProducts();
        int totalInventory = 0;
        for (Product product : products) {
            totalInventory += getTotalInventoryForProductId(product.getNumber());
        }
        return totalInventory;
    }

    /**
     * Checks if the product number is occupied.
     * @param number The number of the product.
     * @return Whether or not the product number is used.
     */
    public static boolean productNumberUsed(int number) {
        Collection<Product> products = getAllProducts();
        for (Product product : products) {
            if(product.getNumber() == number)
                return true;
        }
        return false;
    }

    /**
     * Deletes the product by its id.
     * @param number The id of the product.
     * @return Whether the product was deleted.
     */
    public static boolean deleteProductById(int number) {
        Collection<Product> products = getAllProducts();
        for (Product product : products) {
            if(product.getNumber() == number)
                return deleteProduct(number);
        }
        return false;
    }

}

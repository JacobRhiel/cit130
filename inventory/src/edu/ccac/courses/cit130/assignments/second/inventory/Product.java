package edu.ccac.courses.cit130.assignments.second.inventory;

/**
 * Represents a Product to store in Inventory.
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jul 04, 2021
 */
public class Product {

    private int number;
    private String description;
    private float price;
    private int onHandQuantity;
    private int onOrderQuantity;
    private int quantitySold;

    private int inventoryQuantity;
    private float inventoryValue;

    private boolean shouldOrder = false;

    /**
     * Empty constructor for builder object.
     */
    public Product() {

    }

    /**
     * Constructor to create a new object.
     * @param productNumber The product number.
     * @param productDescription The product description.
     * @param productPrice The product price.
     * @param onHandQuantity The on hand quantity of the product.
     * @param onOrderQuantity The on order quantity.
     * @param quantitySold The quantity of the product sold.
     */
    public Product(int productNumber, String productDescription,
                     float productPrice, int onHandQuantity, int onOrderQuantity,
                     int quantitySold) {
        this.number = productNumber;
        this.description = productDescription;
        this.price = productPrice;
        this.onHandQuantity = onHandQuantity;
        this.onOrderQuantity = onOrderQuantity;
        this.quantitySold = quantitySold;
        updateTotalQuantity();
        updateTotalValue();
    }

    public int getNumber() {
        return this.number;
    }

    public String getDescription() {
        return this.description;
    }

    public float getPrice() {
        return this.price;
    }

    public int getOnHandQuantity() {
        return this.onHandQuantity;
    }

    public int getOnOrderQuantity() {
        return this.onOrderQuantity;
    }

    public int getQuantitySold() {
        return this.quantitySold;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setOnHandQuantity(int onHandQuantity) {
        this.onHandQuantity = onHandQuantity;
        updateTotalQuantity();
    }

    public void setOnOrderQuantity(int onOrderQuantity) {
        this.onOrderQuantity = onOrderQuantity;
        updateTotalQuantity();
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
        updateTotalQuantity();
    }

    /**
     * Sets the total quantity of product.
     */
    public void updateTotalQuantity() {
        this.inventoryQuantity = (getOnHandQuantity() + getOnOrderQuantity()) - getQuantitySold();
    }

    public int getInventoryQuantity() {
        return this.inventoryQuantity;
    }

    /**
     * Sets the total value of product.
     */
    public void updateTotalValue() {
        this.inventoryValue = getPrice() * (getOnHandQuantity() + getOnOrderQuantity()) - getQuantitySold();
    }

    public float getInventoryValue() {
        return this.inventoryValue;
    }

    /**
     * Synchronization for product values.
     */
    public void synchronize() {
        updateTotalValue();
        updateTotalQuantity();
        shouldOrderProduct();
    }

    /**
     * Tells whether or not the product should be ordered based on quantity.
     * @return Whether the product needs to be ordered.
     */
    public boolean shouldOrderProduct() {
        return shouldOrder = getOnHandQuantity() + getOnOrderQuantity() < 50;
    }

    @Override
    public String toString() {
        return "Product{" +
                "number=" + number +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", onHandQuantity=" + onHandQuantity +
                ", onOrderQuantity=" + onOrderQuantity +
                ", quantitySold=" + quantitySold +
                ", shouldOrder=" + shouldOrder +
                ", totalValue=" + inventoryValue +
                ", totalQuantity=" + inventoryQuantity +
                '}';
    }
}

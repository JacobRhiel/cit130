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
    }

    public void setOnOrderQuantity(int onOrderQuantity) {
        this.onOrderQuantity = onOrderQuantity;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
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
                '}';
    }
}

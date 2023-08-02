/**
 * The Item class represents an item that can be sold in a vending machine.
 * It contains information about the item's name, calories, price, and saleability.
 */
public class Item {
    private String name;
    private int calories;
    private double price;
    private boolean saleable;

    /**
     * Creates a new Item instance with the specified attributes.
     *
     * @param name The name of the item.
     * @param calories The number of calories in the item.
     * @param price The price of the item.
     * @param saleable Whether the item is saleable (true) or not (false).
     */
    public Item(String name, int calories, double price, boolean saleable) {
        this.name = name;
        this.calories = calories;
        this.price = price;
        this.saleable = saleable;
    }

    /**
     * Retrieves the name of the item.
     *
     * @return The name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the number of calories in the item.
     *
     * @return The number of calories in the item.
     */
    public int getCalories() {
        return calories;
    }

    /**
     * Retrieves the price of the item.
     *
     * @return The price of the item.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Checks whether the item is saleable or not.
     *
     * @return True if the item is saleable, false otherwise.
     */
    public boolean isSaleable() {
        return saleable;
    }

    /**
     * Sets the name of the item.
     *
     * @param name The new name of the item.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the number of calories in the item.
     *
     * @param calories The new number of calories in the item.
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * Sets the price of the item.
     *
     * @param price The new price of the item.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets whether the item is saleable or not.
     *
     * @param saleable The new saleability status of the item (true for saleable, false otherwise).
     */
    public void setSaleable(boolean saleable) {
        this.saleable = saleable;
    }
}

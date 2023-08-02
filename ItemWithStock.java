/**
 * The ItemWithStock class represents an item along with its stock quantity in a vending machine.
 * It encapsulates an Item object and the corresponding stock quantity available for that item.
 */
public class ItemWithStock {
    private Item item;
    private int stock;

    /**
     * Creates a new ItemWithStock instance with the specified item and stock quantity.
     *
     * @param item The item associated with this ItemWithStock.
     * @param stock The quantity of the item available in stock.
     */
    public ItemWithStock(Item item, int stock) {
        this.item = item;
        this.stock = stock;
    }

    /**
     * Retrieves the item associated with this ItemWithStock.
     *
     * @return The item associated with this ItemWithStock.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Retrieves the stock quantity of the item.
     *
     * @return The quantity of the item available in stock.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the stock quantity of the item.
     *
     * @param stock The new stock quantity to be set.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
}

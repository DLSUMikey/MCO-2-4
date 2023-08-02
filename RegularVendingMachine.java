/**
 * The RegularVendingMachine class represents a type of vending machine that performs regular vending operations.
 * It is a subclass of the VendingMachine class and provides a specific implementation for the performVending method.
 */
public class RegularVendingMachine extends VendingMachine {

    /**
     * Constructs a RegularVendingMachine instance with the specified name, maximum slots, and maximum stocks.
     *
     * @param name      The name of the regular vending machine.
     * @param maxSlots  The maximum number of slots in the vending machine.
     * @param maxStocks The maximum number of stocks per slot in the vending machine.
     */
    public RegularVendingMachine(String name, int maxSlots, int maxStocks) {
        super(name, maxSlots, maxStocks);
    }

    /**
     * Performs regular vending operations specific to the RegularVendingMachine.
     * This method is an implementation of the abstract method in the parent class.
     * It prints a message indicating that the Regular Vending Machine is vending.
     */
    @Override
    public void performVending() {
        System.out.println("Regular Vending Machine is vending.");
    }
}

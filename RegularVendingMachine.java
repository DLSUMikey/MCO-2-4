public class RegularVendingMachine extends VendingMachine {
    public RegularVendingMachine(String name, int maxSlots, int maxStocks) {
        super(name, maxSlots, maxStocks);
    }

    // Implementation of performVending() specific to RegularVendingMachine
    @Override
    public void performVending() {
        // Add custom logic for RegularVendingMachine
        System.out.println("Regular Vending Machine is vending.");
    }
    

    @Override
    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}

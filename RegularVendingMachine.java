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
    public void addItem(Item item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addItem'");
    }

    @Override
    public Currency getCurrency() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCurrency'");
    }
}

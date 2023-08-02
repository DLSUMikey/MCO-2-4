public class SpecialVendingMachine extends VendingMachine {
    public SpecialVendingMachine(String name, int maxSlots, int maxStocks) {
        super(name, maxSlots, maxStocks);
    }

    // Implementation of performVending() specific to SpecialVendingMachine
    @Override
    public void performVending() {
        // Add custom logic for SpecialVendingMachine
        System.out.println("Special Vending Machine is vending.");
    }


    @Override
    public Currency getCurrency() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCurrency'");
    }
}
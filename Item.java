public class Item {
    private String name;
    private int calories;
    private double price;
    private boolean saleable;

    public Item(String name, int calories, double price, boolean saleable) {
        this.name = name;
        this.calories = calories;
        this.price = price;
        this.saleable = saleable;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public double getPrice() {
        return price;
    }

    public boolean isSaleable() {
        return saleable;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSaleable(boolean saleable) {
        this.saleable = saleable;
    }
}

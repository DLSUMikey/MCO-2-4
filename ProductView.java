import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * The ProductView class represents a dialog for creating a custom meal with selected toppings.
 * It allows the customer to choose toppings for the meal, calculate the price and calories, and add it to their cart.
 */
@SuppressWarnings("unused")
public class ProductView extends JDialog {

    private Map<String, ItemWithStock> availableToppings;
    private String selectedBread;
    private String selectedMeat;
    private Customer customer;

    /**
     * Constructs a ProductView instance for creating a custom meal.
     *
     * @param availableToppings A map containing available toppings with their stock information.
     * @param customer          The Customer instance representing the current user.
     * @param selectedBread     The selected bread for the meal.
     * @param selectedMeat      The selected meat for the meal.
     */
    public ProductView(Map<String, ItemWithStock> availableToppings, Customer customer,
                       String selectedBread, String selectedMeat) {
        this.availableToppings = availableToppings;
        this.customer = customer;
        this.selectedBread = selectedBread;
        this.selectedMeat = selectedMeat;

        setTitle("Create a meal");
        setSize(300, 200);
        setModal(true);

        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

        Map<JCheckBox, Item> checkBoxes = new HashMap<>();
        for (Map.Entry<String, ItemWithStock> entry : availableToppings.entrySet()) {
            JCheckBox checkBox = new JCheckBox(entry.getKey());
            checkBoxes.put(checkBox, entry.getValue().getItem());
            checkBoxPanel.add(checkBox);
        }

        JButton finishButton = new JButton("Finish");
        finishButton.addActionListener(e -> {
            double price = 0.0;
            int calories = 0;

            ItemWithStock breadItemWithStock = availableToppings.get(selectedBread);
            ItemWithStock meatItemWithStock = availableToppings.get(selectedMeat);
            if (breadItemWithStock != null && breadItemWithStock.getItem() != null
                    && meatItemWithStock != null && meatItemWithStock.getItem() != null) {
                price += breadItemWithStock.getItem().getPrice() + meatItemWithStock.getItem().getPrice();
                calories += breadItemWithStock.getItem().getCalories() + meatItemWithStock.getItem().getCalories();
            }

            List<String> messages = new ArrayList<>();
            messages.add("Slicing Bread...");
            messages.add("Preparing Meat...");
            for (Map.Entry<JCheckBox, Item> entry : checkBoxes.entrySet()) {
                JCheckBox checkBox = entry.getKey();
                Item topping = entry.getValue();
                if (checkBox.isSelected()) {
                    price += topping.getPrice();
                    calories += topping.getCalories();
                    switch (topping.getName()) {
                        case "Cheese":
                            messages.add("Slicing Cheese...");
                            break;
                        case "Bacon Strips":
                            messages.add("Adding Bacon...");
                            break;
                        case "Ham Strips":
                            messages.add("Adding Ham...");
                            break;
                        case "Lettuce":
                            messages.add("Adding Lettuce...");
                            break;
                        case "Pickles":
                            messages.add("Adding Pickles...");
                            break;
                        case "Mustard":
                            messages.add("Spreading Mustard...");
                            break;
                        case "Ketchup":
                            messages.add("Spreading Ketchup...");
                            break;
                        case "Onion":
                            messages.add("Topping Onions...");
                            break;
                        case "Turkey Strips":
                            messages.add("Adding Turkey Strips...");
                            break;
                        case "Sausage":
                            messages.add("Adding Sausage...");
                            break;
                        case "Cucumber":
                            messages.add("Topping Cucumber...");
                            break;
                        case "Mayonnaise":
                            messages.add("Spreading Mayonnaise...");
                            break;
                        default:
                            break;
                    }
                    customer.addItemToCart(topping.getName());
                }
            }

            new PreparingMealView(messages).setVisible(true);

            System.out.println("The meal's price is: " + price);
            System.out.println("The meal's calories are: " + calories);
            dispose();
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            dispose();
        });

        getContentPane().add(checkBoxPanel, BorderLayout.CENTER);
        getContentPane().add(finishButton, BorderLayout.SOUTH);
        getContentPane().add(cancelButton, BorderLayout.NORTH);
        pack();
    }
}

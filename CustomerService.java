import java.util.Map;

public interface CustomerService {
    Map<String, Integer> getCart();
    void addItemToCart(String itemName);
    void clearCart();
}
package rvt;

import java.util.HashMap;
import java.util.Set;

public class Warehouse {
    private HashMap<String, Integer> prices = new HashMap<String, Integer>();
    private HashMap<String, Integer> stocks = new HashMap<String, Integer>();
    Warehouse() {

    }

    void addProduct(String product, int price, int stock) {
        prices.put(product, price);
        stocks.put(product, stock);
    }

    int price(String product) {
        Integer price = prices.get(product);
        return price == null ? -99 : price;
    }

    int stock(String product) {
        Integer stock = stocks.get(product);
        return stock == null ? 0 : stock;
    }

    boolean take(String product) {
        Integer stock = stocks.get(product);
        if (stock != null && stock > 0) { // Dangerous to write the condition, since if stock is null, the Integer constructor would trow a conversion error, but since we check if it's null first, thanks to the compiler, it doesn't check any further if the first condition is false.
            stocks.put(product, stock - 1);
            return true;
        }
        return false;
    }

    Set<String> products() {
        return prices.keySet();
    }
}

package rvt;

import java.util.HashMap;

public class ShoppingCart {
    HashMap<String, Item> content = new HashMap<String, Item>();

    ShoppingCart() {};

    void add(String product, int price) {
        Item existing_product = content.get(product);
        if (existing_product != null) {
            existing_product.increaseQuantity();
        }
        else {
            content.put(product, new Item(product, 1, price));
        }
    }

    int price() {
        int sum = 0;
        for (var value : content.values()) {
            sum += value.price();
        }
        return sum;
    }

    void print() {
        for (var value : content.values()) {
            System.out.println(value.toString());
        }
    }

}

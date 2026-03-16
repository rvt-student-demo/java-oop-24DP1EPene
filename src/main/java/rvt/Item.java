package rvt;

public class Item {
    private String product;
    private int quantity;
    private int unitPrice;

    Item(String product, int qty, int unitPrice) {
        this.product = product;
        this.quantity = qty;
        this.unitPrice = unitPrice;
    }

    int price() {
        return quantity * unitPrice;
    }

    void increaseQuantity() {
        quantity += 1;
    }

    @Override
    public String toString() {
        return product + ": " + quantity;
    }
}

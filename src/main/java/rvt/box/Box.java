package rvt.box;

import java.util.ArrayList;

public class Box implements Packable {
    private double maximumCapacity;
    private ArrayList<Packable> items;

    public Box(double maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
        this.items = new ArrayList<>();
    }

    public void add(Packable item) {
        if (this.weight() + item.weight() <= this.maximumCapacity) {
            this.items.add(item);
        }
    }

    public double weight() {
        double totalWeight = 0;
        for (Packable item : items) {
            totalWeight += item.weight();
        }
        return totalWeight;
    }

    public String toString() {
        return "Box: " + this.items.size() + " items, total weight " + this.weight() + " kg";
    }
}
package com.example.foodordering.Activity.Domain;

public class Item {

    private int foodId,qty;

    public Item(int foodId, int qty) {
        this.foodId = foodId;
        this.qty = qty;
    }

    public int getFoodId() {
        return foodId;
    }

    public int getQty() {
        return qty;
    }
}

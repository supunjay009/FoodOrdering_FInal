package com.example.foodordering.Activity.Domain;

public class Item {

    private int foodId,qty;
    private String name,image;

    public Item() {
    }

    public Item(int foodId, int qty, String name, String imageURL) {
        this.foodId = foodId;
        this.qty = qty;
        this.name = name;
        this.image = image;
    }

    public int getFoodId() {
        return foodId;
    }

    public int getQty() {
        return qty;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}

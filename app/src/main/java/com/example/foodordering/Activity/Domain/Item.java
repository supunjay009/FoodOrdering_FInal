package com.example.foodordering.Activity.Domain;

public class Item {

    private int id,qty;
    private String name,image;

    public Item() {
    }

    public Item(int id, int qty, String name, String image) {
        this.id = id;
        this.qty = qty;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
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

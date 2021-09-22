package com.example.foodordering.Activity.Domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Orders {
    private int id;
    private boolean served;
    private HashMap<String,Item> items;

    public Orders() {
    }

    public Orders(int id, boolean served, HashMap<String,Item> items) {
        this.id = id;
        this.served = served;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public boolean isServed() {
        return served;
    }

    public HashMap<String, Item> getItems() {
        return items;
    }

    public ArrayList<Item> getItemList() {
        ArrayList<Item> itemList = new ArrayList<>();
        itemList.addAll(items.values());
        return itemList;
    }
}

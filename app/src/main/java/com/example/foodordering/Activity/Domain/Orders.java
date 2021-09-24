package com.example.foodordering.Activity.Domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Orders {
    private int id,tableNo;
    private boolean served;
    private HashMap<String,Item> items;
    private String name;

    public Orders() {
    }

    public Orders(int id, int tableNo, boolean served, HashMap<String, Item> items) {
        this.id = id;
        this.tableNo = tableNo;
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

    public void setServed(boolean served) {
        this.served = served;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTableNo() {
        return tableNo;
    }
}

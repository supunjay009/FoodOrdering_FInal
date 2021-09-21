package com.example.foodordering.Activity.Domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Orders {
    private int id;
    private boolean served;
    private HashMap<String,HashMap<String,Integer>> items;

    public Orders() {
    }

    public Orders(int id, boolean served, HashMap<String,HashMap<String,Integer>> items) {
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

    public HashMap<String, HashMap<String, Integer>> getItems() {
        return items;
    }

    public ArrayList<Item> getItemList() {
        ArrayList<Item> itemList = new ArrayList<>();

        for (HashMap<String,Integer> item: items.values()) {
            Item i = new Item(item.get("food"), item.get("qty"));
            itemList.add(i);
        }

        return itemList;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", served=" + served +
                ", items=" + items +
                '}';
    }
}

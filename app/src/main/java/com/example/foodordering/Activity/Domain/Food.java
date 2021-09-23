package com.example.foodordering.Activity.Domain;

public class Food {

    private int id;
    private double price,rating;
    private String name,description,image;

    public Food() {
    }

    public Food(int id, double price, double rating, String name, String description, String image) {
        this.id = id;
        this.price = price;
        this.rating = rating;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }
}

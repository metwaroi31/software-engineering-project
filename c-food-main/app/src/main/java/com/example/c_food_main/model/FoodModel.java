package com.example.c_food_main.model;

public class FoodModel {

    private int image,calories;
    private String name;

    public FoodModel(int image, String name, int calories) {
        this.image = image;
        this.name = name;
        this.calories = calories;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}

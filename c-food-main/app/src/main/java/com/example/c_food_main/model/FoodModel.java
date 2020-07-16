package com.example.c_food_main.model;

public class FoodModel {

    private int image;
    private String name, calories;

    public FoodModel(int image, String name, String calories) {
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

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }
}

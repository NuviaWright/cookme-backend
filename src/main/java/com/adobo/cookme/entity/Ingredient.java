package com.adobo.cookme.entity;

public class Ingredient {

    private String ingredientName;
    private String measurement;

    public Ingredient() {
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }
}

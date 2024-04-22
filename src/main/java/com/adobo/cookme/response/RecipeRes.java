package com.adobo.cookme.response;

import com.adobo.cookme.entity.MealPreview;

import java.util.List;

public class RecipeRes {
    private List<MealPreview> meals;

    public RecipeRes() {
    }

    public List<MealPreview> getMeals() {
        return meals;
    }

    public void setMeals(List<MealPreview> meals) {
        this.meals = meals;
    }
}

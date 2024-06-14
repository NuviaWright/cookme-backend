package com.adobo.cookme.response;

import com.adobo.cookme.entity.Meal;

import java.util.List;

public class MealRes {
    private List<Meal> meals;

    public MealRes() {
    }

    public List<Meal> getMeals() {
        return this.meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}

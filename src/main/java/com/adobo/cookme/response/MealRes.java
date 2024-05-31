package com.adobo.cookme.response;

import com.adobo.cookme.entity.Meal;

import java.util.List;

public class MealRes implements IMealDbRes<Meal> {
    private List<Meal> meals;

    public MealRes() {
    }

    @Override
    public List<Meal> getMeals() {
        return this.meals;
    }

    @Override
    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}

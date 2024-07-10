package com.adobo.cookme.response;

import java.util.List;

public class MealDbRes<T>{
    private List<T> meals;

    public MealDbRes() {
    }

    public List<T> getMeals() {
        return meals;
    }

    public void setMeals(List<T> meals) {
        this.meals = meals;
    }
}

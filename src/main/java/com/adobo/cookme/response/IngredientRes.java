package com.adobo.cookme.response;

import com.adobo.cookme.entity.IngredientList;

import java.util.List;

public class IngredientRes {
    private List<IngredientList> meals;

    public List<IngredientList> getMeals() {
        return this.meals;
    }

    public void setMeals(List<IngredientList> meals) {
        this.meals = meals;
    }
}

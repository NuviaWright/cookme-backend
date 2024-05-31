package com.adobo.cookme.response;

import com.adobo.cookme.entity.IngredientList;

import java.util.List;

public class IngredientRes implements IMealDbRes<IngredientList> {
    private List<IngredientList> meals;

    @Override
    public List<IngredientList> getMeals() {
        return this.meals;
    }

    @Override
    public void setMeals(List<IngredientList> meals) {
        this.meals = meals;
    }
}

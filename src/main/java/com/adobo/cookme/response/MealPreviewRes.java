package com.adobo.cookme.response;

import com.adobo.cookme.entity.MealPreview;

import java.util.List;

public class MealPreviewRes implements IMealDbRes<MealPreview> {
    private List<MealPreview> meals;

    public MealPreviewRes() {
    }

    @Override
    public List<MealPreview> getMeals() {
        return this.meals;
    }

    @Override
    public void setMeals(List<MealPreview> meals) {
        this.meals = meals;
    }
}

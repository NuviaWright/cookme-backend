package com.adobo.cookme.response;

import com.adobo.cookme.entity.MealPreview;

import java.util.List;

public class MealPreviewRes {
    private List<MealPreview> meals;

    public MealPreviewRes() {
    }

    public List<MealPreview> getMeals() {
        return this.meals;
    }

    public void setMeals(List<MealPreview> meals) {
        this.meals = meals;
    }
}

package com.adobo.cookme.response;

import com.adobo.cookme.entity.MealPreview;

import java.util.List;

public class MealPreviewRes extends AResponse<MealPreview> {

    private int size;
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

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }
}

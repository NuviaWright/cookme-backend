package com.adobo.cookme.response;

import com.adobo.cookme.entity.MealPreview;

import java.util.List;

public class MealDbPaginatedRes implements PaginatedResponse {
    private List<MealPreview> meals;

    public MealDbPaginatedRes() {
    }

    public List<MealPreview> getMeals() {
        return meals;
    }

    public void setMeals(List<MealPreview> meals) {
        this.meals = meals;
    }

    @Override
    public int getSize() {
        return meals.size();
    }

    @Override
    public List<?> getPaginatedList(int start, int end) {
        return this.meals.subList(start, end);
    }
}

package com.adobo.cookme.response;

import com.adobo.cookme.api.MealDb;
import com.adobo.cookme.entity.MealPreview;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MealDbPaginatedRes implements PaginatedResponse {
    private List<MealPreview> meals;

    @Autowired
    private MealDb mealDb;

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

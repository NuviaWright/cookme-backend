package com.adobo.cookme.response;

import com.adobo.cookme.entity.MealPreview;

import java.util.List;

public interface IRecipeRes<T> {
    List<T> getMeals();
    void setMeals(List<T> meals);
}

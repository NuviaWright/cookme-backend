package com.adobo.cookme.service;

import com.adobo.cookme.response.Response;

public interface RecipeService {

    Response getRecipesByIngredients(String ingredients);

    Response getMealById(Long id);

    Response getRecipesByIngredients(String ingredients, int page, int size);
}
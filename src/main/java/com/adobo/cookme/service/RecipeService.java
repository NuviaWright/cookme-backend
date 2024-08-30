package com.adobo.cookme.service;

import com.adobo.cookme.response.Response;

import java.util.List;

public interface RecipeService {

    Response getRecipesByIngredients(String ingredients);

    Response getMealById(Long id);

    Response getRecipesByIngredients(String ingredients, int page, int size);
}
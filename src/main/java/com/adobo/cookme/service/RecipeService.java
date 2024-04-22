package com.adobo.cookme.service;

import com.adobo.cookme.response.Response;

public interface RecipeService {

    public Response getRecipesByIngredients(String ingredients);
}
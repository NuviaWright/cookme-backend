package com.adobo.cookme.service;

import com.adobo.cookme.api.MealDb;
import com.adobo.cookme.entity.Meal;
import com.adobo.cookme.entity.MealPreview;
import com.adobo.cookme.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService{
    Logger logger = LoggerFactory.getLogger(RecipeServiceImpl.class);

    @Autowired
    private MealDb mealDb;

    @Autowired
    private Response res;

    @Override
    public Response getRecipesByIngredients(String ingredients) {
        logger.trace("Line {}: [RecipeServiceImpl][getRecipesByIngredients]", Thread.currentThread().getStackTrace()[1].getLineNumber());

        MealDbRes<MealPreview> mealRes = new MealDbRes<>();
        return mealDb.fetchRecipes(ingredients, mealRes.getClass());
    }

    @Override
    public Response getMealById(Long id) {
        logger.trace("Line {}: [RecipeServiceImpl][getMealById]", Thread.currentThread().getStackTrace()[1].getLineNumber());

        MealDbRes<Meal> mealRes = new MealDbRes<>();
        return mealDb.fetchMeal(id, mealRes.getClass());
    }

    @Override
    public Response getRecipesByIngredients(String ingredients, int page, int size) {
        logger.trace("Line {}: [RecipeServiceImpl][getRecipesByIngredients]", Thread.currentThread().getStackTrace()[1].getLineNumber());


        return mealDb.fetchRecipes(ingredients, MealDbPaginatedRes.class, page, size);
    }
}

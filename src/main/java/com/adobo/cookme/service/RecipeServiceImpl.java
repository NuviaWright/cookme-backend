package com.adobo.cookme.service;

import com.adobo.cookme.config.MealDb;
import com.adobo.cookme.response.MealPreviewRes;
import com.adobo.cookme.response.MealRes;
import com.adobo.cookme.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService{
    Logger logger = LoggerFactory.getLogger(RecipeServiceImpl.class);

    @Override
    public Response getRecipesByIngredients(String ingredients) {
        Response res = new Response();
        MealPreviewRes mealPreviewRes = new MealPreviewRes();
        MealDb mealDb = new MealDb();

        mealDb.fetchRecipes(ingredients, res, mealPreviewRes.getClass());
        return res;
    }

    @Override
    public Response getMealById(Long id) {
        Response res = new Response();
        MealRes mealRes = new MealRes();
        MealDb mealDb = new MealDb();

        mealDb.fetchMeal(id, res, mealRes.getClass());
        return res;
    }
}

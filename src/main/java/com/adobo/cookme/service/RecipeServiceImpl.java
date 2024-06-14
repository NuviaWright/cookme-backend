package com.adobo.cookme.service;

import com.adobo.cookme.config.MealDb;
import com.adobo.cookme.entity.Meal;
import com.adobo.cookme.entity.MealPreview;
import com.adobo.cookme.response.IMealDbRes;
import com.adobo.cookme.response.MealPreviewRes;
import com.adobo.cookme.response.MealRes;
import com.adobo.cookme.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService{
    Logger logger = LoggerFactory.getLogger(RecipeServiceImpl.class);

    @Override
    public Response getRecipesByIngredients(String ingredients) {
        logger.trace("Starting getting recipes...");

        Response res = new Response();
        IMealDbRes<MealPreview> mealPreviewRes = new MealPreviewRes();
        MealDb mealDb = new MealDb();

        mealDb.fetchRecipes(ingredients, res, mealPreviewRes.getClass());
        return res;
    }

    @Override
    public Response getMealById(Long id) {
        logger.trace("Getting meal...");

        Response res = new Response();
        IMealDbRes<Meal> mealRes = new MealRes();
        MealDb mealDb = new MealDb();

        mealDb.fetchMeal(id, res, mealRes.getClass());
        return res;
    }
}

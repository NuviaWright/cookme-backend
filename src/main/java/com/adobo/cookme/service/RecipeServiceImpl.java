package com.adobo.cookme.service;

import com.adobo.cookme.api.MealDb;
import com.adobo.cookme.entity.Meal;
import com.adobo.cookme.entity.MealPreview;
import com.adobo.cookme.exception.MealDbException;
import com.adobo.cookme.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

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

        String ing = ingredients
                .strip()
                .replace(" ", "_");
        try {
            List<MealPreview> mealPreviews = mealDb.fetchRecipes(ing);

            MealDbRes<MealPreview> mealDbRes = new MealDbRes<>();
            mealDbRes.setMeals(mealPreviews);

            res.setResponse(mealDbRes);
            res.setCode("OK");
        } catch (Exception e) {
            res.setCode("NG");
            res.setMessage(e.getMessage());
        }

        return res;
    }

    @Override
    public Response getMealById(Long id) {
        logger.trace("Line {}: [RecipeServiceImpl][getMealById]", Thread.currentThread().getStackTrace()[1].getLineNumber());

        try {
            List<Meal> meals = mealDb.fetchMeal(id);

            MealDbRes<Meal> mealDbRes = new MealDbRes<>();
            mealDbRes.setMeals(meals);

            res.setResponse(mealDbRes);
            res.setCode("OK");
        } catch (Exception e) {
            res.setCode("NG");
            res.setMessage(e.getMessage());
        }

        return res;
    }

    @Override
    public Response getRecipesByIngredients(String ingredients, int page, int size) {
        logger.trace("Line {}: [RecipeServiceImpl][getRecipesByIngredients]", Thread.currentThread().getStackTrace()[1].getLineNumber());

        String ing = ingredients
                .strip()
                .replace(" ", "_");
        try {
            PageImpl<?> mealDbPaginatedRes = mealDb.fetchRecipes(ing, MealDbPaginatedRes.class, page, size);

            res.setResponse(mealDbPaginatedRes);
            res.setCode("OK");
        } catch (Exception e) {
            res.setCode("NG");
            res.setMessage(e.getMessage());
        }

        return res;
    }
}

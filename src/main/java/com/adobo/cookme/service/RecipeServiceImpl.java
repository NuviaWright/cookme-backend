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

    private final MealDb mealDb = new MealDb();

    @Autowired
    private Environment env;


    @Override
    public Response getRecipesByIngredients(String ingredients) {
        logger.trace("Starting getting recipes...");

        StringBuffer url = new StringBuffer(env.getProperty("meal.db.url") + env.getProperty("meal.db.apikey"));
        url.append("/filter.php?i=").append(ingredients);

        Response res = new Response();
        IMealDbRes<MealPreview> response = new MealPreviewRes();

        mealDb.fetchData(url.toString(), res, response.getClass());
        return res;
    }

    @Override
    public Response getMealById(Long id) {
        logger.trace("Getting meal...");

        StringBuffer url = new StringBuffer(env.getProperty("meal.db.url") + env.getProperty("meal.db.apikey"));
        url.append("/lookup.php?i=");
        url.append(id.toString());

        Response res = new Response();
        IMealDbRes<Meal> response = new MealRes();
        mealDb.fetchData(url.toString(), res, response.getClass());
        return res;
    }
}

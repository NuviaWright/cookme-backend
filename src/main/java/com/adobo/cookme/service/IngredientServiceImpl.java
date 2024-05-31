package com.adobo.cookme.service;

import com.adobo.cookme.config.MealDb;
import com.adobo.cookme.entity.IngredientList;
import com.adobo.cookme.response.IMealDbRes;
import com.adobo.cookme.response.IngredientRes;
import com.adobo.cookme.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService{
    Logger logger = LoggerFactory.getLogger(IngredientServiceImpl.class);

    private final MealDb mealDb = new MealDb();

    @Autowired
    private Environment env;

    @Override
    public Response getAvailableIngredients() {
        logger.trace("Getting all ingredients...");

        StringBuffer url = new StringBuffer(env.getProperty("meal.db.url") + env.getProperty("meal.db.apikey"));
        url.append("/list.php?i=list");

        Response res = new Response();
        IMealDbRes<IngredientList> response = new IngredientRes();
        mealDb.fetchData(url.toString(), res, response.getClass());

        return res;
    }
}

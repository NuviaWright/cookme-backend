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

    @Autowired
    private Environment env;

    @Override
    public Response getAvailableIngredients() {
        logger.trace("Getting all ingredients...");

        Response res = new Response();
        IMealDbRes<IngredientList> ingredientRes = new IngredientRes();
        MealDb mealDb = new MealDb();

        mealDb.fetchIngredients(res, ingredientRes.getClass());

        return res;
    }
}

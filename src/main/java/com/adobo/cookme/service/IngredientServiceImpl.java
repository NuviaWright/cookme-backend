package com.adobo.cookme.service;

import com.adobo.cookme.api.MealDb;
import com.adobo.cookme.response.IngredientRes;
import com.adobo.cookme.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService{
    Logger logger = LoggerFactory.getLogger(IngredientServiceImpl.class);

    @Autowired
    private Environment env;

    @Override
    public Response getAvailableIngredients() {
        Response res = new Response();
        IngredientRes ingredientRes = new IngredientRes();
        MealDb mealDb = new MealDb();

        mealDb.fetchIngredients(res, ingredientRes.getClass());

        return res;
    }
}

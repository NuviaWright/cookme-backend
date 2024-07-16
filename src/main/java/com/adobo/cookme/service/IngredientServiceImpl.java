package com.adobo.cookme.service;

import com.adobo.cookme.api.MealDb;
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

    @Autowired
    private MealDb mealDb;

    @Override
    public Response getAvailableIngredients() {
        return mealDb.fetchIngredients();
    }
}

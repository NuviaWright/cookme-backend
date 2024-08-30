package com.adobo.cookme.service;

import com.adobo.cookme.api.MealDb;
import com.adobo.cookme.entity.IngredientList;
import com.adobo.cookme.entity.Meal;
import com.adobo.cookme.exception.MealDbException;
import com.adobo.cookme.response.MealDbRes;
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

    @Autowired
    private MealDb mealDb;

    @Autowired
    private Response res;

    @Override
    public Response getAvailableIngredients() {
        try{
            MealDbRes<IngredientList> ingredientLists = mealDb.fetchIngredients();

            res.setResponse(ingredientLists);
            res.setCode("OK");
        }catch (Exception e) {
            res.setCode("NG");
            res.setMessage(e.getMessage());
        }

        return res;
    }
}

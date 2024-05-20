package com.adobo.cookme.service;

import com.adobo.cookme.entity.Meal;
import com.adobo.cookme.entity.MealPreview;
import com.adobo.cookme.response.IRecipeRes;
import com.adobo.cookme.response.MealPreviewRes;
import com.adobo.cookme.response.MealRes;
import com.adobo.cookme.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class RecipeServiceImpl implements RecipeService{
    Logger logger = LoggerFactory.getLogger(RecipeServiceImpl.class);
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private Environment env;


    @Override
    public Response getRecipesByIngredients(String ingredients) {
        logger.trace("Starting getting recipes...");

        StringBuffer url = new StringBuffer(env.getProperty("meal.db.url") + env.getProperty("meal.db.apikey"));
        url.append("/filter.php?i=");
        url.append(ingredients);

        Response res = new Response();
        IRecipeRes<MealPreview> response = new MealPreviewRes();
        fetchData(url.toString(), res, response.getClass());
        return res;
    }

    @Override
    public Response getMealById(Long id) {
        logger.trace("Getting meal...");

        StringBuffer url = new StringBuffer(env.getProperty("meal.db.url") + env.getProperty("meal.db.apikey"));
        url.append("/lookup.php?i=");
        url.append(id.toString());

        Response res = new Response();
        IRecipeRes<Meal> response = new MealRes();
        fetchData(url.toString(), res, response.getClass());
        return res;
    }

    private <T> void fetchData(String url, Response res, Class<T> mealRes) {
        logger.info("URL: " + url);

        try {
            ResponseEntity<T> response = restTemplate.getForEntity(url, mealRes);
            res.setCode("OK");
            res.setResponse(response.getBody());

            logger.trace("STATUS:" + response.getStatusCode());
        } catch (RestClientException e) {
            res.setCode("NG");
            res.setMessage("Oops! Something went wrong. Please try again later.");

            logger.error("ERROR: " + e.getMessage());
        } catch (Exception e) {
            res.setCode("NG");
            res.setMessage("Internal Error");

            logger.error("ERROR: " + e.getMessage());
        }
    }
}

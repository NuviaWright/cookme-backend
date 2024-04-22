package com.adobo.cookme.service;

import com.adobo.cookme.config.MealDb;
import com.adobo.cookme.response.RecipeRes;
import com.adobo.cookme.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class RecipeServiceImpl implements RecipeService{
    Logger logger = LoggerFactory.getLogger(RecipeServiceImpl.class);
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private MealDb mealDb;

    @Override
    public Response getRecipesByIngredients(String ingredients) {
        logger.info("Starting getting recipes...");
        Response res = new Response();
        StringBuffer fullUrl = new StringBuffer();
        fullUrl.append(mealDb.getUrl());
        fullUrl.append(mealDb.getApiKey());
        fullUrl.append("/filter.php?i=");
        fullUrl.append(ingredients);

        ResponseEntity<RecipeRes> response;
        try {
            response = restTemplate.getForEntity(fullUrl.toString(), RecipeRes.class);
            res.setCode("OK");
            res.setResponse(response.getBody());

            logger.trace("STATUS:" + response.getStatusCode());
        } catch (RestClientException e) {
            res.setCode("NG");
            res.setMessage("Oops! Something went wrong. Please try again later.");

            logger.error("ERROR: " + e.getMessage());
        }

        return res;
    }
}

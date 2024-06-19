package com.adobo.cookme.config;

import com.adobo.cookme.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class MealDb {
    Logger logger = LoggerFactory.getLogger(MealDb.class);
    private final String url = "https://www.themealdb.com/api/json/v1/";
    private final String apiKey = "1";
    private final StringBuffer ingredientsUrl = new StringBuffer(this.url)
            .append(this.apiKey)
            .append("/list.php?i=list");
    private final StringBuffer recipesUrl = new StringBuffer(this.url)
            .append(this.apiKey)
            .append("/filter.php?i=");
    private final StringBuffer mealUrl = new StringBuffer(this.url)
            .append(this.apiKey)
            .append("/lookup.php?i=");

    public MealDb() {
    }

    private <T> void fetchData(String url, Response res, Class<T> mealRes) {
        Logger logger = LoggerFactory.getLogger(MealDb.class);
        RestTemplate restTemplate = new RestTemplate();
        logger.info("URL: " + url);

        try {
            ResponseEntity<T> response = restTemplate.getForEntity(url, mealRes);
            res.setCode("OK");
            res.setResponse(response.getBody());

            logger.info("STATUS:" + response.getStatusCode());
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

    public <T> void fetchIngredients(Response res, Class<T> mealRes) {
        this.fetchData(this.ingredientsUrl.toString(), res, mealRes);
    }

    public <T> void fetchRecipes(String ingredients, Response res, Class<T> mealRes) {
        StringBuffer newRecipeUrl = this.recipesUrl;
        newRecipeUrl.append(ingredients);

        this.fetchData(newRecipeUrl.toString(), res, mealRes);
    }

    public <T> void fetchMeal(String id, Response res, Class<T> mealRes) {
        StringBuffer newMealUrl = this.mealUrl;
        newMealUrl.append(id);

        this.fetchData(newMealUrl.toString(), res, mealRes);
    }

    public <T> void fetchMeal(Long id, Response res, Class<T> mealRes) {
        StringBuffer newMealUrl = this.mealUrl;
        newMealUrl.append(id.toString());

        this.fetchData(newMealUrl.toString(), res, mealRes);
    }
}

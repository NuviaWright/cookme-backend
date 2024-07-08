package com.adobo.cookme.api;

import com.adobo.cookme.entity.MealPreview;
import com.adobo.cookme.response.AResponse;
import com.adobo.cookme.response.MealPreviewRes;
import com.adobo.cookme.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class MealDb {
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

    private <T> void fetchData(String url, Response res, Class<? extends AResponse<T>> mealRes, int page, int size) {
        Logger logger = LoggerFactory.getLogger(MealDb.class);
        RestTemplate restTemplate = new RestTemplate();
        logger.info("URL: " + url + "?page=" + page + "&size=" + size);

        try {
            Pageable pageRequest = PageRequest.of(page, size);
            ResponseEntity<? extends AResponse<T>> response = restTemplate.getForEntity(url, mealRes);
            AResponse<T> obj = response.getBody();

            if(obj == null) {
                res.setCode("OK");
                res.setResponse(new MealPreviewRes());
                return;
            }

            int listSize = obj.getMeals().size();
            int start = (int) pageRequest.getOffset();
            int end = Math.min((start + pageRequest.getPageSize()), listSize);

            obj.setMeals(obj.getMeals().subList(start, end));

            res.setCode("OK");
            res.setResponse(new PageImpl<>(obj.getMeals(), pageRequest, listSize));
            logger.info("STATUS:" + response.getStatusCode());
        } catch (RestClientException e) {
            res.setCode("NG");
            res.setMessage("Oops! Something went wrong. Please try again later.");
            logger.error("ERROR: " + e);
        } catch (NullPointerException e){
            res.setCode("OK");
            res.setResponse(new MealPreviewRes());
            logger.error("ERROR: " + e);
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            res.setCode("NG");
            res.setMessage("Page is out of bounds");
            logger.error("ERROR: " + e);
        } catch (Exception e) {
            res.setCode("NG");
            res.setMessage("Internal Error");
            logger.error("ERROR: " + e);
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

    public <T> void fetchRecipes(String ingredients, Response res, Class<? extends AResponse<T>> mealRes, int page, int size) {
        StringBuffer newRecipeUrl = this.recipesUrl;
        newRecipeUrl.append(ingredients);

        this.fetchData(newRecipeUrl.toString(), res, mealRes, page, size);
    }
}

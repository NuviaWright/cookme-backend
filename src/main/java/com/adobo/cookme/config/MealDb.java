package com.adobo.cookme.config;

import com.adobo.cookme.response.Response;
import com.adobo.cookme.service.IngredientServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class MealDb {
    Logger logger = LoggerFactory.getLogger(MealDb.class);
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${meal.db.url}")
    private String url;

    @Value("${meal.db.apiKey}")
    private String apiKey;

    public MealDb() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public <T> void fetchData(String url, Response res, Class<T> mealRes) {
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

package com.adobo.cookme.api;

import com.adobo.cookme.entity.IngredientList;
import com.adobo.cookme.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component("mealDb")
@Slf4j
public class MealDb {

    @Value("${mealdb.url}")
    private String url;

    @Value("${mealdb.apikey}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Response response;

    public MealDb() {
    }

    private <T> void fetchData(String url, Response res, Class<T> mealRes) {
        log.info("URL: {}", url);

        try {
            ResponseEntity<T> response = restTemplate.getForEntity(url, mealRes);
            res.setCode("OK");
            res.setResponse(response.getBody());

            log.info("STATUS:" + response.getStatusCode());
        } catch (RestClientException e) {
            res.setCode("NG");
            res.setMessage("Oops! Something went wrong. Please try again later.");

            log.error("ERROR: " + e.getMessage());
        } catch (Exception e) {
            res.setCode("NG");
            res.setMessage("Internal Error");

            log.error("ERROR: " + e.getMessage());
        }
    }

    private Response fetchData(UriComponents url, Class<? extends PaginatedResponse> mealRes, int page, int size) {
        log.info("URL: " + url + "?page=" + page + "&size=" + size);

        try {
            Pageable pageRequest = PageRequest.of(page, size);
            ResponseEntity<? extends PaginatedResponse> responseEntity = restTemplate.getForEntity(url.toString(), mealRes);
            PaginatedResponse obj = responseEntity.getBody();

            if(obj == null) {
                response.setCode("OK");
                response.setResponse(null);
                return response;
            }

            int listSize = obj.getSize();
            int start = (int) pageRequest.getOffset();
            int end = Math.min((start + pageRequest.getPageSize()), listSize);

            response.setCode("OK");
            response.setResponse(new PageImpl<>(obj.getPaginatedList(start, end), pageRequest, listSize));
            log.info("STATUS:" + responseEntity.getStatusCode());
        } catch (RestClientException e) {
            response.setCode("NG");
            response.setMessage("Oops! Something went wrong. Please try again later.");
            log.error("ERROR: " + e);
        } catch (NullPointerException e){
            response.setCode("OK");
            response.setResponse(null);
            log.error("ERROR: " + e);
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            response.setCode("NG");
            response.setMessage("Page is out of bounds");
            log.error("ERROR: " + e);
        } catch (Exception e) {
            response.setCode("NG");
            response.setMessage("Internal Error");
            log.error("ERROR: " + e);
        }

        return response;
    }

    private <T> Response fetchData(UriComponents url, Class<T> mealRes) {
        log.info("URL: {}", url);

        try {
            ResponseEntity<T> responseEntity = restTemplate.getForEntity(url.toString(), mealRes);
            response.setCode("OK");
            response.setResponse(responseEntity.getBody());

            log.info("STATUS:" + responseEntity.getStatusCode());
        } catch (RestClientException e) {
            response.setCode("NG");
            response.setMessage("Oops! Something went wrong. Please try again later.");

            log.error("ERROR: " + e.getMessage());
        } catch (Exception e) {
            response.setCode("NG");
            response.setMessage("Internal Error");

            log.error("ERROR: " + e.getMessage());
        }

        return response;
    }

    public <T> void fetchIngredients(Response res, Class<T> mealRes) {
        UriComponents ingredientsUrl = UriComponentsBuilder.fromHttpUrl(this.url)
                .path(this.apiKey)
                .path("/list.php")
                .queryParam("i", "list")
                .build();

        this.fetchData(ingredientsUrl.toString(), res, mealRes);
    }

    public Response fetchIngredients() {
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(this.url)
                .path(this.apiKey)
                .path("/list.php")
                .queryParam("i","list")
                .build();

        MealDbRes<IngredientList> mealRes = new MealDbRes<>();
        return this.fetchData(uri, mealRes.getClass());
    }

    public <T> Response fetchRecipes(String ingredients, Class<T> mealRes) {
        UriComponents recipesUrl = UriComponentsBuilder.fromHttpUrl(this.url)
                .path(this.apiKey)
                .path("/filter.php")
                .queryParam("i", ingredients)
                .build();

        return this.fetchData(recipesUrl, mealRes);
    }

    public <T> void fetchMeal(String id, Response res, Class<T> mealRes) {
        StringBuffer mealUrl = new StringBuffer(this.url)
                .append(this.apiKey)
                .append("/lookup.php?i=")
                .append(id);

        this.fetchData(mealUrl.toString(), res, mealRes);
    }

    public <T> void fetchMeal(Long id, Response res, Class<T> mealRes) {
        StringBuffer mealUrl = new StringBuffer(this.url)
                .append(this.apiKey)
                .append("/lookup.php?i=").append(id.toString());

        this.fetchData(mealUrl.toString(), res, mealRes);
    }

    public <T> Response fetchMeal(Long id, Class<T> mealRes) {
        UriComponents mealUrl = UriComponentsBuilder.fromHttpUrl(this.url)
                .path(this.apiKey)
                .path("/lookup.php")
                .queryParam("i", id.toString())
                .build();

        return this.fetchData(mealUrl, mealRes);
    }

    public Response fetchRecipes(String ingredients, Class<? extends PaginatedResponse> mealRes, int page, int size) {
        UriComponents recipesUrl = UriComponentsBuilder.fromHttpUrl(this.url)
                .path(this.apiKey)
                .path("/filter.php")
                .queryParam("i", ingredients)
                .build();

        return this.fetchData(recipesUrl, mealRes, page, size);
    }
}

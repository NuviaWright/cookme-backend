package com.adobo.cookme.api;

import com.adobo.cookme.exception.MealDbException;
import com.adobo.cookme.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
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

    public MealDb() {
    }

    private PageImpl<?> fetchData(UriComponents url, Class<? extends PaginatedResponse> mealRes, int page, int size) throws MealDbException {
        log.info("URL: {}?page={}&size={}", url, page, size);

        try {
            Pageable pageRequest = PageRequest.of(page, size);
            ResponseEntity<? extends PaginatedResponse> responseEntity = restTemplate.getForEntity(url.toString(), mealRes);
            PaginatedResponse obj = responseEntity.getBody();

            int listSize = obj.getSize();
            int start = (int) pageRequest.getOffset();
            int end = Math.min((start + pageRequest.getPageSize()), listSize);

            log.info("STATUS: {}", responseEntity.getStatusCode());
            return new PageImpl<>(obj.getPaginatedList(start, end), pageRequest, listSize);
        } catch (RestClientException e) {
            log.error("ERROR: " + e);
            throw new RestClientException("Oops! Something went wrong. Please try again later.");
        } catch (NullPointerException e){
            log.error("ERROR: " + e);
            return null;
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            log.error("ERROR: " + e);
            throw new MealDbException("Page is out of bounds");
        } catch (Exception e) {
            log.error("ERROR: " + e);
            throw new MealDbException("Internal Error");
        }
    }

    private <T> T fetchData(UriComponents url, Class<T> mealRes) throws MealDbException {
        log.info("URL: {}", url);

        try {
            ResponseEntity<T> responseEntity = restTemplate.getForEntity(url.toString(), mealRes);
            log.info("STATUS:" + responseEntity.getStatusCode());
            return responseEntity.getBody();
        } catch (RestClientException e) {
            log.error("ERROR: " + e);
            throw new RestClientException("Oops! Something went wrong. Please try again later.");
        } catch (Exception e) {
            log.error("ERROR: " + e);
            throw new MealDbException("Internal Error");
        }
    }

    private <T> MealDbRes<T> fetchData(UriComponents uri) throws MealDbException {
        log.info("URL: {}", uri);
        try {
            ResponseEntity<MealDbRes<T>> responseEntity = restTemplate.exchange(uri.toString(), HttpMethod.GET, null, new ParameterizedTypeReference<MealDbRes<T>>() {
            });

            log.info("STATUS:" + responseEntity.getStatusCode());
            return responseEntity.getBody();
        } catch (RestClientException e) {
            log.error("ERROR: " + e);
            throw new RestClientException("Oops! Something went wrong. Please try again later.");
        } catch (Exception e) {
            log.error("ERROR: " + e);
            throw new MealDbException("Internal Error");
        }
    }

    public <T> MealDbRes<T> fetchIngredients() throws MealDbException {
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(this.url)
                .path(this.apiKey)
                .path("/list.php")
                .queryParam("i","list")
                .build();

        return this.fetchData(uri);
    }

    public <T> MealDbRes<T> fetchRecipes(String ingredients) throws MealDbException {
        UriComponents recipesUrl = UriComponentsBuilder.fromHttpUrl(this.url)
                .path(this.apiKey)
                .path("/filter.php")
                .queryParam("i", ingredients)
                .build();

        return this.fetchData(recipesUrl);
    }

    public <T> MealDbRes<T> fetchMeal(Long id) throws MealDbException {
        UriComponents mealUrl = UriComponentsBuilder.fromHttpUrl(this.url)
                .path(this.apiKey)
                .path("/lookup.php")
                .queryParam("i", id.toString())
                .build();

        return this.fetchData(mealUrl);
    }

    public PageImpl<?> fetchRecipes(String ingredients, Class<? extends PaginatedResponse> mealRes, int page, int size) throws MealDbException {
        UriComponents recipesUrl = UriComponentsBuilder.fromHttpUrl(this.url)
                .path(this.apiKey)
                .path("/filter.php")
                .queryParam("i", ingredients)
                .build();

        return this.fetchData(recipesUrl, mealRes, page, size);
    }
}

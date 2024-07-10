package com.adobo.cookme.config;

import com.adobo.cookme.service.IngredientService;
import com.adobo.cookme.service.IngredientServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class IngredientServiceTestContextConfig {

    @Bean
    public IngredientService ingredientService() {
        return new IngredientServiceImpl();
    }
}

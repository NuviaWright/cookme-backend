package com.adobo.cookme.service;

import com.adobo.cookme.api.MealDb;
import com.adobo.cookme.config.IngredientServiceTestContextConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(IngredientServiceTestContextConfig.class)
public class IngredientServiceIntegrationTest {

    @Autowired
    private IngredientService ingredientService;

    @MockBean
    private MealDb mealDb;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void gettingAllIngredients_success() {

    }
}

package com.adobo.cookme.controller;

import com.adobo.cookme.response.Response;
import com.adobo.cookme.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "recipe/")
public class RecipeFinderController {
    Logger logger = LoggerFactory.getLogger(RecipeFinderController.class);

    @Autowired
    private RecipeService recipeService;

    @GetMapping("find/{ingredient}")
    public Response getRecipes(@PathVariable("ingredient") String ingredients) {
        logger.info("recipe/find/" + ingredients);
        return recipeService.getRecipesByIngredients(ingredients);
    }

    @GetMapping("meal/{mealId}")
    public Response getMeal(@PathVariable("mealId") Long id) {
        logger.info("meal/" + id);
        return recipeService.getMealById(id);
    }
}

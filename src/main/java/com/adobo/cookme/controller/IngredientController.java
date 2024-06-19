package com.adobo.cookme.controller;

import com.adobo.cookme.response.Response;
import com.adobo.cookme.service.IngredientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "ingredient/")
public class IngredientController {
    Logger logger = LoggerFactory.getLogger(IngredientController.class);

    @Autowired
    private IngredientService ingredientService;

    @GetMapping("/list")
    public Response getIngredients() {
        logger.trace("--------------------------------------------------------");
        logger.trace("[IngredientController][getIngredients]");
        return ingredientService.getAvailableIngredients();
    }
}

package com.adobo.cookme.controller;

import com.adobo.cookme.response.Response;
import com.adobo.cookme.service.IngredientService;
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

    @Autowired
    private IngredientService ingredientService;

    @GetMapping("/list")
    public Response getIngredients() {
        return ingredientService.getAvailableIngredients();
    }
}

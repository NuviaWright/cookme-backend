package com.adobo.cookme.request;

import com.adobo.cookme.entity.Ingredient;

import java.util.List;

public class IngredientReq {
    private List<Ingredient> ingredients;

    public IngredientReq() {
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}

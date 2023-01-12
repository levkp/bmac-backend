package com.bmac.factory.domain;

import java.util.Map;
import java.util.UUID;

public class Recipe extends FactoryEntity {

    private Product product;
    private Map<Ingredient, Double> ingredients;

    public Recipe(UUID id, Product product, Map<Ingredient, Double> ingredients) {
        super(id);
        this.product = product;
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient, double quantity) {
        ingredients.put(ingredient, quantity);
    }

    public Product getProduct() {
        return product;
    }

    public Map<Ingredient, Double> getIngredients() {
        return ingredients;
    }
}

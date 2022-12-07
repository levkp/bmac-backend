package com.bmac.common.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Recipe {
    UUID productId;
    Map<Ingredient, Double> ingredients = new HashMap<>();
    String instructions;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return productId.equals(recipe.productId) && ingredients.equals(recipe.ingredients) && instructions.equals(recipe.instructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, ingredients, instructions);
    }
}

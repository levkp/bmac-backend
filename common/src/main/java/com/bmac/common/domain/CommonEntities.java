package com.bmac.common.domain;

import com.bmac.common.IngredientTemperature;
import com.bmac.common.IngredientUnit;

import java.util.Map;
import java.util.UUID;

public class CommonEntities {
    public abstract static class CommonEntity {
        public final UUID id;

        public CommonEntity(UUID id) {
            this.id = id;
        }
    }

    public static class Product extends CommonEntity {
        public final String name;

        public Product(UUID id, String name) {
            super(id);
            this.name = name;
        }
    }

    public static class Ingredient extends CommonEntity {
        public final String name;
        public final IngredientUnit unit;
        public final IngredientTemperature temperature;

        public Ingredient(UUID id, String name, IngredientUnit unit, IngredientTemperature temperature) {
            super(id);
            this.name = name;
            this.unit = unit;
            this.temperature = temperature;
        }
    }

    public static class Recipe extends CommonEntity {
        public final Product product;
        public final Map<Ingredient, Double> ingredients;

        public Recipe(UUID id, Product product, Map<Ingredient, Double> ingredients) {
            super(id);
            this.product = product;
            this.ingredients = ingredients;
        }
    }
}

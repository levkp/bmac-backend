package com.bmac.factory.bootstrap;

import com.bmac.common.IngredientTemperature;
import com.bmac.common.IngredientUnit;
import com.bmac.common.exception.EntityConstraintException;
import com.bmac.factory.domain.Ingredient;
import com.bmac.factory.domain.Product;
import com.bmac.factory.ports.in.*;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
@Order(1)
@Profile("seed")
public class FactorySeeder implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final CreateIngredientUseCase createIngredient;
    private final CreateProductUseCase createProduct;
    private final CreateRecipeUseCase createRecipe;
    private final LoadAllFactoryEntityQuery<Ingredient> ingredientLoader;
    private final LoadAllFactoryEntityQuery<Product> productLoader;

    @Autowired
    public FactorySeeder(CreateIngredientUseCase createIngredient,
                         CreateProductUseCase createProduct,
                         CreateRecipeUseCase createRecipe,
                         LoadAllFactoryEntityQuery<Ingredient> ingredientLoader,
                         LoadAllFactoryEntityQuery<Product> productLoader) {
        this.createIngredient = createIngredient;
        this.createProduct = createProduct;
        this.createRecipe = createRecipe;
        this.ingredientLoader = ingredientLoader;
        this.productLoader = productLoader;
    }

    @Override
    public void run(String... args) {
        fakeIngredients(30);
        fakeProducts(20);
        fakeRecipes();
    }

    public void fakeIngredients(int amount) {
        log.info("Faking " + amount + " ingredients");

        Faker faker = new Faker();
        Random random = new Random();

        for (int i = 0; i < amount; i++) {
            try {
                createIngredient.create(
                        new CreateIngredientCommand(
                                faker.food().ingredient(),
                                IngredientUnit.values()[random.nextInt(IngredientUnit.values().length)],
                                IngredientTemperature.values()[random.nextInt(IngredientTemperature.values().length)])
                );
            } catch (EntityConstraintException exception) {
                // .
            }
        }
    }

    public void fakeProducts(int amount) {
        log.info("Faking " + amount + " products");

        Faker faker = new Faker();

        for (int i = 0; i < amount; i++) {
            try {
                createProduct.create(new CreateProductCommand(faker.food().dish()));
            } catch (EntityConstraintException exception) {
                // .
            }
        }
    }

    public void fakeRecipes() {
        log.info("Faking recipes");

        Random random = new Random();
        List<Product> products = productLoader.loadAll();
        List<Ingredient> ingredients = ingredientLoader.loadAll();

        for(Product product : products) {
            Map<Ingredient, Double> recipeIngredients = new HashMap<>();

            for(int i = 0; i < random.nextInt(1, 15); i++) {
                recipeIngredients.put(
                        ingredients.get(random.nextInt(ingredients.size())),
                        (double) random.nextInt(10));
            }

            try {
                createRecipe.create(new CreateRecipeCommand(product, recipeIngredients));
            } catch (EntityConstraintException exception) {
                // .
            }
        }
    }
}


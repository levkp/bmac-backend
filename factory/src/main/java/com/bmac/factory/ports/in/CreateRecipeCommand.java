package com.bmac.factory.ports.in;

import com.bmac.factory.domain.Ingredient;
import com.bmac.factory.domain.Product;

import java.util.Map;

public record CreateRecipeCommand(Product product, Map<Ingredient, Double> ingredients) { }

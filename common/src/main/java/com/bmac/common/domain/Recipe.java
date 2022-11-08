package com.bmac.common.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Recipe {
    Product product;
    Map<Ingredient, Double> ingredients = new HashMap<>();
    String instructions;

}

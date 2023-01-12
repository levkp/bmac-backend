package com.bmac.factory.ports.in;

import com.bmac.common.IngredientTemperature;
import com.bmac.common.IngredientUnit;

public record CreateIngredientCommand(String name, IngredientUnit unit, IngredientTemperature temperature) { }

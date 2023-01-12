package com.bmac.warehouse.ports.in.item;

import com.bmac.common.IngredientTemperature;
import com.bmac.common.IngredientUnit;

import java.util.UUID;

public record CreateItemCommand(UUID id, String name, double minimum, double maximum, IngredientTemperature temperature, IngredientUnit unit, int expiryDays) {

}

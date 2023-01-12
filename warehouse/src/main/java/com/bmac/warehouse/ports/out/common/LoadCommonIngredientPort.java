package com.bmac.warehouse.ports.out.common;

import com.bmac.common.domain.CommonEntities;

import java.util.List;

public interface LoadCommonIngredientPort {
    List<CommonEntities.Ingredient> loadAll();
}

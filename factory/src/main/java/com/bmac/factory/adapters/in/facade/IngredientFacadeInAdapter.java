package com.bmac.factory.adapters.in.facade;

import com.bmac.common.domain.CommonEntities;
import com.bmac.common.facade.CommonEntityFacade;
import com.bmac.factory.domain.Ingredient;
import com.bmac.factory.ports.out.LoadFactoryEntityPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IngredientFacadeInAdapter implements CommonEntityFacade<CommonEntities.Ingredient> {

    private final LoadFactoryEntityPort<Ingredient> ingredientLoader;

    @Autowired
    public IngredientFacadeInAdapter(LoadFactoryEntityPort<Ingredient> ingredientLoader) {
        this.ingredientLoader = ingredientLoader;
    }

    @Override
    public List<CommonEntities.Ingredient> loadAll() {
        return ingredientLoader.loadAll()
                .stream()
                .map(ingredient -> new CommonEntities.Ingredient(ingredient.getId(), ingredient.getName(), ingredient.getUnit(), ingredient.getTemperature()))
                .toList();
    }
}

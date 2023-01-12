package com.bmac.factory.adapters.in.facade;

import com.bmac.common.domain.CommonEntities;
import com.bmac.common.facade.CommonEntityFacade;
import com.bmac.factory.domain.Recipe;
import com.bmac.factory.ports.out.LoadFactoryEntityPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RecipeFacadeInAdapter implements CommonEntityFacade<CommonEntities.Recipe> {

    private final LoadFactoryEntityPort<Recipe> recipeLoader;

    @Autowired
    public RecipeFacadeInAdapter(LoadFactoryEntityPort<Recipe> recipeLoader) {
        this.recipeLoader = recipeLoader;
    }

    @Override
    public List<CommonEntities.Recipe> loadAll() {
        return recipeLoader.loadAll()
                .stream()
                .map(recipe -> new CommonEntities.Recipe(
                        recipe.getId(),
                        new CommonEntities.Product(recipe.getProduct().getId(), recipe.getProduct().getName()),
                        recipe.getIngredients().entrySet().stream().collect(Collectors.toMap(
                                entry -> {
                                    var ingredient = entry.getKey();
                                    return new CommonEntities.Ingredient(ingredient.getId(), ingredient.getName(), ingredient.getUnit(), ingredient.getTemperature());
                                },
                                Map.Entry::getValue
                        ))))
                .toList();
    }
}

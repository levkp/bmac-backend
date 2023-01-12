package com.bmac.factory.core;

import com.bmac.factory.domain.Recipe;
import com.bmac.factory.ports.in.CreateRecipeCommand;
import com.bmac.factory.ports.in.CreateRecipeUseCase;
import com.bmac.factory.ports.out.CreateFactoryEntityPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultCreateRecipeUseCase implements CreateRecipeUseCase {

    private final CreateFactoryEntityPort<Recipe> recipeCreator;

    @Autowired
    public DefaultCreateRecipeUseCase(CreateFactoryEntityPort<Recipe> recipeCreator) {
        this.recipeCreator = recipeCreator;
    }

    @Override
    public void create(CreateRecipeCommand command) {
        recipeCreator.create(new Recipe(UUID.randomUUID(), command.product(), command.ingredients()));
    }
}

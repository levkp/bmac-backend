package com.bmac.factory.core;

import com.bmac.factory.domain.Ingredient;
import com.bmac.factory.ports.in.CreateIngredientCommand;
import com.bmac.factory.ports.in.CreateIngredientUseCase;
import com.bmac.factory.ports.out.CreateFactoryEntityPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultCreateIngredientUseCase implements CreateIngredientUseCase {

    private final CreateFactoryEntityPort<Ingredient> ingredientCreator;

    @Autowired
    public DefaultCreateIngredientUseCase(CreateFactoryEntityPort<Ingredient> ingredientCreator) {
        this.ingredientCreator = ingredientCreator;
    }

    @Override
    public void create(CreateIngredientCommand command) {
        ingredientCreator.create(
                new Ingredient(UUID.randomUUID(), command.name(), command.unit(), command.temperature()));
    }
}

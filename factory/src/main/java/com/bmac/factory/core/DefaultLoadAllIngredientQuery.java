package com.bmac.factory.core;

import com.bmac.factory.domain.Ingredient;
import com.bmac.factory.ports.in.LoadAllFactoryEntityQuery;
import com.bmac.factory.ports.out.LoadFactoryEntityPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultLoadAllIngredientQuery implements LoadAllFactoryEntityQuery<Ingredient> {

    private final LoadFactoryEntityPort<Ingredient> ingredientLoader;

    @Autowired
    public DefaultLoadAllIngredientQuery(LoadFactoryEntityPort<Ingredient> ingredientLoader) {
        this.ingredientLoader = ingredientLoader;
    }

    @Override
    public List<Ingredient> loadAll() {
        return ingredientLoader.loadAll();
    }
}

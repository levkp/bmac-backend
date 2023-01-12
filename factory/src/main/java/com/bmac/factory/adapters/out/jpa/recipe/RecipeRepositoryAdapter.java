package com.bmac.factory.adapters.out.jpa.recipe;

import com.bmac.common.exception.EntityConstraintException;
import com.bmac.factory.domain.Recipe;
import com.bmac.factory.ports.out.CreateFactoryEntityPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class RecipeRepositoryAdapter implements CreateFactoryEntityPort<Recipe> {

    private final RecipeRepository repository;

    @Autowired
    public RecipeRepositoryAdapter(RecipeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Recipe recipe) {
        try {
            repository.save(toJpaEntity(recipe));
        } catch (DataIntegrityViolationException exception) {
            throw new EntityConstraintException(exception.getMessage());
        }
    }

    private RecipeJpaEntity toJpaEntity(Recipe recipe) {
        Map<UUID, Double> ingredients = recipe.getIngredients().entrySet()
                .stream()
                .collect(Collectors.toMap(
                        k -> k.getKey().getId(),
                        Map.Entry::getValue)
                );
        return new RecipeJpaEntity(recipe.getId(), recipe.getProduct().getId(), ingredients);
    }
}

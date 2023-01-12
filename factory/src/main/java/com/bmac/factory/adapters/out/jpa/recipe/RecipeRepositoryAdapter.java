package com.bmac.factory.adapters.out.jpa.recipe;

import com.bmac.common.exception.EntityConstraintException;
import com.bmac.common.exception.EntityNotFoundException;
import com.bmac.factory.domain.Ingredient;
import com.bmac.factory.domain.Product;
import com.bmac.factory.domain.Recipe;
import com.bmac.factory.ports.out.CreateFactoryEntityPort;
import com.bmac.factory.ports.out.LoadFactoryEntityPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class RecipeRepositoryAdapter implements CreateFactoryEntityPort<Recipe>, LoadFactoryEntityPort<Recipe> {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final RecipeRepository repository;

    private final LoadFactoryEntityPort<Ingredient> ingredientLoader;

    private final LoadFactoryEntityPort<Product> productLoader;

    @Autowired
    public RecipeRepositoryAdapter(RecipeRepository repository,
                                   LoadFactoryEntityPort<Ingredient> ingredientLoader,
                                   LoadFactoryEntityPort<Product> productLoader) {
        this.repository = repository;
        this.ingredientLoader = ingredientLoader;
        this.productLoader = productLoader;
    }

    @Override
    public void create(Recipe recipe) {
        try {
            repository.save(toJpaEntity(recipe));
        } catch (DataIntegrityViolationException exception) {
            throw new EntityConstraintException(exception.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Recipe> loadAll() {
        return repository.findAll().stream().map(this::fromJpaEntity).toList();
    }

    @Override
    public Optional<Recipe> loadById(UUID id) {
        return repository.findById(id).map(this::fromJpaEntity);
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

    private Recipe fromJpaEntity(RecipeJpaEntity jpaEntity) {
         Map<Ingredient, Double> ingredients = jpaEntity.getIngredients().entrySet()
                .stream()
                .collect(Collectors.toMap(
                        k -> ingredientLoader.loadById(k.getKey()).orElseThrow(
                                () -> new EntityNotFoundException(Ingredient.class, UUID.class, k.getKey().toString())
                        ),
                        Map.Entry::getValue)
                );

         var product = productLoader.loadById(jpaEntity.getProductId()).orElseThrow(
                 () -> {
                     log.error("This should never happen");
                     return new EntityNotFoundException(Product.class, UUID.class, jpaEntity.getProductId().toString());
                 }
         );

         return new Recipe(jpaEntity.getId(), product, ingredients);
    }
}

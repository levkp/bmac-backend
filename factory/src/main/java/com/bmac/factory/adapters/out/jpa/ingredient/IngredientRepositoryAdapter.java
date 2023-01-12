package com.bmac.factory.adapters.out.jpa.ingredient;

import com.bmac.common.exception.EntityConstraintException;
import com.bmac.factory.domain.Ingredient;
import com.bmac.factory.ports.out.CreateFactoryEntityPort;
import com.bmac.factory.ports.out.LoadFactoryEntityPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IngredientRepositoryAdapter implements CreateFactoryEntityPort<Ingredient>, LoadFactoryEntityPort<Ingredient> {

    private final IngredientRepository repository;

    @Autowired
    public IngredientRepositoryAdapter(IngredientRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Ingredient ingredient) {
        try {
            repository.save(toJpaEntity(ingredient));
        } catch (DataIntegrityViolationException exception) {
            throw new EntityConstraintException(exception.getMessage());
        }
    }

    private IngredientJpaEntity toJpaEntity(Ingredient ingredient) {
        return new IngredientJpaEntity(ingredient.getId(), ingredient.getName(), ingredient.getUnit(), ingredient.getTemperature());
    }

    private Ingredient fromJpaEntity(IngredientJpaEntity jpaEntity) {
        return new Ingredient(jpaEntity.getId(), jpaEntity.getName(), jpaEntity.getUnit(), jpaEntity.getTemperature());
    }

    @Override
    public List<Ingredient> loadAll() {
        return repository.findAll().stream().map(this::fromJpaEntity).toList();
    }
}

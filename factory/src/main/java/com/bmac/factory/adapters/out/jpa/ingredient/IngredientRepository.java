package com.bmac.factory.adapters.out.jpa.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IngredientRepository extends JpaRepository<IngredientJpaEntity, UUID> { }

package com.bmac.factory.adapters.out.jpa.recipe;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecipeRepository extends JpaRepository<RecipeJpaEntity, UUID> { }

package com.bmac.warehouse.adapters.out.jpa.shelf;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShelfActivityRepository extends JpaRepository<ShelfActivityJpaEntity, UUID> { }
